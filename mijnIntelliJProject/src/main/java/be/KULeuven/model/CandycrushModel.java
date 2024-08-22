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
}