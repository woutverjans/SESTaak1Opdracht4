package be.kuleuven.candycrush.model;
import com.KULeuven.CheckNeighboursInGrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CandycrushModel {
    private String speler;
    private ArrayList<Integer> speelbord;
    private int width;
    private int height;
    private int score;

    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new ArrayList<>();
        width = 4;
        height = 4;
        score = 0;

        for (int i = 0; i < width*height; i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.add(randomGetal);
        }
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;
        Iterator<Integer> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()){
            int candy = iter.next();
            System.out.print(candy);
            if(i% model.getWidth()==0){
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

    public ArrayList<Integer> getSpeelbord() {
        return speelbord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getScore(){return score;}

    public void candyWithIndexSelected(int index) {
        if (index != -1) {
            Iterable<Integer> neighbours = CheckNeighboursInGrid.getSameNeighboursIds(speelbord, width, height, index);
            Random random = new Random();

            for (Integer neighborIndex : neighbours) {
                int randomGetal = random.nextInt(5) + 1;
                speelbord.set(neighborIndex, randomGetal);
            }

            int scoreIncrease = (int) neighbours.spliterator().getExactSizeIfKnown(); //score = aantal snoepjes veranderd
            updateScore(scoreIncrease);

        } else {
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public int getIndexFromRowColumn(int row, int column) {
        return column+row*width;
    }

    public void updateScore(int scoreIncrease) {
        this.score += scoreIncrease;
    }
}