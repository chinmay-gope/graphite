package io.graphite.algorithm;

import io.graphite.algorithm.topology.DFSTopologicalSort;
import io.graphite.algorithm.topology.KahnTopologicalSort;
import io.graphite.builder.Graphs;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.exception.graph.GraphEmptyException;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import io.graphite.result.TopologicalSortResult;
import io.graphite.testutil.AlgorithmVerifier;
import io.graphite.testutil.TestGraphs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TopologicalSortTest {

    @Test
    void dfsTopologicalSortShouldProduceValidOrder() {

        TopologicalSortResult result =
                DFSTopologicalSort.INSTANCE.sort(
                        TestGraphs.dag());

        AlgorithmVerifier.verifyTopologicalOrder(
                TestGraphs.dag(),
                result);
    }

    @Test
    void kahnTopologicalSortShouldProduceValidOrder() {

        TopologicalSortResult result =
                KahnTopologicalSort.INSTANCE.sort(
                        TestGraphs.dag());

        AlgorithmVerifier.verifyTopologicalOrder(
                TestGraphs.dag(),
                result);
    }

    @Test
    void disconnectedDagShouldProduceValidOrder() {

        IGraph graph =
                TestGraphs.disconnectedDag();

        TopologicalSortResult result =
                KahnTopologicalSort.INSTANCE.sort(graph);

        AlgorithmVerifier.verifyTopologicalOrder(
                graph,
                result);
    }

    @Test
    void dfsShouldRejectCycle() {

        assertThrows(
                GraphCycleException.class,
                () -> DFSTopologicalSort.INSTANCE.sort(
                        TestGraphs.cyclicDirectedGraph()));
    }

    @Test
    void kahnShouldRejectCycle() {

        assertThrows(
                GraphCycleException.class,
                () -> KahnTopologicalSort.INSTANCE.sort(
                        TestGraphs.cyclicDirectedGraph()));
    }

    @Test
    void nullGraphThrows() {

        assertThrows(
                GraphEmptyException.class,
                () -> DFSTopologicalSort.INSTANCE.sort(null));
    }

    @Test
    void emptyGraphConstructionThrows() {

        assertThrows(
                InvalidGraphConfigurationException.class,
                () -> Graphs.directed()
                        .vertices(0)
                        .build());
    }
}
