package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Figure {



    public King(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(900);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, -1, 8, -8)));
        if(isblack ? (position == 60) : (position == 4)) {
            this.setHasMoved(false);
        } else {
            this.setHasMoved(true);
        }

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
        list.addAll(this.addRochade(board));

        this.setPossibleMoveList(list);
    }

    private ArrayList<Integer> addRochade(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        if (!this.isBlack()) {
            if (this.hasMoved() == false && board.getBoard()[0] instanceof Rook && board.getBoard()[1].isEmptyField() && board.getBoard()[2].isEmptyField() && board.getBoard()[3].isEmptyField()) {
                if (!((Rook) board.getBoard()[0]).hasMoved()) {
                    list.add(100);
                }

            }
            if (this.hasMoved() == false && board.getBoard()[7] instanceof Rook && board.getBoard()[5].isEmptyField() && board.getBoard()[6].isEmptyField()) {
                if (!((Rook) board.getBoard()[7]).hasMoved()) {
                    list.add(101);
                }
            }
        } else {
            if (this.hasMoved() == false && board.getBoard()[56] instanceof Rook && board.getBoard()[57].isEmptyField() && board.getBoard()[58].isEmptyField() && board.getBoard()[59].isEmptyField()) {
                if (!((Rook) board.getBoard()[56]).hasMoved()) {
                    list.add(101);
                }
            }
            if (this.hasMoved() == false && board.getBoard()[63] instanceof Rook && board.getBoard()[61].isEmptyField() && board.getBoard()[62].isEmptyField()) {
                if (!((Rook) board.getBoard()[63]).hasMoved()) {
                    list.add(100);
                }
            }
        }
        return list;


    }



}
