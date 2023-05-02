package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(Board.intialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);
        player1.playerGetFigureList(board);
        player1.generateAllMoves(board);

        for (Figure figure: player1.getFigureList()) {
            System.out.println("Possible Moves of " + figure.getClass().getName() + ": " + figure.getPossibleMoveList());
        }

        player2.playerGetFigureList(board);
        player2.generateAllMoves(board);

        for (Figure figure: player2.getFigureList()) {
            System.out.println("Possible Moves of " + figure.getClass().getName() + ": " + figure.getPossibleMoveList());
        }


    }

}
