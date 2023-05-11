package Main;

public class Benchmark {

    Player player1 = new Player(true);
    Player player2 = new Player(false);
    Board board = new Board();

    public double benchmarkOneMove(String fenString, int amountOfRepetitions) {

        board.setBoardFromFEN(fenString);
        long totalTime = 0;

        for (int i = 0; i < amountOfRepetitions; i++) {
            long startTime = System.nanoTime();
            player1.makeMove(board);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        double averageTime = totalTime / (double) amountOfRepetitions;
        double averageTimeInMillis = averageTime / 1000000.0;
        return averageTimeInMillis;
    }

}

