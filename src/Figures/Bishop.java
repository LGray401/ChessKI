package Figures;

import Main.Board;
import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Figure {

    ArrayList<Integer> movesNE =  new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63));
    ArrayList<Integer> movesSW = new ArrayList<>(Arrays.asList(-9, -18, -27, -36, -45, - 54, -63));
    ArrayList<Integer> movesNW = new ArrayList<>(Arrays.asList(7, 14, 21, 28, 35, 42, 49));
    ArrayList<Integer> movesSE = new ArrayList<>(Arrays.asList(-7, -14, -21, -28, -35, -42, -49));
    public Bishop(boolean isblack, int position, int nextmove) {
        this.value = 30;
        this.isBlack = isblack;
        this.position = position;
        this.nextPosition = nextmove;
        this.possibleMoves =new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, - 54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49));
    }

    @Override
    public List<Figure> calculatePossibleMoves(Board board) {
        MovesList nextPossibleMoves = new MovesList();

        for (int move: movesNE) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesSE) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesSW) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        for (int move: movesNW) {
            if(board.getBoard()[position + move].getClass().getName() == EmptyField.class.getName()) {
                nextPossibleMoves.addMove(new Rook(isBlack, position, position + move));
            }

        }

        return nextPossibleMoves;
    }}
