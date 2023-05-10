package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(board.initialize());
        Player player1 = new Player(false);
        Player player2 = new Player(true);

        //board.setBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"); //does not work
        board.setBoardFromFEN("7k/8/8/8/8/8/7P/K7"); //does not work
        board.to2DArrayAndDisplay(board.getBoard());
        //player1.printAllMovesAndAmountOfMovesGivenBoard(board);


        while (true) {
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
