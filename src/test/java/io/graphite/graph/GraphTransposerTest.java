package io.graphite.graph;

import io.graphite.builder.Graphs;
import org.junit.jupiter.api.Test;

import static io.graphite.testutil.GraphAssertions.edgeExists;
import static org.junit.jupiter.api.Assertions.*;

class GraphTransposerTest {
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
}
