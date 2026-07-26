package io.graphite.testutil;

import io.graphite.builder.Graphs;
import io.graphite.graph.IGraph;

public final class TestGraphs {

    private TestGraphs() {
    }

    public static IGraph traversal() {
        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(2, 4)
                .addEdge(4, 5)
                .build();
    }

    public static IGraph weighted() {
        return Graphs.undirected()
                .weighted(true)
                .addEdge(0, 1, 4)
                .addEdge(0, 2, 2)
                .addEdge(1, 2, 1)
                .addEdge(1, 3, 5)
                .addEdge(2, 3, 8)
                .addEdge(2, 4, 10)
                .addEdge(3, 4, 2)
                .build();
    }

    public static IGraph dag() {
        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .build();
    }

    public static IGraph directedCycle() {
        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 0)
                .build();
    }

    public static IGraph tree() {
        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(1, 4)
                .addEdge(2, 5)
                .build();
    }

    public static IGraph disconnected() {
        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(2, 3)
                .build();
    }

    public static IGraph singleVertex() {
        return Graphs.undirected()
                .addEdge(0, 0)
                .build();
    }

    public static IGraph empty() {
        return Graphs.undirected()
                .vertices(0)
                .build();
    }
}
