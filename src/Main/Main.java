package Main;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;

        String startgame = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String midgame = "rnbqk3/p6P/2n1p1P1/1r3p2/8/1PN1K3/P4P2/R1BQ1BNR";
        String endgame = "6r1/p5k1/3Q4/2N5/5P2/1p6/P5KP/4qR2";
        String fen = "k7/8/8/8/8/8/7K/8";
        //benchmark
        Benchmark benchmark = new Benchmark();
        //benchmark.benchmarkNegaMax(endgame, 1);
        //benchmark.benchmarkAlphaBeta(fenStringStart, 10);
        //benchmark.benchmarkMinmax(fenStringStart, 1);

        Board board = new Board();
        board.setBoardFromFEN(startgame);
        //player1.setFigureAndMovesListForPlayerGivenBoard(board);
        //player2.setFigureAndMovesListForPlayerGivenBoard(board);
        while(true) {

            board = player1.makeTurnMCTS(board); // black (lower case) wins
            //System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            //board.changeBoard(nextMove);
            //System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            System.out.println("---------------------------------------------------");
            board.isGameOverAndExit(player2.isBlack());

            board = player2.makeTurnMCTS(board);
            //nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            //System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            //board.changeBoard(nextMove);
            //System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            board.to2DArrayAndDisplay(board.getBoard());
            System.out.println("---------------------------------------------------");
            board.isGameOverAndExit(player1.isBlack());

        }


    }
}