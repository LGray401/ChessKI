package Figures;

import java.util.ArrayList;
import java.util.List;
import Main.Board;

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

    public abstract List<Figure> calculatePossibleMoves(Board board);

    public ArrayList<Integer> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Integer> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }




}
