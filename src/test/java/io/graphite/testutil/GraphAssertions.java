package io.graphite.testutil;

import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TraversalResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public final class GraphAssertions {

    private GraphAssertions() {
    }

    public static void assertTraversal(
            List<Integer> expected,
            TraversalResult actual) {

        assertEquals(expected, actual.traversalOrder());
    }

    public static void assertVisitsExactly(
            TraversalResult result,
            Integer... vertices) {

        assertEquals(
                Set.of(vertices),
                new HashSet<>(result.traversalOrder())
        );
    }

    public static void assertTraversalSize(
            int expected,
            TraversalResult result) {

        assertEquals(expected,
                result.traversalOrder().size());
    }

    public static void assertStartsFrom(
            int source,
            TraversalResult result) {

        assertEquals(source,
                result.traversalOrder().getFirst());
    }

    public static void assertTopologicalOrder(IGraph graph, List<Integer> order) {

        assertNotNull(order);

        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < order.size(); i++) {
            Integer vertex = order.get(i);

            assertTrue(
                    position.put(vertex, i) == null,
                    "Duplicate vertex in ordering: " + vertex
            );
        }

        int activeVertices = graph.activeVertexCount();

        assertEquals(
                activeVertices,
                order.size(),
                "Ordering does not contain all active vertices."
        );

        for (Edge edge : graph.getEdges()) {

            int sourcePos = position.get(edge.source());
            int destinationPos = position.get(edge.destination());

            assertTrue(
                    sourcePos < destinationPos,
                    () -> "Invalid topological order: "
                            + edge.source() + " appears after "
                            + edge.destination()
            );
        }
    }
}
