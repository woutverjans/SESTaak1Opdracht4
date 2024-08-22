package be.KULeuven.model;

import java.util.List;

public class BoardPartialSolution {
    private Board board;
    private List<int[]> moves;

    public BoardPartialSolution(Board board, List<int[]> moves) {
        this.board = board;
        this.moves = moves;
    }
}
