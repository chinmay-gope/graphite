package io.graphite.graph;

import io.graphite.builder.Graphs;
import org.junit.jupiter.api.Test;

import static io.graphite.testutil.GraphAssertions.edgeExists;
import static org.junit.jupiter.api.Assertions.*;

class GraphCopierTest {
    @Test
    void shouldCopyDirectedGraph() {
        IGraph graph = Graphs.directed()
                .vertices(4)
                .weighted(true)
                .addEdge(0, 1, 5)
                .addEdge(1, 2, 7)
                .addEdge(2, 3, 3)
                .build();

        IGraph copy = graph.copy();

        assertNotSame(graph, copy);

        assertEquals(graph.getVertices(), copy.getVertices());
        assertEquals(graph.edgeCount(), copy.edgeCount());

        assertEquals(graph.getEdges(), copy.getEdges());
    }

    @Test
    void modifyingCopyShouldNotAffectOriginal() {
        IGraph graph = Graphs.directed()
                .vertices(3)
                .addEdge(0, 1)
                .build();

        IGraph copy = graph.copy();

        copy.addEdge(1, 2);

        assertFalse(edgeExists(graph, 1, 2));
        assertTrue(edgeExists(copy, 1, 2));
    }

    @Test
    void shouldCopyEmptyGraph() {
        IGraph graph = Graphs.directed()
                .vertices(5)
                .build();

        IGraph copy = graph.copy();

        assertEquals(5, copy.getVertices());
        assertEquals(0, copy.edgeCount());
    }

    @Test
    void shouldCopyUndirectedGraph() {
        IGraph graph = Graphs.undirected()
                .vertices(3)
                .addEdge(0, 1)
                .addEdge(1, 2)
                .build();

        IGraph copy = graph.copy();

        assertEquals(graph.edgeCount(), copy.edgeCount());
        assertEquals(graph.getEdges(), copy.getEdges());
    }
}
