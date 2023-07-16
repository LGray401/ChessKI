package MonteCarleTree;

import java.util.Comparator;
import java.util.List;

public class UCT {

    private static final double EXPLORATION_CONSTANT = Math.sqrt(2);

    public static Node findBestNodeWithUCT(List<Node> children, int parentVisit) {
        return children.stream()
                .max(Comparator.comparing(c -> uctValue(parentVisit, c.getWinScore(), c.getVisitCount())))
                .orElseThrow(() -> new RuntimeException("No children nodes found"));
    }

    private static double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {
        if (nodeVisit == 0) {
            return Integer.MAX_VALUE;
        }
        return (nodeWinScore / (double) nodeVisit)
                + EXPLORATION_CONSTANT * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
    }
}

