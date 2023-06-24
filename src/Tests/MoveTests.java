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
        Assertions.assertEquals(20, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeN(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r2qk2r/pp1bp1bp/2np1np1/2pP4/2P1P3/2N2N2/PP3PPP/R1BQKB1R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        System.out.println(player1.getAllMovesInFenNotation());
        Assertions.assertEquals(38, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeN(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "6r1/p5k1/3Q4/2N5/5P2/1p6/P5KP/4qR2";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(42, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeL(){ //Rochade

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "r2qk2r/p1p1p1P1/1pn4b/1N1Pb3/1PB1N1nP/8/1B1PQPp1/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(40, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeL(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r1bq4/pp1p1k1p/2p2p1p/2b5/3Nr1Q1/2N1P3/PPPK1PPP/3R1B1R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(51, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeO(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "1k6/p1nrp3/n2p4/2p5/3PP3/2P2P2/P7/RN4K1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        System.out.println(player1.getAllMovesInFenNotation());
        Assertions.assertEquals(14, player1.amountOfLegalMovesGivenBoard(board));
    }

    //@Test
    void stellung1GruppeJ(){ //mit en pessant

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(43, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeJ(){ //mit rochade

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "r3k2r/pp2qppp/1np2n2/2bPp1B1/B2P2Q1/2N2N2/PPP2PPP/2KR3R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(36, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeH(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "5r2/p2Qb2p/3n1p2/3P2k1/1PB5/8/1B5P/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(27, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeH(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "4k2r/r2n1pbp/3B2p1/p1p3P1/2p4P/7B/PP2K3/1R4NR";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(34, player1.amountOfLegalMovesGivenBoard(board));
    }

    //@Test
    void stellung1GruppeAB(){ //3 promotions für pawn fehlen

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "rnbqk3/p6P/2n1p1P1/1r3p2/8/1PN1K3/P4P2/R1BQ1BNR";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(50, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeAB(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "rnb1kbnr/p6p/Bp4p1/8/Q4p2/N2qBN2/PP3PPP/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(9, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeAF(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "8/8/4kpp1/3p4/p6P/2B4b/6P1/6K1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(17, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeAF(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "5rk1/pp4pp/4p3/2R3Q1/3n4/6qr/P1P2PPP/5RK1";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(41, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeK(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "4K3/PP3PP1/8/8/1p2n1P1/7R/3k4/2R5";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(37, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeK(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "R3K2R/P5PP/2P3QN/7k/6p1/2n4n/p5pp/7r";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(2, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeT(){

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r1bqk1nr/ppp2ppp/2n1p3/3p4/1b1P1B2/4PN2/PPP2PPP/RN1QKB1R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(6, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeT(){

        Board board = new Board();
        Player player1 = new Player(true);
        String fenString = "8/8/4k3/3pP3/3K4/8/8/8";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(4, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung1GruppeU(){ //1x Rochade

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = " r1bq1rk1/ppp2ppp/2np1n2/3p4/2PP4/2NBPN2/PP3PPP/R1BQK2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(43, player1.amountOfLegalMovesGivenBoard(board));
    }

    @Test
    void stellung2GruppeU(){ //2x rochade

        Board board = new Board();
        Player player1 = new Player(false);
        String fenString = "r4rk1/1pp1q1pp/2np1pn1/p3p3/2PPP3/2N1BP2/PPQ2P1P/R3K2R";
        board.setBoardFromFEN(fenString);
        player1.setFigureAndMovesListForPlayerGivenBoard(board);
        Assertions.assertEquals(40, player1.amountOfLegalMovesGivenBoard(board));
    }







}
