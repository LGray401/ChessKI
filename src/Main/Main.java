package Main;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;
        String fenString = "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R";
        //Benchmark benchmark = new Benchmark();
        //System.out.println(benchmark.benchmarkOneMove(fenString, 100000) );

        Board.simulateGame(fenString, player1, player2, amountOfMoves);

    }
}