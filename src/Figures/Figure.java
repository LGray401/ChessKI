package Figures;

import java.util.ArrayList;
import java.util.List;

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
    public int nextMove;

    public ArrayList<Integer> possibleMoves;

    public abstract List<Figure> calculatePossibleMoves();

    public ArrayList<Integer> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Integer> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }




}
