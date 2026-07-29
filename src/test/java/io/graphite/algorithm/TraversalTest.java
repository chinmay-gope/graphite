package io.graphite.algorithm;

import io.graphite.algorithm.traversal.BFS;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.IGraph;
import io.graphite.result.TraversalResult;
import io.graphite.testutil.GraphAssertions;
import io.graphite.testutil.TestGraphs;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TraversalTest {

    @Test
    void bfsTraversalGraph() {

        TraversalResult result =
                BFS.INSTANCE.traverse(
                        TestGraphs.traversalGraph(),
                        0);

        GraphAssertions.assertTraversal(
                List.of(0, 1, 2, 3, 4, 5),
                result);
    }

    @Test
    void dfsTraversalGraph() {

        TraversalResult result =
                DFS.INSTANCE.traverse(
                        TestGraphs.traversalGraph(),
                        0);

        GraphAssertions.assertStartsFrom(0, result);
        GraphAssertions.assertTraversalSize(6, result);
        GraphAssertions.assertVisitsExactly(
                result,
                0, 1, 2, 3, 4, 5);
    }

    @Test
    void bfsDisconnectedGraph() {

        TraversalResult result =
                BFS.INSTANCE.traverse(
                        TestGraphs.disconnectedGraph(),
                        0);

        GraphAssertions.assertVisitsExactly(
                result,
                0, 1);
    }

    @Test
    void dfsDisconnectedGraph() {

        TraversalResult result =
                DFS.INSTANCE.traverse(
                        TestGraphs.disconnectedGraph(),
                        0);

        GraphAssertions.assertVisitsExactly(
                result,
                0, 1);
    }

    @Test
    void bfsSingleVertex() {

        TraversalResult result =
                BFS.INSTANCE.traverse(
                        TestGraphs.singleVertexGraph(),
                        5);

        GraphAssertions.assertTraversal(
                List.of(5),
                result);
    }

    @Test
    void dfsSingleVertex() {

        TraversalResult result =
                DFS.INSTANCE.traverse(
                        TestGraphs.singleVertexGraph(),
                        5);

        GraphAssertions.assertTraversal(
                List.of(5),
                result);
    }

    @Test
    void invalidSourceThrows() {

        IGraph graph = TestGraphs.traversalGraph();

        assertThrows(
                InvalidVertexException.class,
                () -> BFS.INSTANCE.traverse(graph, 100));

        assertThrows(
                InvalidVertexException.class,
                () -> DFS.INSTANCE.traverse(graph, -1));
    }

    @Test
    void inactiveSourceThrows() {

        IGraph graph = TestGraphs.inactiveVertexGraph();

        assertThrows(
                InvalidVertexException.class,
                () -> BFS.INSTANCE.traverse(graph, 0));

        assertThrows(
                InvalidVertexException.class,
                () -> DFS.INSTANCE.traverse(graph, 0));
    }
}
