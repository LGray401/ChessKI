package Figures;

import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(boolean isblack, int position, int nextmove) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves =new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, - 54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49));
    }

    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        for(int move: possibleMoves){
            nextPossibleMoves.addMove(new Bishop(isBlack, position, position + move));
        }

        return nextPossibleMoves;
    }
}
