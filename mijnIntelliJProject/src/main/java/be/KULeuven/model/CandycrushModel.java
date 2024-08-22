package be.KULeuven.model;
import be.KULeuven.model.BoardSize;
import com.KULeuven.CheckNeighboursInGrid;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static be.KULeuven.model.Position.fromIndex;

public class CandycrushModel {
    private String speler;
    private Board<Candy> speelbord;
    private BoardSize boardSize;
    private int score;

    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new Board();
        this.boardSize = new BoardSize(4,4);
        score = 0;

        for (int i = 0; i < boardSize.aantalKolommen()* boardSize.aantalRijen(); i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.getCellen().put(fromIndex(i, boardSize), randomCandy());
            speelbord.getomgekeerdeCellen().put(randomCandy(), fromIndex(i, boardSize));
        }
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;
        Iterator<Integer> iter = (Iterator<Integer>) model.getSpeelbord();
        while(iter.hasNext()){
            int candy = iter.next();
            System.out.print(candy);
            if(i% model.boardSize.aantalKolommen()==0){
                System.out.print("\n");
                i = 1;
            }
            i++;
        }
        System.out.print("\n");

    }
    public String getSpeler() {
        return speler;
    }

    public Board<Candy> getSpeelbord() {
        return speelbord;
    }

    public int getWidth() {
        return boardSize.getAantalKolommen();
    }

    public BoardSize getBoardSize(){return boardSize;}

    public void candyWithIndexSelected(int index) {
        if (index != -1) {
            Iterable<Integer> neighbours = CheckNeighboursInGrid.getSameNeighboursIds(new ArrayList<>(speelbord.getCellen().values()), boardSize.getAantalKolommen(), boardSize.aantalRijen(), index); //Zet speelbord.getCellen eerst om naar een ArrayList omdat er een Iterable nodig is daar
            Random random = new Random();
            for (Integer neighborIndex : neighbours) {
                int randomGetal = random.nextInt(5) + 1;
                speelbord.replaceCellAtPosition(fromIndex(neighborIndex, this.boardSize), randomCandy());
            }

            int scoreIncrease = (int) neighbours.spliterator().getExactSizeIfKnown(); //score = aantal snoepjes veranderd
            updateScore(scoreIncrease);

        } else {
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public void updateScore(int scoreIncrease) {
        this.score += scoreIncrease;
    }

    public Iterable<Position> getSameNeighbourPositions(Position position){
        ArrayList<Position> buren = (ArrayList<Position>) position.neighborPositions(position.toIndex());
        ArrayList<Position> zelfdeBuren = new ArrayList<>();
        boolean zelfdeType = false;
        Candy snoepje = speelbord.getCellAtPosition(position);
        for(Position buur : buren){
            if(equals(buur.equals(snoepje))){
                zelfdeBuren.add(buur);
            }
        }
        return zelfdeBuren;
    }

    public static Candy randomCandy(){return null;}

    public Set<List<Position>> findAllMatches(){ //Geeft alle matchen (3 opgeenvolgende dezelfde) terug
        Set<List<Position>> matchendeKetens = new HashSet<>(); //Kan niet gewoon Set gebruiken?
        //Plan: ga eerst alle horizontale matches zoeken en dan de verticale, deze 2 (streams of al lijsten?) samenvoegen (concateneren?)
        horizontalStartingPositions()
                .forEach(positie -> {
                    List<Position> match = longestMatchToRight(positie);
                    if (match.size() >= 3) {
                        matchendeKetens.add(match);
                    }
                });

        verticalStartingPositions()
                .forEach(positie -> {
                    List<Position> match = longestMatchToDown(positie);
                    if (match.size() >= 3) {
                        matchendeKetens.add(match);
                    }
                });
        return matchendeKetens;
    } //Hulpfuncties staan hieronder (tot en met longestMatchDown)

    private boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions){ //Geeft true terug wanneer er op de eerste 2 posities van een stream de gegeven Candy staat
        if(positions.limit(2)
                .allMatch(positie -> speelbord.getCellAtPosition(positie).equals(candy))){
            return true;
        }
        else {
            return false;
        }
    }

    //Deze 2 zoeken mogelijke startposities van een nieuwe match, wordt telkens rechts en onder een positie gekeken
    private Stream<Position> horizontalStartingPositions() {
        return IntStream.range(0, speelbord.getBoardSize().aantalRijen() - 1)
                .boxed()
                .flatMap(rij -> IntStream.range(0, speelbord.getBoardSize().getAantalKolommen() - 1)
                        .mapToObj(kolom -> new Position(rij, kolom, boardSize))
                        .filter(positie -> !firstTwoHaveCandy(speelbord.getCellAtPosition(positie), positie.walkRight(positie))));
    }
    private Stream<Position> verticalStartingPositions() {
        return IntStream.range(0, speelbord.getBoardSize().getAantalKolommen() - 1)
                .boxed()
                .flatMap(rij -> IntStream.range(0, speelbord.getBoardSize().aantalRijen() - 1)
                        .mapToObj(kolom -> new Position(rij, kolom, boardSize))
                        .filter(positie -> !firstTwoHaveCandy(speelbord.getCellAtPosition(positie), positie.walkDown(positie))));
    }

    //Deze 2 zoeken de langste matchen vanaf een positie
    private List<Position> longestMatchToRight(Position position){
        return position.walkRight(position)
                .takeWhile(p -> speelbord.getCellAtPosition(p).equals(speelbord.getCellAtPosition(position)))
                .collect(Collectors.toList());
    }
    private List<Position> longestMatchToDown(Position position){
        return position.walkDown(position)
                .takeWhile(p -> speelbord.getCellAtPosition(p).equals(speelbord.getCellAtPosition(position)))
                .collect(Collectors.toList());
    }

    private void clearMatch(List<Position> match) {
        if (match.isEmpty()) {return;} //Als de List leeg is mag er gestopt worden
        Position position = match.get(0);
        speelbord.replaceCellAtPosition(position, null); //Heb null gebruikt om lege plaatsen aan te tonen
        match.remove(0);
        List<Position> cellen = new ArrayList<>(speelbord.getCellen().keySet());
        clearMatch(match); //Geeft een geupdate versie van de matches mee aan de volgende instantie van de methode
    }

    private void fallDownTo(Position pos) {
        int rij = pos.rij(); //De rij is het enige dat van de candy veranderd in de positie van de candy

        while (rij < boardSize.getAantalRijen() - 1) { //Bord is opgebouwd met idex 0 links vanboven (zoals bij punt 3 van opdracht 8 op de website staat)
            Position volgendePositie = new Position(rij - 1, pos.kolom(), boardSize); //volgendePositie is de positie van de volgende candy dat naar beneden moet vallen
            Candy aboveCandy = speelbord.getCellAtPosition(volgendePositie);

            if (aboveCandy == null) {
                rij++;
            }
            else {
                Candy candyOpPositie = speelbord.getCellAtPosition(pos);
                speelbord.replaceCellAtPosition(pos, aboveCandy);
                speelbord.replaceCellAtPosition(volgendePositie, candyOpPositie);

                fallDownTo(volgendePositie);
                return;
            }
        }
    }

    private boolean updateBoard() {
        boolean matchesVerwijderd = false;
        Set<List<Position>> matches = findAllMatches();
        if (!matches.isEmpty()) {
            matchesVerwijderd = true;

            for (List<Position> match : matches) {
                clearMatch(match);
            }
            for (int i = 0; i < speelbord.getBoardSize().aantalKolommen(); i++) { //i stelt hier de kolom voor waarin de candies naar beneden vallen
                fallDownTo(new Position(speelbord.getBoardSize().aantalRijen() - 1, i, boardSize));
            }

            updateBoard();
        }
        return matchesVerwijderd;
    }

    private void maximizeScore(Board bord){//Skelet deze en volgende methodes (inclusief PartialSolution, Solution en Extension.java) van de SES website
        /*
        * Algemene plan: begin bij positie met index 0 en ga alle mogelijke wissels af, als er geen zijn:
        * ga naar positie 2 en doe hetzelfde. Als er wel een match kan gemaakt worden ga dan verder hiermee
        * maw: voer de hele functie opnieuw uit over het nieuwe bord dat er onstaan is na de match.
        * Houdt alle mogelijke wissels bij in een (Array)List? Beste oplossing is degene die het meeste candies weg doet.
        *
        * Een move wordt bijgehouden als de 2 indexen die gewisselt worden van plaats
        * */


    }
    public Solution solve() {
        PartialSolution initial = new PartialSolution(speelbord, new ArrayList<>()); // Create an initial solution with an empty list of moves
        return findOptimalSolution(initial, null);
    }
}