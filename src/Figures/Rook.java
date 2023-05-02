package Figures;

import Main.Board;
import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Figure {

    ArrayList<Integer> movesRight =  new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
    ArrayList<Integer> movesLeft = new ArrayList<>(Arrays.asList(-1,-2,-3,-4,-5,-6,-7));
    ArrayList<Integer> movesUp = new ArrayList<>(Arrays.asList(8, 16, 24, 32, 40, 48, 57));
    ArrayList<Integer> movesDown = new ArrayList<>(Arrays.asList(-8,-16,-24,-32,-40,-48,-56));

    public Rook (boolean isblack, int position, int nextmove) {
        this.value = 50;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,-1,-2,-3,-4,-5,-6,-7,-8,-16,-24,-32,-40,-48,-56, 8, 16, 24, 32, 40, 48, 57));
    }

    @Override
    public List<Figure> calculatePossibleMoves(Board board) {
        MovesList nextPossibleMoves = new MovesList();

        for (int move: movesRight) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesLeft) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesUp) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesDown) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        return nextPossibleMoves;
    }


}
