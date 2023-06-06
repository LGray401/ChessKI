package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;

        String fenStringStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String fen = "k7/8/8/8/8/8/7K/8";
        String fenTest = "6r1/p5k1/3Q4/2N5/5P2/1p6/P5KP/4qR2";
        String fenTest2 = "8/6k1/8/8/5P2/1p6/6KP/8";

        //benchmark
        Benchmark benchmark = new Benchmark();
        //benchmark.benchmarkAlphaBeta(fenStringStart, 10);
        //benchmark.benchmarkMinmax(fenStringStart, 1);

        Board board = new Board();
        board.setBoardFromFEN(fenTest2);

        /*for (int i = 0; i < amountOfMoves; i++) {
            //player1.printAllMovesAndAmountOfMovesGivenBoard(board);
            Figure nextMove = player1.findBestMove(board);
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player2.isBlack());

            //player2.printAllMovesAndAmountOfMovesGivenBoard(board);

            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size()))); // Nullpointer exception
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            board.isGameOverAndExit(player1.isBlack());


            //Board.simulateGame(fenString, player1, player2, amountOfMoves);
            //System.out.println(board.createFENFromBoard(board.getBoard()));

        }*/


        while (true) {
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