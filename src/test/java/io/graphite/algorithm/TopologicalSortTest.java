package io.graphite.algorithm;

import io.graphite.algorithm.topology.DFSTopologicalSort;
import io.graphite.algorithm.topology.KahnTopologicalSort;
import io.graphite.builder.Graphs;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.exception.graph.GraphEmptyException;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.exception.graph.UnsupportedGraphTypeException;
import io.graphite.generator.pattern.DAGGenerator;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;
import io.graphite.result.TopologicalSortResult;
import io.graphite.testutil.AlgorithmVerifier;
import io.graphite.testutil.GraphAssertions;
import io.graphite.testutil.TestGraphs;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void shouldSortSimpleDag() {

        IGraph graph = TestGraphs.dag();

        List<Integer> dfs = graph.topology().dfs().order();
        List<Integer> kahn = graph.topology().kahn().order();

        GraphAssertions.assertTopologicalOrder(graph, dfs);
        GraphAssertions.assertTopologicalOrder(graph, kahn);
    }

    @Test
    void randomDagShouldAlwaysProduceValidOrdering() {

        for (int i = 0; i < 100; i++) {

            IGraph graph = GraphPresetGenerator.dag(50);

            GraphAssertions.assertTopologicalOrder(
                    graph,
                    graph.topology().dfs().order()
            );

            GraphAssertions.assertTopologicalOrder(
                    graph,
                    graph.topology().kahn().order()
            );
        }
    }

    @Test
    void dfsAndKahnShouldReturnSameVertices() {

        IGraph graph = DAGGenerator.generate(7, 12);

        List<Integer> dfs = graph.topology().dfs().order();
        List<Integer> kahn = graph.topology().kahn().order();

        assertEquals(
                new HashSet<>(dfs),
                new HashSet<>(kahn)
        );
    }


    @Test
    void undirectedGraphThrows() {

        IGraph graph = TestGraphs.undirectedTree();

        assertThrows(
                UnsupportedGraphTypeException.class,
                () -> graph.topology().dfs()
        );

        assertThrows(
                UnsupportedGraphTypeException.class,
                () -> graph.topology().kahn()
        );
    }

    @Test
    void cyclicGraphThrows() {

        IGraph graph = TestGraphs.directedCycle();

        assertThrows(
                GraphCycleException.class,
                () -> graph.topology().dfs()
        );

        assertThrows(
                GraphCycleException.class,
                () -> graph.topology().kahn()
        );
    }
}
