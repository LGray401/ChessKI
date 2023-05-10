package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Figure {

    boolean alreadyMoved = false;

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public King(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(900);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, -1, 8, -8)));
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
