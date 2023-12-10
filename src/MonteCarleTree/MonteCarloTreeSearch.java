package MonteCarleTree;

import Helpers.Stopwatch;
import Main.Board;

import java.util.List;

public class MonteCarloTreeSearch {

    private final int timeLimit;
    static int turns = 0;
    static int totalPlayouts = 0;

    private int maxDepth = 0;
    private double averageDepth = 0;

    public int getMaxDepth() {
        return maxDepth;
    }
    public double getAverageDepth() {
        return averageDepth;
    }
    public MonteCarloTreeSearch(int timeLimit) {

    if (timeLimit <= 0) {
        throw new IllegalArgumentException("Time limit must be positive.");
    }

    this.timeLimit = timeLimit;
    }


    Stopwatch randomPlaygetChildrenStopwatch = new Stopwatch("randomPlay getChildren");

    public Board findNextMove(Board board, boolean isBlackTurn) {
        int randomPlayouts = 0;
        long start = System.currentTimeMillis();
        long end = start + (timeLimit * 1000) - 100; // 60 seconds * 1000 ms/sec

        // Initialize root node with the current game state
        Node rootNode = new Node(new GameState(board, isBlackTurn, isBlackTurn), null, 0);

        // Main loop of MCTS algorithm
        while (System.currentTimeMillis() < end) {
            // Phase 1 - Selection
            Node promisingNode = selectPromisingNode(rootNode);

            // Phase 2 - Expansion
            if (!promisingNode.getGameState().isGameOver()) {
                promisingNode.expand();
            }
            // Phase 3 - Simulation
            Node nodeToExplore = promisingNode;
            if (!promisingNode.getChildren().isEmpty()) {
                nodeToExplore = promisingNode.getRandomChildNode();
            }
            int playoutResult = simulateRandomPlayout(nodeToExplore);
            randomPlayouts++;

            // Phase 4 - Update
            backpropagation(nodeToExplore, playoutResult);

        }

        // Selecting the best move after search is completed
        Node winnerNode = rootNode.getChildWithMaxScore();
        turns++;
        totalPlayouts += randomPlayouts;
        maxDepth = this.getMaxDepth(rootNode);
        averageDepth = this.getAverageDepth(rootNode);
        return winnerNode.getGameState().getBoard();
    }

    // Selects the most promising node to explore next based on UCT value
    public Node selectPromisingNode(Node rootNode) {
        Node node = rootNode;
        List<Node> children = node.getChildren();

        // Traverse the tree until a leaf node is reached
        while (!children.isEmpty()) {
            node = UCT.findBestNodeWithUCT(children, node.getVisitCount());
            children = node.getChildren();
        }
        return node;
    }

    public int simulateRandomPlayout(Node node) {
        Node tempNode = new Node(node.getGameState().copy(), node.getParent(), node.getDepth() +1);


        while (!tempNode.getGameState().isGameOver()) {
            tempNode.getGameState().randomPlay(randomPlaygetChildrenStopwatch);
        }

        return tempNode.getGameState().getGameResult();
    }

    public void backpropagation(Node nodeToExplore, int score) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.incrementVisit();

            tempNode.addScore(score);
            tempNode = tempNode.getParent();
        }
    }

    public int getMaxDepth(Node rootNode) {
        int maxDepth = rootNode.getDepth();
        for (Node child : rootNode.getChildren()) {
            maxDepth = Math.max(maxDepth, getMaxDepth(child));
        }
        return maxDepth;
    }

    public double getAverageDepth(Node rootNode) {
        int totalDepth = getTotalDepth(rootNode);
        int totalNodes = getTotalNodes(rootNode);
        return (double) totalDepth / totalNodes;
    }

    private int getTotalDepth(Node node) {
        int totalDepth = node.getDepth();
        for (Node child : node.getChildren()) {
            totalDepth += getTotalDepth(child);
        }
        return totalDepth;
    }

    private int getTotalNodes(Node node) {
        int totalNodes = 1; // Count this node
        for (Node child : node.getChildren()) {
            totalNodes += getTotalNodes(child);
        }
        return totalNodes;
    }
}


