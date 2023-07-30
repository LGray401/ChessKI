package MonteCarleTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Node {
    private GameState gameState;
    private Node parent;
    private List<Node> children;
    private int visitCount;
    private double winScore;
    private int depth;


    public Node(GameState gameState, Node parent, int depth) {
        this.gameState = gameState;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.visitCount = 0;
        this.winScore = 0;
        this.depth = depth;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public double getWinScore() {
        return winScore;
    }
    public int getDepth() {
        return depth;
    }
    public void incrementVisit() {
        visitCount++;
    }

    public void addScore(double score) {
        winScore += score;
    }

    public void expand() {
        for (GameState childGameState : gameState.getChildren()) {
            children.add(new Node(childGameState, this, depth + 1));
        }
    }

    public Node getRandomChildNode() {
        int index = (int) (Math.random() * children.size());
        return children.get(index);
    }

    public Node getChildWithMaxScore() {
        return children.stream().max(Comparator.comparing(c -> c.winScore)).orElse(null);
    }
}


