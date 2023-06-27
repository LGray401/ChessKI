package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Figure {

    public Bishop(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49)));
    }


    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(this.movingSE(board));
        list.addAll(this.movingSW(board));
        list.addAll(this.movingNE(board));
        list.addAll(this.movingNW(board));

        this.setPossibleMoveList(list);
    }

    @Override
    public int getTypeAsInt() {
        return 3;
    }
}
