package io.graphite.algorithm;

import io.graphite.algorithm.cycle.DirectedCycleDetector;
import io.graphite.algorithm.cycle.UndirectedCycleDetector;
import io.graphite.builder.Graphs;
import io.graphite.exception.graph.GraphEmptyException;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import io.graphite.testutil.TestGraphs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleDetectionTest {
    @Test
    void directedCycleShouldBeDetected() {

        assertTrue(
                DirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.directedCycle()));
    }

    @Test
    void directedAcyclicGraphShouldNotContainCycle() {

        assertFalse(
                DirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.directedAcyclic()));
    }

    @Test
    void undirectedCycleShouldBeDetected() {

        assertTrue(
                UndirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.undirectedCycle()));
    }

    @Test
    void undirectedTreeShouldNotContainCycle() {

        assertFalse(
                UndirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.undirectedTree()));
    }

    @Test
    void directedDisconnectedGraphWithCycle() {

        assertTrue(
                DirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.directedDisconnectedCycle()));
    }

    @Test
    void undirectedDisconnectedGraphWithCycle() {

        assertTrue(
                UndirectedCycleDetector.INSTANCE
                        .hasCycle(TestGraphs.undirectedDisconnectedCycle()));
    }

    @Test
    void directedSelfLoopIsCycle() {

        IGraph graph = Graphs.directed()
                .addEdge(5, 5)
                .build();

        assertTrue(
                DirectedCycleDetector.INSTANCE.hasCycle(graph));
    }

    @Test
    void undirectedSelfLoopIsCycle() {

        IGraph graph = Graphs.undirected()
                .addEdge(5, 5)
                .build();

        assertTrue(
                UndirectedCycleDetector.INSTANCE.hasCycle(graph));
    }

    @Test
    void nullGraphThrows() {

        assertThrows(
                GraphEmptyException.class,
                () -> DirectedCycleDetector.INSTANCE.hasCycle(null));
    }

    @Test
    void emptyGraphThrows() {

        assertThrows(
                InvalidGraphConfigurationException.class,
                () -> Graphs.directed()
                        .vertices(0)
                        .build());
    }
}
