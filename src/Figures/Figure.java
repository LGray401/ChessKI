package Figures;

import java.util.ArrayList;

public abstract class Figure {

    public int value;
    public boolean isBlack;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    int nextmove;

    public ArrayList<Integer> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Integer> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    ArrayList<Integer> possibleMoves;


}
