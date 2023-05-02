package Figures;

import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Figure {

    public Rook (boolean isblack, int position, int nextmove) {
        this.value = 50;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,-1,-2,-3,-4,-5,-6,-7,-8,-16,-24,-32,-40,-48,-56, 8, 16, 24, 32, 40, 48, 57));
    }

    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        for (int move: possibleMoves) {
            nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
        }

        return nextPossibleMoves;
    }


}
