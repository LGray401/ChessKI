package Figures;


import Main.Board;
import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Figure{

    public Knight(boolean isblack, int position, int nextmove) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.nextPosition = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(6, 10, 15, 17, 19, -6, -10, -15, -17 - 19));
    }

    public List<Figure> calculatePossibleMoves(Board board) {

        MovesList nextPossibleMoves = new MovesList();

        for (int move: possibleMoves) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new King(isBlack, position, position + nextPosition));
            }
        }

        return nextPossibleMoves;
    }
}
