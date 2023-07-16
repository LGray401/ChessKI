package MonteCarleTree;

import Helpers.Stopwatch;
import Main.Board;

import java.util.List;

public class MonteCarloTreeSearch {
    private static final int WIN_SCORE = 1;
    private int timeLimit;
    static int turns = 0;
    static int totalPlayouts = 0;

    public MonteCarloTreeSearch(int timeLimit) {
    this.timeLimit = timeLimit;
    }


    Stopwatch selectionStopwatch = new Stopwatch("Selection");
    Stopwatch expansionStopwatch = new Stopwatch("Expansion");
    Stopwatch simulationStopwatch = new Stopwatch("Simulation");
    Stopwatch updateStopwatch = new Stopwatch("Update");
    Stopwatch isGameOverStopwatch = new Stopwatch("isGameOver");
    Stopwatch randomPlayoutStopwatch = new Stopwatch("Random Playout");
    Stopwatch randomPlaygetChildrenStopwatch = new Stopwatch("randomPlay getChildren");
    public Board findNextMove(Board board, boolean isBlackTurn) {
        int randomPlayouts = 0;
        long start = System.currentTimeMillis();
        long end = start + timeLimit * 1000; // 60 seconds * 1000 ms/sec

        Node rootNode = new Node(new GameState(board, isBlackTurn, isBlackTurn), null);

        while (System.currentTimeMillis() < end) {
            // Phase 1 - Selection
            //selectionStopwatch.start();
            Node promisingNode = selectPromisingNode(rootNode);
            //selectionStopwatch.stop();

            //expansionStopwatch.start();
            // Phase 2 - Expansion
            if (!promisingNode.getGameState().isGameOver(new Stopwatch("soos"))) {
                promisingNode.expand();
            }
            //expansionStopwatch.stop();

            simulationStopwatch.start();
            // Phase 3 - Simulation
            Node nodeToExplore = promisingNode;
            if (promisingNode.getChildren().size() > 0) {
                nodeToExplore = promisingNode.getRandomChildNode();
            }

            randomPlayoutStopwatch.start();
            int playoutResult = simulateRandomPlayout(nodeToExplore);
            randomPlayouts++;
            randomPlayoutStopwatch.stop();
            simulationStopwatch.stop();

            //updateStopwatch.start();
            // Phase 4 - Update
            backpropagation(nodeToExplore, playoutResult);
           //updateStopwatch.stop();
        }

        Node winnerNode = rootNode.getChildWithMaxScore();
        turns++;
        totalPlayouts += randomPlayouts;
        //System.out.println("Random playouts: " + randomPlayouts);
        //System.out.println("Number of turns: " + turns);
        //System.out.println("Average playouts per turn: " + (double) totalPlayouts / turns);
        //System.out.println("Number of random playouts: " + numberOfRandomPlayouts);
        isGameOverStopwatch.cumulativeElapsedTime();
        randomPlayoutStopwatch.cumulativeElapsedTime();
        randomPlaygetChildrenStopwatch.cumulativeElapsedTime();
        //selectionStopwatch.cumulativeElapsedTime();
        //expansionStopwatch.cumulativeElapsedTime();
        simulationStopwatch.cumulativeElapsedTime();
        //updateStopwatch.cumulativeElapsedTime();
        return winnerNode.getGameState().getBoard();
    }

    private Node selectPromisingNode(Node rootNode) {
        Node node = rootNode;
        List<Node> children = node.getChildren();

        while (children.size() != 0) {
            node = UCT.findBestNodeWithUCT(children, node.getVisitCount());
            children = node.getChildren();
        }
        return node;
    }

    int numberOfRandomPlayouts = 0;
    private int simulateRandomPlayout(Node node) {
        Node tempNode = new Node(node.getGameState().copy(), node.getParent());


        while (!tempNode.getGameState().isGameOver(isGameOverStopwatch)) {
            tempNode.getGameState().randomPlay(randomPlaygetChildrenStopwatch);
            numberOfRandomPlayouts++;
        }

        return tempNode.getGameState().getGameResult();
    }

    private void backpropagation(Node nodeToExplore, int score) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.incrementVisit();

            tempNode.addScore(score);
            tempNode = tempNode.getParent();
        }
    }
}


