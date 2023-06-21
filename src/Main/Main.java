package Main;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;
        String fenString = "rnbqk3/p6P/2n1p1P1/1r3p2/8/1PN1K3/P4P2/R1BQ1BNR";
        String fenTest = "rnbqk3/p6P/2n1p1P1/1r3p2/8/1PN1K3/P4P2/R1BQ1BNR";
        String fenStringStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

        //benchmark
        Benchmark benchmark = new Benchmark();
        System.out.println("Average time in milliseconds: " + benchmark.benchmarkOneMove(fenTest, 1));
        Board board = new Board();
        //board.setBoardFromFEN(fenStringStart);
        board.setBoardFromFEN(fenTest);

        //board.to2DArrayAndDisplay(board.getBoard());
        //board.isGameOverAndExit(player2.isBlack());

        //Figure nextMove = player2.makeMove(board);
        //nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));


        /*while (true) {
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

        }*/
   }
}