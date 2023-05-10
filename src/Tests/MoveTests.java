package Tests;

import Main.Board;
import Main.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTests {

   @Test
    void startstellug(){

        Board board = new Board();
        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        //boolean isBlack = (fenString.substring(fenString.length() - 1).equals('w')) ? false : true;
        Player player1 = new Player(false);
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 20);
    }

    @Test
    void stellung1GruppeN(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r2qk2r/pp1bp1bp/2np1np1/2pP4/2P1P3/2N2N2/PP3PPP/R1BQKB1R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 38);
    }

    @Test
    void stellung2GruppeN(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "6r1/p5k1/3Q4/2N5/5P2/1p6/P5KP/4qR2";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 40);
    }

    @Test
    void stellung1GruppeL(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "r2qk2r/p1p1p1P1/1pn4b/1N1Pb3/1PB1N1nP/8/1B1PQPp1/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 45);
    }

    @Test
    void stellung2GruppeL(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r1bq4/pp1p1k1p/2p2p1p/2b5/3Nr1Q1/2N1P3/PPPK1PPP/3R1B1R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 51);
    }

    @Test
    void stellung1GruppeO(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "1k6/p1nrp3/n2p4/2p5/3PP3/2P2P2/P7/RN4K1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 14);
    }

    @Test
    void stellung1GruppeJ(){ //mit en pessant

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 43);
    }

    @Test
    void stellung2GruppeJ(){ //mit rochade

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "r3k2r/pp2qppp/1np2n2/2bPp1B1/B2P2Q1/2N2N2/PPP2PPP/2KR3R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 36);
    }

    @Test
    void stellung1GruppeH(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "5r2/p2Qb2p/3n1p2/3P2k1/1PB5/8/1B5P/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 29);
    }

    @Test
    void stellung2GruppeH(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "4k2r/r2n1pbp/3B2p1/p1p3P1/2p4P/7B/PP2K3/1R4NR";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 34);
    }

    @Test
    void stellung1GruppeAB(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "rnbqk3/p6P/2n1p1P1/1r3p2/8/1PN1K3/P4P2/R1BQ1BNR";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 50);
    }

    @Test
    void stellung2GruppeAB(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "rnb1kbnr/p6p/Bp4p1/8/Q4p2/N2qBN2/PP3PPP/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 9);
    }

    @Test
    void stellung1GruppeAF(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "8/8/4kpp1/3p4/p6P/2B4b/6P1/6K1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 17);
    }

    @Test
    void stellung2GruppeAF(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "5rk1/pp4pp/4p3/2R3Q1/3n4/6qr/P1P2PPP/5RK1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 41);
    }

    @Test
    void stellung1GruppeK(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "4K3/PP3PP1/8/8/1p2n1P1/7R/3k4/2R5";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 41);
    }

    @Test
    void stellung2GruppeK(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "R3K2R/P5PP/2P3QN/7k/6p1/2n4n/p5pp/7r";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 1);
    }
}
