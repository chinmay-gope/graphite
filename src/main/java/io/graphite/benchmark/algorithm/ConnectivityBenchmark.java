package io.graphite.benchmark.algorithm;

import io.graphite.algorithm.connectivity.APFinder;
import io.graphite.algorithm.connectivity.BiconnectedComponents;
import io.graphite.algorithm.connectivity.BridgeFinder;
import io.graphite.algorithm.connectivity.Kosaraju;
import io.graphite.benchmark.AbstractBenchmark;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;

public final class ConnectivityBenchmark extends AbstractBenchmark {

    private static final IGraph UNDIRECTED =
            GraphPresetGenerator.sparseGraph(1000);

    private static final IGraph DIRECTED =
            GraphPresetGenerator.directedSparseGraph(1000);

    private ConnectivityBenchmark() {
    }

    public static void run() {

        benchmark(
                "Bridge Detection",
                UNDIRECTED,
                () -> BridgeFinder.INSTANCE.findBridges(UNDIRECTED)
        );

        benchmark(
                "Articulation Point Detection",
                UNDIRECTED,
                () -> APFinder.INSTANCE.findArticulationPoints(UNDIRECTED)
        );

        benchmark(
                "Biconnected Components",
                UNDIRECTED,
                () -> BiconnectedComponents.INSTANCE
                        .findBiconnectedComponents(UNDIRECTED)
        );

        benchmark(
                "Kosaraju SCC",
                DIRECTED,
                () -> Kosaraju.INSTANCE.findSCCs(DIRECTED)
        );
    }
}
