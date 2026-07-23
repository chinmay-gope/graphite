package io.graphite.algorithm;

import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.IGraph;
import io.graphite.result.TraversalResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void shouldRejectInactiveSource() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.traversal().bfs(0)
        );
    }

    @Test
    void shouldTraverseConnectedVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        TraversalResult result = graph.traversal().bfs(2);

        assertEquals(List.of(2, 4), result.traversalOrder());
    }

    @Test
    void shouldTraverseSingleActiveVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(5, 5)
                .build();

        TraversalResult result = graph.traversal().bfs(5);

        assertEquals(List.of(5), result.traversalOrder());
    }
}
