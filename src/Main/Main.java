package Main;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(false);
        Player player2 = new Player(true);
        int amountOfMoves = 1;
        String fenString = "r1bq1rk1/ppp2ppp/2np1n2/3p4/2PP4/2NBPN2/PP3PPP/R1BQK2R";
        //Benchmark benchmark = new Benchmark();
        //System.out.println(benchmark.benchmarkOneMove(fenString, 100000) );

        Board.simulateGame(fenString, player1, player2, amountOfMoves);

    }
}