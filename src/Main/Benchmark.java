package Main;

import Figures.Figure;

public class Benchmark {

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    Player player1 = new Player(true);
    Player player2 = new Player(false);
    Board board = new Board();

    public double benchmarkOneMove(String fenString, int amountOfRepetitions) {

        board.setBoardFromFEN(fenString);
        long totalTime = 0;

        for (int i = 0; i < amountOfRepetitions; i++) {
            long startTime = System.nanoTime();
            player1.makeMove(this.getBoard());
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        double averageTime = totalTime / (double) amountOfRepetitions;
        double averageTimeInMillis = averageTime / 1000000.0;
        return averageTimeInMillis;
    }

    public String benchmarkMinmax(String fenString, int amountOfRepetitions) {

        board.setBoardFromFEN(fenString);
        long totalTime = 0;
        long startTime = System.nanoTime();
        Figure bestMove = null;
        for (int i = 0; i < amountOfRepetitions; i++) {
            bestMove =  player1.findBestMove(this.getBoard());
        }
        long endTime = System.nanoTime();
        totalTime += (endTime - startTime);
        double averageTime = totalTime / (double) amountOfRepetitions;
        double averageTimeInMillis = averageTime / 1000000.0;
        System.out.println("Minmax Benchmark");
        System.out.println("Average time in milliseconds: " + averageTimeInMillis);
        System.out.println("Best Move: " + bestMove.getClass().getSimpleName() + " from: " + bestMove.getPosition() + " to: " + bestMove.getNextPosition());
        System.out.println("Examined Positions: " + player1.getExaminedPositions());
        return "Average time in milliseconds: " + averageTimeInMillis;
    }

}

