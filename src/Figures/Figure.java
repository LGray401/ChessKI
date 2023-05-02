package Figures;

import java.util.ArrayList;

import Main.Board;

public abstract class Figure {

    private boolean isEmptyField;
    private int value;
    private boolean isBlack;
    private int position;
    private int nextPosition;
    private ArrayList<Integer> moveSummandList;
    private ArrayList<Integer> possibleMoveList;

    public ArrayList<Integer> getPossibleMoveList() {
        return possibleMoveList;
    }

    public void setPossibleMoveList(ArrayList<Integer> possibleMoveList) {
        this.possibleMoveList = possibleMoveList;
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
                list.add(this.getPosition() + moveSummand);
            }
        }
        this.setPossibleMoveList(list);
    }
}
