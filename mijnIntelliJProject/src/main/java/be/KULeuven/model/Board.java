package be.KULeuven.model;

import java.util.ArrayList;
import java.util.function.Function;

public class Board<E> { //E is een cel op het bord, ongeacht voor wat dit bord dient
    private ArrayList<E> cellen;
    private BoardSize boardSize;

    public ArrayList<E> getCellen(){return this.cellen;}
    public void setCellen(ArrayList<E> cellen){
        this.cellen = cellen;
    }
    public BoardSize getBoardSize(){return this.boardSize;}

    public E getCellAtPosition(Position position){
        E element = cellen.get(position.toIndex());
        return element;
    }

    public void replaceCellAtPosition(Position position, E nieuweCel){
        cellen.set(position.toIndex(), nieuweCel);
    }

    public void fill(Function cellCreator, Position position){
       cellen.set(position.toIndex(), cellCreator());
    }
    private E cellCreator(){
        return null; //Geeft niet het juiste terug
    }

    public void copyTo(Board gegevenBord) throws Exception {
        if(this.boardSize.equals(gegevenBord.getBoardSize())) {
             gegevenBord.setCellen(this.cellen);
        }
        else throw new Exception("BoardSizes zijn niet gelijk");
    }

    public Board(ArrayList<E> cellen, BoardSize boardSize){
        this.cellen = cellen;
        this.boardSize = boardSize;
    }
    public Board(){}

    @Override
    public boolean equals(Object obj) { //Methode toegevoegd op aanraden van blackbox.ai, leek er op dat assertEquals(eersteBord, tweedeBord) iets gelijkaardig deed aan assert(eersteBord == tweedeBord) want de error gaf toen mee dat het twee "Board" opbjecten waren maar met een andere @watNummers
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Board board = (Board) obj;
        return boardSize.equals(board.boardSize) && cellen.equals(board.cellen);
    }
}