package Main;

import Figures.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(board.intialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);


        while (true ) {
            board.isGameOver(player2.isBlack());
            Figure nextMove = player1.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() +" from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.isGameOver(player1.isBlack());
            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() +" from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            //System.out.println(board.createFENFromBoard(board.getBoard()));
        }


   }
}
