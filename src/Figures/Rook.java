package Figures;

import Main.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Figure {

    boolean alreadyMoved = false;

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public Rook (boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(50);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 57)));
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(this.movingSN(board));
        list.addAll(this.movingNS(board));
        list.addAll(this.movingEAST(board));
        list.addAll(this.movingWEST(board));

        this.setPossibleMoveList(list);


    }
    public ArrayList<Integer> movingWestLongRochade(Board board) {



        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <7; i ++){
            if (getWestBarrier().contains(this.getPosition())) break;
            int newPos = this.getPosition() - i;
            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                } else if (this.isAlreadyMoved()== false &&board.getBoard()[newPos].isBlack() == this.isBlack()&& board.getBoard()[newPos]instanceof King ) {



                }
            }
        }
        return list;
    }



}
