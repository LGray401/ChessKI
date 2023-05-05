package Main;

import Figures.Bishop;
import Figures.Figure;
import Figures.Queen;
import Figures.Rook;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(Board.intialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);

        player1.playerGetFigureList(board);
        player1.generateAllMoves(board);
        player2.playerGetFigureList(board);
        player2.generateAllMoves(board);

        System.out.println("All legal moves for player1: " + player1.getAllMovesInFenNotation());
        System.out.println("All legal moves for player2: " + player2.getAllMovesInFenNotation());

        Figure[] testBoard = Board.createBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        System.out.println(Arrays.toString(testBoard));
        System.out.println(testBoard.length);



    }
}
