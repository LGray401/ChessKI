import Figures.EmptyField;
import Figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Player {
    boolean isBlack;
    List<Figure> legalMoves;

    Player(boolean isblack) {
        isBlack = isblack;
    }

    Figure calculateMove(Board board) {

        List<Figure> possibleMoves = new ArrayList<>();

        for (Figure figure : board.board)
            if (figure.isBlack == this.isBlack) {
                List<Figure> possibleMovesFigure;
                possibleMovesFigure = calculatePossibleMoves(figure);
                possibleMoves.addAll(possibleMovesFigure);
            }
        legalMoves = removeIllegalMoves(possibleMoves);

        return legalMoves.get(0);
    }

    List<Figure> calculatePossibleMoves(Figure figure) {


        // return all possible moves of the figure

        return null;
    }

    List<Figure> removeIllegalMoves(List<Figure> moves) {

        // prüft, ob auf dem Weg zum Zielfeld alle Felder frei sind (außer beim Pferd) Linh
        // prüft, ob Zielfeld leer ist oder gegnerischer Spieler drauf ist Ely
        // prüft, ob man nach dem Move im Schach steht Rudi
        //prüft, ob man nach dem Move immer noch im Schach steht Rudi

        testEly(moves); // last method to call

        return moves;
    }

    List<Figure> testEly(List<Figure> moves) {

        List<Figure> newList = new ArrayList<>();

        for (Figure figure: moves) {
            for(int i = 0; i < figure.getPossibleMoves().size(); i++) {
                int newPosition = figure.getPosition() + i;
                Figure player1 = Board.board[figure.getPosition()];
                Figure player2 = Board.board[newPosition];

                // checks if target field is empty or occupied by enemy
                if(player2 == EmptyField.getEmptyField()|| player1.isBlack != player2.isBlack) {
                    newList.add(figure);
                }
            }
        }
        return newList;
    }
}
