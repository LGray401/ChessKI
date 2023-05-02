package Figures;

import Main.Board;
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
        this.nextPosition = nextmove;
        this.possibleMoves = possiblemoves;

    }

    @Override
    public List<Figure> calculatePossibleMoves(Board board) {
        MovesList nextPossibleMoves = new MovesList();

        if (!hasMoved) {
            possibleMoves.add(isBlack ? -16 : 16);
        }

        for (int move: possibleMoves) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {

                nextPossibleMoves.addMove(new Pawn(isBlack, position, true, position + move, possibleMoves));
            }
        }



        return nextPossibleMoves;
    }


}
