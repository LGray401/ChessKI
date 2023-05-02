package Figures;

import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Figure {

    boolean hasMoved;
    public Pawn(boolean isblack, int position, boolean hasmoved, int nextmove, ArrayList possiblemoves) {
        this.value = 10;
        this.isBlack = isblack;
        this.position = position;
        this.hasMoved = hasmoved;
        this.nextMove = nextmove;
        this.possibleMoves = possiblemoves;

    }

    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        if (!hasMoved) {
            nextPossibleMoves.addMove(isBlack ? new Pawn(isBlack, position, hasMoved, position -16, possibleMoves) : new Pawn(isBlack, position, hasMoved, position +16, possibleMoves));
        }

        for (int move: possibleMoves) {
            nextPossibleMoves.addMove(new Pawn(isBlack, position, hasMoved, position + move, possibleMoves));
        }



        return nextPossibleMoves;
    }


}
