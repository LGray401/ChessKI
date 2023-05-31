package Main;

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
            player1.makeAlphaBeta(this.getBoard());
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        double averageTime = totalTime / (double) amountOfRepetitions;
        double averageTimeInMillis = averageTime / 1000000.0;
        return averageTimeInMillis;
    }

}

