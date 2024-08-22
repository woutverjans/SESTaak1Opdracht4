/*package be.KULeuven.view;

import be.KULeuven.model.Candy;
import be.KULeuven.model.CandycrushModel;
import be.KULeuven.model.Position;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class CandycrushView extends Region {
    private CandycrushModel model;
    private int widthCandy;
    private int heigthCandy;

    public CandycrushView(CandycrushModel model) {
        this.model = model;
        widthCandy = 30;
        heigthCandy = 30;
        update();
    }

    public void update(){
        getChildren().clear();
        int i = 0;
        int height = 0;
        Iterator<Integer> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()) {
            int candy = iter.next();
            Rectangle rectangle = new Rectangle(i * widthCandy, height * heigthCandy, widthCandy,heigthCandy);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            Text text = new Text("" + candy);
            text.setX(rectangle.getX() + (rectangle.getWidth() - text.getBoundsInLocal().getWidth()) / 2);
            text.setY(rectangle.getY() + (rectangle.getHeight() + text.getBoundsInLocal().getHeight()) / 2);
            getChildren().addAll(rectangle,text);

            if (i == model.getWidth() - 1) {
                i = 0;
                height++;
            } else {
                i++;
            }
        }
    }

    public int getIndexOfClicked(MouseEvent me){
        int index = -1;
        int row = (int) me.getY()/heigthCandy;
        int column = (int) me.getX()/widthCandy;
        System.out.println(me.getX()+" - "+me.getY()+" - "+row+" - "+column);
        if (row < model.getWidth() && column < model.getHeight()){
            index = model.getIndexFromRowColumn(row,column);
            System.out.println(index);
        }
        return index;
    }

    Node makeCandyShape(Position position, Candy candy){
        return switch(candy){
            case Candy.NormalCandy c -> Circle cirkel = new Circle(10);
            case Candy.Gummybeer c -> Rectangle rechthoek = new Rectangle(10, 20);
            case Candy.Haaientand c -> Rectangle rechthoek = new Rectangle(10, 20);
            case Candy.Lekstok c -> Rectangle rechthoek = new Rectangle(10, 20);
            case Candy.Zuurstok c -> Rectangle rechthoek = new Rectangle(10, 20);
        };
    }

    CandycrushModel model1 = createBoardFromString("""
   @@o#
   o*#o
   @@**
   *#@@""");

    CandycrushModel model2 = createBoardFromString("""
   #oo##
   #@o@@
   *##o@
   @@*@o
   **#*o""");

    CandycrushModel model3 = createBoardFromString("""
   #@#oo@
   @**@**
   o##@#o
   @#oo#@
   @*@**@
   *#@##*""");


    public static CandycrushModel createBoardFromString(String configuration) {
        var lines = configuration.toLowerCase().lines().toList();
        BoardSize size = new BoardSize(lines.size(), lines.getFirst().length());
        var model = createNewModel(size); // deze moet je zelf voorzien
        for (int row = 0; row < lines.size(); row++) {
            var line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                model.setCandyAt(new Position(row, col, size), characterToCandy(line.charAt(col)));
            }
        }
        return model;
    }

    private static Candy characterToCandy(char c) {
        return switch(c) {
            case '.' -> null;
            case 'o' -> new Candy.NormalCandy(0);
            case '*' -> new Candy.NormalCandy(1);
            case '#' -> new Candy.NormalCandy(2);
            case '@' -> new Candy.NormalCandy(3);
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }
}*/