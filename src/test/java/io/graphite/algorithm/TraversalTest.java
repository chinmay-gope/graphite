package io.graphite.algorithm;

import io.graphite.algorithm.traversal.BFS;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.IGraph;
import io.graphite.result.TraversalResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TraversalTest {

    @Test
    void bfsAndDfsShouldRejectInactiveSource() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.traversal().bfs(0)
        );

        assertThrows(
                InvalidVertexException.class,
                () -> graph.traversal().dfs(0)
        );
    }

    @Test
    void bfsAndDfsShouldTraverseConnectedVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 2);
        assertEquals(List.of(2, 4), result.traversalOrder());

        result = DFS.INSTANCE.traverse(graph, 2);
        assertEquals(List.of(2, 4), result.traversalOrder());
    }

    @Test
    void bfsAndDfsShouldTraverseSingleActiveVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(5, 5)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 5);
        assertEquals(List.of(5), result.traversalOrder());

        result = DFS.INSTANCE.traverse(graph, 5);
        assertEquals(List.of(5), result.traversalOrder());
    }

    @Test
    void bfsAndDfsDisconnectedGraph() {

        IGraph graph = Graphs.undirected()
                .vertices(4)
                .addEdge(0, 1)
                .build();

        TraversalResult result =
                BFS.INSTANCE.traverse(graph, 0);
        assertEquals(List.of(0, 1), result.traversalOrder());

        result =
                DFS.INSTANCE.traverse(graph, 0);

        assertEquals(List.of(0, 1), result.traversalOrder());
    }

    @Test
    void bfsAndDfsInvalidSourceThrows() {

        IGraph graph = Graphs.undirected()
                .vertices(5)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> BFS.INSTANCE.traverse(graph, 10)
        );

        assertThrows(
                InvalidVertexException.class,
                () -> DFS.INSTANCE.traverse(graph, -1)
        );
    }

    @Test
    void bfsAndDfsInactiveSourceThrows() {

        IGraph graph = Graphs.undirected()
                .vertices(5)
                .addEdge(2, 3)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> BFS.INSTANCE.traverse(graph, 0)
        );
        assertThrows(
                InvalidVertexException.class,
                () -> DFS.INSTANCE.traverse(graph, 0)
        );
    }

    @Test
    void bfsAndDfsEmptyGraphConstructionThrows() {
        assertThrows(
                InvalidGraphConfigurationException.class,
                () -> Graphs.undirected()
                        .vertices(0)
                        .build()
        );
    }

    @Test
    void bfsAndDfsSelfLoop() {
        IGraph graph = Graphs.undirected()
                .addEdge(0, 0)
                .build();

        assertEquals(List.of(0), BFS.INSTANCE.traverse(graph, 0).traversalOrder());
        assertEquals(List.of(0), DFS.INSTANCE.traverse(graph, 0).traversalOrder());
    }

    @Test
    void bfsAndDfsChainGraph() {

        IGraph graph = Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 0);
        assertEquals(List.of(0, 1, 2, 3, 4), result.traversalOrder());

        result = DFS.INSTANCE.traverse(graph, 0);
        assertEquals(List.of(0, 1, 2, 3, 4), result.traversalOrder());
    }

    @Test
    void bfsAndDfsCycleGraph() {

        IGraph graph = Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 0)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 0);
        assertEquals(List.of(0, 1, 2), result.traversalOrder());

        result = DFS.INSTANCE.traverse(graph, 0);
        assertEquals(List.of(0, 1, 2), result.traversalOrder());
    }

    @Test
    void bfsAndDfsLargerConnectedGraph() {

        IGraph graph = Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(1, 4)
                .addEdge(2, 5)
                .addEdge(2, 6)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 0);

        assertEquals(
                List.of(0, 1, 2, 3, 4, 5, 6),
                result.traversalOrder()
        );

        result = DFS.INSTANCE.traverse(graph, 0);

        assertEquals(
                List.of(0, 1, 3, 4, 2, 5, 6),
                result.traversalOrder()
        );
    }

    @Test
    void bfsAndDfsFromMiddleVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .build();

        TraversalResult result = BFS.INSTANCE.traverse(graph, 2);

        assertEquals(
                List.of(2, 1, 3, 0, 4),
                result.traversalOrder()
        );

        result = DFS.INSTANCE.traverse(graph, 2);

        assertEquals(
                List.of(2, 1, 0, 3, 4),
                result.traversalOrder()
        );
    }
}
