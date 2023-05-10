package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(false);
        Player player2 = new Player(true);
        int amountOfMoves = 5;

        Board.simulateGame("r2qk2r/pp1bp1bp/2np1np1/2pP4/2P1P3/2N2N2/PP3PPP/R1BQKB1R", player1, player2, amountOfMoves);

    }
}

    /*
    FEN-Sammlung:
    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w" - 20 Züge
    "r2qk2r/pp1bp1bp/2np1np1/2pP4/2P1P3/2N2N2/PP3PPP/R1BQKB1R w" - 38 Züge
    "6r1/p5k1/3Q4/2N5/5P2/1p6/P5KP/4qR2 w" - 40 Züge
    "r2qk2r/p1p1p1P1/1pn4b/1N1Pb3/1PB1N1nP/8/1B1PQPp1/R3K2R b Qkq" - 45 Züge
    "r1bq4/pp1p1k1p/2p2p1p/2b5/3Nr1Q1/2N1P3/PPPK1PPP/3R1B1R w" - 51 Züge
    "1k6/p1nrp3/n2p4/2p5/3PP3/2P2P2/P7/RN4K1 w" - 14 Züge
    "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R w" - 43 Züge
    "r3k2r/pp2qppp/1np2n2/2bPp1B1/B2P2Q1/2N2N2/PPP2PPP/2KR3R b" - 36 Züge

     */

