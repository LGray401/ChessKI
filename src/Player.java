import Figures.Figure;
import helpers.MovesList;

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
                possibleMoves.addAll(figure.calculatePossibleMoves());
            }
        legalMoves = removeIllegalMoves(possibleMoves);

        return legalMoves.get(0);
    }



    List<Figure> removeIllegalMoves(List<Figure> moves) {

        // prüft, ob auf dem Weg zum Zielfeld alle Felder frei sind (außer beim Pferd)
        // prüft, ob Zielfeld leer ist oder gegnerischer Spieler drauf ist
        // prüft, ob man nach dem Move im Schach steht
        //prüft, ob man nach dem Move immer noch im Schach steht

        return null;
    }
}
