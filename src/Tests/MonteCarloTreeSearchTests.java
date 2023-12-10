package Tests;
import Main.Board;
import MonteCarleTree.GameState;
import MonteCarleTree.Node;
import org.junit.jupiter.api.Test;
import MonteCarleTree.MonteCarloTreeSearch;
import static Tests.NodeTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class MonteCarloTreeSearchTests {

    @Test
    void constructor_ValidTimeLimit_CreatesInstance() {
        assertDoesNotThrow(() -> new MonteCarloTreeSearch(10));
    }

    @Test
    void constructor_InvalidTimeLimit_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MonteCarloTreeSearch(-1));
    }

    @Test
    void findNextMove_ReturnsValidMove() {
        String startgame = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Board mockBoard = new Board();
        mockBoard.setBoardFromFEN(startgame);
        MonteCarloTreeSearch search = new MonteCarloTreeSearch(10);
        assertNotNull(search.findNextMove(mockBoard, true));
    }


    @Test
    void findNextMove_CompletesWithinTimeLimit() {
        String startgame = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Board mockBoard = new Board();
        mockBoard.setBoardFromFEN(startgame);

        // Set up mockBoard as needed
        MonteCarloTreeSearch search = new MonteCarloTreeSearch(1);
        long startTime = System.currentTimeMillis();
        search.findNextMove(mockBoard, true);
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 1000);
    }


    @Test
    void simulateRandomPlayout_ReturnsExpectedResult() {
        String startgame = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Board mockBoard = new Board();
        mockBoard.setBoardFromFEN(startgame);
        GameState testGamestate = new GameState(mockBoard, true, true);
        Node testNode = new Node(testGamestate, null, 0);

        MonteCarloTreeSearch search = new MonteCarloTreeSearch(10);
        int result = search.simulateRandomPlayout(testNode);
        assertTrue(result == 1 || result == 0 || result == -1);
    }

    @Test
    void backpropagation_VisitCount() {
        Node testNode = createSampleNodeTreeLast("Start");
        MonteCarloTreeSearch search = new MonteCarloTreeSearch(10);
        search.backpropagation(testNode, 1);
        assertEquals(1, testNode.getVisitCount());

    }

    @Test
    void backpropagation_UpdatesScore() {
        Node testNode = createSampleNodeTreeLast("Start");
        MonteCarloTreeSearch search = new MonteCarloTreeSearch(10);
        search.backpropagation(testNode, 1);
        assertEquals(1, testNode.getWinScore());

    }






}
