package Figures;
// import Figures.Figure;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen  extends Figure {

    public Queen(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(90);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 56, // up, down, left, right
                9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49))); // diagonal
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(this.movingNorth(board));
        list.addAll(this.movingSouth(board));
        list.addAll(this.movingEAST(board));
        list.addAll(this.movingWEST(board));
        list.addAll(this.movingSE(board));
        list.addAll(this.movingSW(board));
        list.addAll(this.movingNE(board));
        list.addAll(this.movingNW(board));

        this.setPossibleMoveList(list);
    }
}