package Figures;
// import Figures.Figure;

import Main.Board;
import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen  extends Figure {

    ArrayList<Integer> movesNE = new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63));
    ArrayList<Integer> movesSW = new ArrayList<>(Arrays.asList(-9, -18, -27, -36, -45, -54, -63));
    ArrayList<Integer> movesNW = new ArrayList<>(Arrays.asList(7, 14, 21, 28, 35, 42, 49));
    ArrayList<Integer> movesSE = new ArrayList<>(Arrays.asList(-7, -14, -21, -28, -35, -42, -49));
    ArrayList<Integer> movesRight = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    ArrayList<Integer> movesLeft = new ArrayList<>(Arrays.asList(-1, -2, -3, -4, -5, -6, -7));
    ArrayList<Integer> movesUp = new ArrayList<>(Arrays.asList(8, 16, 24, 32, 40, 48, 57));
    ArrayList<Integer> movesDown = new ArrayList<>(Arrays.asList(-8, -16, -24, -32, -40, -48, -56));


    public Queen(boolean isblack, int position, int nextmove) {
        this.value = 90;
        this.isBlack = isblack;
        this.position = position;
        this.nextPosition = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 56, // up, down, left, right
                9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49)); // diagonal
    }


    @Override
    public List<Figure> calculatePossibleMoves(Board board) {
        MovesList nextPossibleMoves = new MovesList();

        for (int move : movesRight) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesLeft) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesUp) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesDown) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesNE) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesSE) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesSW) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move : movesNW) {
            if (board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }
        return nextPossibleMoves;
    }
}