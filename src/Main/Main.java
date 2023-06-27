package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;

        String fenStringStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String fen = "k7/8/8/8/8/8/7K/8";
        String fenTest = "8/pr4k1/3Q4/2N5/5P2/1p6/P5KP/4qR2";
        String fenTest2 = "8/5qk1/8/8/5P2/1p6/6KP/8";

        //benchmark
        Benchmark benchmark = new Benchmark();
        //benchmark.benchmarkAlphaBeta(fenStringStart, 10);
        //benchmark.benchmarkMinmax(fenStringStart, 1);

        Board board = new Board();
        board.setBoardFromFEN(fenTest);
        //player1.setFigureAndMovesListForPlayerGivenBoard(board);
        //player2.setFigureAndMovesListForPlayerGivenBoard(board);
        /*while(true) {

            Figure nextMove = player1.makeAlphaBeta(board); // black (lower case) wins
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player2.isBlack());

            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player1.isBlack());
        }*/

        benchmark.benchmarkNegaMax(fenTest, 1);
    }
}