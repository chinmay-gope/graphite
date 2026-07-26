package io.graphite.benchmark.algorithm;

import io.graphite.benchmark.AbstractBenchmark;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;

public final class ShortestPathBenchmark extends AbstractBenchmark {

    private static final IGraph GRAPH =
            GraphPresetGenerator.weightedGraph(1000);

    public static void run() {

        benchmark(
                "Dijkstra",
                GRAPH,
                () -> GRAPH.shortestPath().dijkstra(0)
        );

        benchmark(
                "BellmanFord",
                GRAPH,
                () -> GRAPH.shortestPath().bellmanFord(0)
        );
    }
}
