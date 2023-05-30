package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;
        String fenStringStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String fen = "k7/8/8/8/8/8/7K/8";

        Board board = new Board();
        board.setBoardFromFEN(fen);

        for (int i = 0; i < amountOfMoves; i++) {
            //player1.printAllMovesAndAmountOfMovesGivenBoard(board);
            Figure nextMove = player1.findBestMove(board);
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOver(player2.isBlack());

            //player2.printAllMovesAndAmountOfMovesGivenBoard(board);

            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOver(player1.isBlack());


            //Board.simulateGame(fenString, player1, player2, amountOfMoves);
            //System.out.println(board.createFENFromBoard(board.getBoard()));

        }
    }
}