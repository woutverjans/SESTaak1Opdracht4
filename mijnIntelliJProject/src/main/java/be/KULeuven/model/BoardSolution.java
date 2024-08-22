package be.KULeuven.model;

import java.util.List;

public class BoardSolution {
    private Board board;
    private List<int[]> moves;

    public BoardSolution(Board board, List<int[]> moves) {
        this.board = board;
        this.moves = moves;
    }
}
