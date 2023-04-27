import Figures.Figure;

import java.util.List;

public class Player {
    boolean isBlack;
    List<Figure> possibleMoves;

    Player(boolean isblack) {
        isBlack = isblack;
    }

    Figure calculateMove(Board board) {
        for (Figure figure : board.board)
            if (figure.isBlack == this.isBlack) {
                List<Figure> possibleMovesFigure;
                possibleMovesFigure = calculatePossibleMoves(figure);
                possibleMoves.addAll(possibleMovesFigure);
            }
        return possibleMoves.get(0);
    }

    List<Figure> calculatePossibleMoves(Figure figure) {
        // return all possible moves of the figure
        return null;
    }
}
