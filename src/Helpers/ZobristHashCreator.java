package Helpers;

import Figures.Figure;

import java.util.Random;

public class ZobristHashCreator {

    private static final long[][] zobristTable = new long[12][64];
    private static final long[] zobristTurn = new long[2];

    static {
        Random rand = new Random();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 64; j++) {
                zobristTable[i][j] = rand.nextLong();
            }
        }

        zobristTurn[0] = rand.nextLong();
        zobristTurn[1] = rand.nextLong();
    }

    public static long calculateZobristHash(Figure[] board, boolean isBlack) {
        long hash = 0;

        for (int i = 0; i < board.length; i++) {
            Figure piece = board[i];
            if (!piece.isEmptyField()) {
                int pieceType = piece.getTypeAsInt(); // This should return a value from 0-5 representing the type of the piece (Pawn, Rook, Knight, Bishop, Queen, King)
                int color = piece.isBlack() == true ? 0 : 6;

                hash ^= zobristTable[pieceType + color][i];
            }
        }

        // Include the turn in the hash
        int turn = isBlack ? 1 : 0;
        hash ^= zobristTurn[turn];

        return hash;
    }


}
