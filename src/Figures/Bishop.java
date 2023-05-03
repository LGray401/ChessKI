package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public ArrayList<Integer> movingNE(Board board) {



        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() + i*9;
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

    public ArrayList<Integer> movingNW(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i*7;
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

    public ArrayList<Integer> movingSE(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i*7;
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

    public List<Integer> movingSW(Board board) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i*9;
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
}
