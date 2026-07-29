package io.graphite.testutil;

import io.graphite.builder.Graphs;
import io.graphite.graph.IGraph;

public final class TestGraphs {

    private TestGraphs() {
    }

    // Traversal test graphs

    public static IGraph traversalGraph() {
        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(1, 4)
                .addEdge(2, 5)
                .build();
    }

    public static IGraph disconnectedGraph() {
        return Graphs.undirected()
                .vertices(6)
                .addEdge(0, 1)
                .addEdge(2, 3)
                .build();
    }

    public static IGraph singleVertexGraph() {
        return Graphs.undirected()
                .addEdge(5, 5)
                .build();
    }

    public static IGraph inactiveVertexGraph() {
        return Graphs.undirected()
                .vertices(5)
                .addEdge(2, 3)
                .build();
    }

    // Cycle detection test graphs

    public static IGraph directedCycle() {

        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 0)
                .build();
    }

    public static IGraph directedAcyclic() {

        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .build();
    }

    public static IGraph undirectedCycle() {

        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 0)
                .build();
    }

    public static IGraph undirectedTree() {

        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .build();
    }

    public static IGraph directedDisconnectedCycle() {

        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(3, 4)
                .addEdge(4, 5)
                .addEdge(5, 3)
                .build();
    }

    public static IGraph undirectedDisconnectedCycle() {

        return Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(3, 4)
                .addEdge(4, 5)
                .addEdge(5, 3)
                .build();
    }

    //    topology test graphs
    public static IGraph dag() {

        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .build();
    }

    public static IGraph disconnectedDag() {

        return Graphs.directed()
                .vertices(6)
                .addEdge(0, 1)
                .addEdge(2, 3)
                .addEdge(4, 5)
                .build();
    }

    public static IGraph cyclicDirectedGraph() {

        return Graphs.directed()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 0)
                .build();
    }

}
