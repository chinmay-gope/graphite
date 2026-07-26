package io.graphite.benchmark.algorithm;

import io.graphite.algorithm.bipartite.BFSBipartiteChecker;
import io.graphite.algorithm.bipartite.DFSBipartiteChecker;
import io.graphite.benchmark.AbstractBenchmark;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;

public final class BipartiteBenchmark extends AbstractBenchmark {

    private static final IGraph GRAPH =
            GraphPresetGenerator.bipartiteGraph(1000);

    private BipartiteBenchmark() {
    }

    public static void run() {

        benchmark(
                "BFS Bipartite Check",
                GRAPH,
                () -> BFSBipartiteChecker.INSTANCE.isBipartite(GRAPH)
        );

        benchmark(
                "DFS Bipartite Check",
                GRAPH,
                () -> DFSBipartiteChecker.INSTANCE.isBipartite(GRAPH)
        );
    }
}
