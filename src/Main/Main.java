package Main;

import Figures.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(Board.intialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);


        Figure nextMove = player1.makeMove(board);
        nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
        board.changeBoard(nextMove);
        System.out.println("Player1 moved: " + nextMove.getNextPosition());
        nextMove = player2.makeMove(board);
        nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
        board.changeBoard(nextMove);
        System.out.println("Player2 moved: " + nextMove.getNextPosition());


        System.out.println("All legal moves for player1: " + player1.getAllMovesInFenNotation());
        System.out.println("All legal moves for player2: " + player2.getAllMovesInFenNotation());

        //Figure[] testBoard = Board.createBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");


    }
}
