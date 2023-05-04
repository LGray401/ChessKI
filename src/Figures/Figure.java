package Figures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Main.Board;

public abstract class Figure {

    private boolean isEmptyField;
    private int value;
    private boolean isBlack;
    private int position;
    private int nextPosition;
    private ArrayList<Integer> moveSummandList;
    private ArrayList<Integer> possibleMoveList;
    private static final ArrayList<Integer> eastBarrier = new ArrayList<>(Arrays.asList(7, 15, 23, 31, 39, 47, 55, 63));
    private static final ArrayList<Integer> westBarrier = new ArrayList<>(Arrays.asList(0, 8, 16, 24, 32, 40, 48, 56));
    private static final ArrayList<Integer> northBarrier = new ArrayList<>(Arrays.asList(56, 57, 58, 59, 60, 61, 62, 63));
    private static final ArrayList<Integer> southBarrier = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15));

    public static ArrayList<Integer> getEastBarrier() {
        return eastBarrier;
    }

    public static ArrayList<Integer> getWestBarrier() {
        return westBarrier;
    }

    public ArrayList<Integer> getPossibleMoveList() {
        return possibleMoveList;
    }

    public void setPossibleMoveList(ArrayList<Integer> possibleMoveList) {
        this.possibleMoveList = possibleMoveList;
    }

    public void concatenatePossibleMoveList(ArrayList<Integer> possibleMoveList){
        getPossibleMoveList().addAll(possibleMoveList);
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField(boolean emptyField) {
        isEmptyField = emptyField;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(int nextPosition) {
        this.nextPosition = nextPosition;
    }

    public ArrayList<Integer> getMoveSummandList() {
        return moveSummandList;
    }

    public void setMoveSummandList(ArrayList<Integer> moveSummandList) {
        this.moveSummandList = moveSummandList;
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int moveSummand: this.getMoveSummandList()) {
            if ((this.getPosition() + moveSummand < 64) && (this.getPosition() + moveSummand >= 0)) {
                //list.add(this.getPosition() + moveSummand);
            }
        }
        this.setPossibleMoveList(list);
    }

    public boolean withInPossibleRange(int i){
        if (i >= 0 && i <= 63) return true;
        return false;
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

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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

    public ArrayList<Integer> movingNE(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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

        int multiplier = (this instanceof King) ? 1 : 7;



        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
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
