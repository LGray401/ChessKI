package Tests;

import Main.Board;
import Main.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTests {

    @Test
    void opening(){

        Board board = new Board();
        Player player1 = new Player(false);
        Player player2 = new Player(true);
        board.setBoard(board.createBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));
        Assertions.assertEquals(player1.amountOfLegalMovesGivenBoard(board), 20);
    }
}
