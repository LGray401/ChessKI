package Figures;

import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Figure {

    public King(boolean isblack, int position, int nextmove) {
        this.value = 900;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,-1,8,-8));
    }

    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        for(int move: possibleMoves){
            nextPossibleMoves.addMove(new King(isBlack, position, position + move));
        }

        return nextPossibleMoves;
    }
}
