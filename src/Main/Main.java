package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;
        String fenString = "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R";
        String fenTest = "r1B1kb2/6p1/n4nP1/2P1P1Qr/2PP3P/2Kq4/1P3p2/R1B2qR1";
        String fenStringStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

        Board board = new Board();
        board.setBoardFromFEN(fenStringStart);
        //board.setBoardFromFEN(fenTest);

        //board.to2DArrayAndDisplay(board.getBoard());
        //board.isGameOverAndExit(player2.isBlack());

        //Figure nextMove = player2.makeMove(board);
        //nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));

        for (int i = 0; i < amountOfMoves; i++) {
            //player1.printAllMovesAndAmountOfMovesGivenBoard(board);
            Figure nextMove = player1.makeAlphaBeta(board);
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player2.isBlack());

            //player2.printAllMovesAndAmountOfMovesGivenBoard(board);

            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player1.isBlack());


            //System.out.println(board.createFENFromBoard(board.getBoard()));

        }
   }
}