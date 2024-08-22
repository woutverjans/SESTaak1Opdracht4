package be.KULeuven.model;

public class BoardExtension {
    private int[] move;
    private Board newBoard;

    public BoardExtension(int[] move, Board newBoard) {
        this.move = move;
        this.newBoard = newBoard;
    }
}
