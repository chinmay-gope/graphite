package io.graphite.graph;

import io.graphite.builder.Graphs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyAndTransposeTest {
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

    // Transposed graph tests

    @Test
    void shouldTransposeDirectedGraph() {
        IGraph graph = Graphs.directed()
                .vertices(4)
                .weighted(true)
                .addEdge(0, 1, 5)
                .addEdge(1, 2, 7)
                .addEdge(2, 3, 9)
                .build();

        IGraph transpose = graph.transposed();

        assertTrue(edgeExists(transpose, 1, 0));
        assertTrue(edgeExists(transpose, 2, 1));
        assertTrue(edgeExists(transpose, 3, 2));

        assertFalse(edgeExists(transpose, 0, 1));
        assertFalse(edgeExists(transpose, 1, 2));
    }

    @Test
    void transposeOfTransposeShouldEqualOriginal() {
        IGraph graph = Graphs.directed()
                .vertices(4)
                .weighted(true)
                .addEdge(0, 1, 4)
                .addEdge(2, 3, 7)
                .build();

        IGraph restored =
                graph.transposed().transposed();

        assertEquals(graph.getEdges(), restored.getEdges());
    }

    @Test
    void transposeOfUndirectedShouldBeCopy() {
        IGraph graph = Graphs.undirected()
                .vertices(4)
                .addEdge(0, 1)
                .addEdge(2, 3)
                .build();

        IGraph transpose = graph.transposed();

        assertEquals(graph.getEdges(), transpose.getEdges());
    }

    @Test
    void transposeShouldNotModifyOriginal() {
        IGraph graph = Graphs.directed()
                .vertices(3)
                .addEdge(0, 1)
                .build();

        graph.transposed();

        assertTrue(edgeExists(graph, 0, 1));
        assertFalse(edgeExists(graph, 1, 0));
    }


    private static boolean edgeExists(IGraph graph, int u, int v) {
        return graph.getNeighbors(u).stream()
                .anyMatch(e -> e.destination() == v);
    }

}
