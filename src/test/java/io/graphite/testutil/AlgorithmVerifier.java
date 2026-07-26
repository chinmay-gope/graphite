package io.graphite.testutil;

import io.graphite.graph.IGraph;
import io.graphite.result.MSTResult;
import io.graphite.result.ShortestPathResult;
import io.graphite.result.TopologicalSortResult;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class AlgorithmVerifier {

    private AlgorithmVerifier() {
    }

    // ---------------------------------------------------------
    // Shortest Paths
    // ---------------------------------------------------------

    public static void verifyShortestPaths(
            IGraph graph,
            int source
    ) {

        ShortestPathResult dijkstra =
                graph.shortestPath().dijkstra(source);

        ShortestPathResult bellman =
                graph.shortestPath().bellmanFord(source);

        assertArrayEquals(
                bellman.distance(),
                dijkstra.distance(),
                "Dijkstra and Bellman-Ford disagree."
        );
    }

    // ---------------------------------------------------------
    // MST
    // ---------------------------------------------------------

    public static void verifyMST(
            IGraph graph
    ) {

        MSTResult prim =
                graph.mst().prim(0);

        MSTResult kruskal =
                graph.mst().kruskal();

        assertEquals(
                prim.cost(),
                kruskal.cost(),
                "Prim and Kruskal produced different MST costs."
        );

        assertEquals(
                prim.edges().size(),
                kruskal.edges().size(),
                "Prim and Kruskal produced different edge counts."
        );
    }

    // ---------------------------------------------------------
    // Topology
    // ---------------------------------------------------------

    public static void verifyTopology(
            IGraph graph
    ) {

        TopologicalSortResult dfs =
                graph.topology().dfs();

        TopologicalSortResult kahn =
                graph.topology().kahn();

        assertEquals(
                dfs.order().size(),
                kahn.order().size(),
                "Topological ordering size mismatch."
        );
    }
}
