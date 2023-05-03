package Main;

import Figures.Bishop;
import Figures.Figure;
import Figures.Queen;
import Figures.Rook;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(Board.intialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);

        board.getBoard()[25] = new Bishop(false, 25);
        board.getBoard()[24] = new Rook(false, 24);
        board.getBoard()[28] = new Queen(false, 28);


        player1.playerGetFigureList(board);
        player1.generateAllMoves(board);
        player2.playerGetFigureList(board);
        player2.generateAllMoves(board);

        for (Figure figure: player1.getFigureList()) {System.out.println("Possible Moves of " + figure.getClass().getName() + ": " + figure.getPossibleMoveList());}
        for (Figure figure: player2.getFigureList()) {System.out.println("Possible Moves of " + figure.getClass().getName() + ": " + figure.getPossibleMoveList());}

    }
}
