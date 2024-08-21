package be.KULeuven.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Board<E> { //E is een cel op het bord, ongeacht voor wat dit bord dient
    private Map<Position, E> cellen; //Houdt het element op elke positie bij
    private Map<E, Position> omgekeerdeCellen; //Omgekeerd, houd dus bij waar een bepaald element allemaal voorkomt
    private BoardSize boardSize;

    public Map getCellen(){return this.cellen;}
    public Map getomgekeerdeCellen(){return this.omgekeerdeCellen;}
    public void setCellen(Map cellen){
        this.cellen = cellen;
        this.omgekeerdeCellen = cellen;
    }
    public BoardSize getBoardSize(){return this.boardSize;}

    public E getCellAtPosition(Position position){
        E element = cellen.get(position.toIndex());
        return element;
    }

    public void replaceCellAtPosition(Position position, E nieuweCel){
        cellen.put(position, nieuweCel);
        omgekeerdeCellen.put(nieuweCel, position);
    }

    public void fill(Function cellCreator, Position position){
       cellen.put(position, cellCreator());
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

    public Board(Map cellen, BoardSize boardSize){
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

    public List<Position> getPositionsOfElement(E element){
        return List.of(omgekeerdeCellen.get(element)); //Volgens de SES website 5.3: Collections: 5.3.: Lijsten aanmaken is dit immutable
    }
}