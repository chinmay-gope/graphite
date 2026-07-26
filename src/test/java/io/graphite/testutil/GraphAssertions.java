package io.graphite.testutil;

import io.graphite.graph.IGraph;
import io.graphite.result.MSTResult;
import io.graphite.result.ShortestPathResult;
import io.graphite.result.TopologicalSortResult;
import io.graphite.result.TraversalResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public final class GraphAssertions {

    private GraphAssertions() {
    }

    // ---------------------------------------------------------
    // Traversal
    // ---------------------------------------------------------

    public static void traversalEquals(
            TraversalResult actual,
            Integer... expected
    ) {

        assertIterableEquals(
                List.of(expected),
                actual.traversalOrder()
        );
    }

    // ---------------------------------------------------------
    // Topology
    // ---------------------------------------------------------

    public static void topologyEquals(
            TopologicalSortResult actual,
            Integer... expected
    ) {

        assertIterableEquals(
                List.of(expected),
                actual.order()
        );
    }

    // ---------------------------------------------------------
    // Shortest Path
    // ---------------------------------------------------------

    public static void distanceEquals(
            ShortestPathResult result,
            int vertex,
            int... expected
    ) {

        assertEquals(
                expected,
                result.distance()
        );
    }

    // ---------------------------------------------------------
    // MST
    // ---------------------------------------------------------

    public static void totalWeightEquals(
            MSTResult result,
            int expectedWeight
    ) {

        assertEquals(
                expectedWeight,
                result.cost()
        );
    }

    public static void edgeCountEquals(
            MSTResult result,
            int expected
    ) {
        assertEquals(
                expected,
                result.edges().size()
        );
    }

    public static boolean edgeExists(IGraph graph, int u, int v) {
        return graph.getNeighbors(u).stream()
                .anyMatch(e -> e.destination() == v);
    }

    public static boolean edgeExistsUndirected(IGraph graph, int u, int v) {
        return edgeExists(graph, u, v) || edgeExists(graph, v, u);
    }
}
