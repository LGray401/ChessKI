package Figures;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {

    public int value;
    public boolean isBlack;
    int position;
    public int nextMove;

    public ArrayList<Integer> possibleMoves;

    public abstract List<Figure> calculatePossibleMoves();

}
