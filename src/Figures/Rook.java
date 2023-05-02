package Figures;

import Main.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Figure {

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

    public ArrayList<Integer> movingEAST(Board board) {



        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() + i;
            if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingWEST(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i;
            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingSN(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() + i*8;
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return list;
    }

    public List<Integer> movingNS(Board board) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i*8;
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return list;
    }
}
