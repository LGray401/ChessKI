package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setBoard(board.initialize());
        board.setBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Player player1 = new Player(false);
        Player player2 = new Player(true);
        int amountOfMoves = 5;

        Board.simulateGame(board, player1, player2, amountOfMoves);

    }
}

    /*
    FEN-Sammlung:
    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    "r2qk2r/pp1bp1bp/2np1np1/2pP4/2P1P3/2N2N2/PP3PPP/R1BQKB1R"
     */

