package Figures;

import java.util.ArrayList;
import java.util.Arrays;

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
}
