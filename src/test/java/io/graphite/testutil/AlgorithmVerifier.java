package io.graphite.testutil;

import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TopologicalSortResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class AlgorithmVerifier {

    private AlgorithmVerifier() {
    }

    public static void verifyTopologicalOrder(
            IGraph graph,
            TopologicalSortResult result) {

        List<Integer> order = result.order();

        assertEquals(
                graph.activeVertexCount(),
                order.size(),
                "Topological ordering missed vertices.");

        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < order.size(); i++) {
            position.put(order.get(i), i);
        }

        for (Edge edge : graph.getEdges()) {

            int source = edge.source();
            int destination = edge.destination();

            assertTrue(
                    position.get(source) < position.get(destination),
                    () -> "Invalid topological order: "
                            + source + " -> " + destination);
        }
    }
}
