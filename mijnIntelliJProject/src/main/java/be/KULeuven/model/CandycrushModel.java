package be.KULeuven.model;
import be.KULeuven.model.BoardSize;
import com.KULeuven.CheckNeighboursInGrid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
}