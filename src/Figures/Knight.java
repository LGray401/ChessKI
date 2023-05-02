package Figures;


import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Figure{

    public Knight(boolean isblack, int position, int nextmove) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(6, 10, 15, 17, 19, -6, -10, -15, -17 - 19));
    }

    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        for(int move: possibleMoves){
            nextPossibleMoves.addMove(new Knight(isBlack, position, position + move));
        }

        return nextPossibleMoves;
    }
}
