package MonteCarleTree;

import Helpers.Stopwatch;
import Main.Board;
import Main.EndOfGame;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Board board;
    private boolean isBlackTurn;

    private boolean isBlackPlaying;

    public GameState(Board board, boolean isBlackTurn, boolean isBlackPlaying) {
        this.board = board;
        this.isBlackTurn = isBlackTurn;
        this.isBlackPlaying = isBlackPlaying;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }

    public GameState copy() {
        return new GameState(board.copy(), isBlackTurn, isBlackPlaying);
    }

    public List<GameState> getChildren() {
        List<GameState> children = new ArrayList<>();
        for (Board childBoard : board.getChildren(isBlackTurn)) {
            children.add(new GameState(childBoard, !isBlackTurn, isBlackPlaying));
        }
        return children;
    }

    public boolean isGameOver() {

        boolean b = board.isGameOver(isBlackTurn).isGameFinished();

        return b;
    }

    public int getGameResult() {
        //Logik lässt kein Draw zu weil in EndOfGame nicht definiert
        EndOfGame endOfGame = board.isGameOver(isBlackTurn);
        if (endOfGame.isGameFinished()) {
            return endOfGame.getScore(isBlackPlaying);
        } else {
            return 0; // Draw
        }
    }

    public void randomPlay(Stopwatch stopwatch) {
        //geChildren ist rechenintensiv, evtl. durch simulateGame aus Board ersetzten
        stopwatch.start();
        List<Board> children = board.getChildren(isBlackTurn);
        stopwatch.stop();
        int index = (int) (Math.random() * children.size());
        board = children.get(index);
        isBlackTurn = !isBlackTurn;
    }
}

