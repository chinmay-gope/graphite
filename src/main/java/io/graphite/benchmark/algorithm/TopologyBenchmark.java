package io.graphite.benchmark.algorithm;

import io.graphite.algorithm.topology.DFSTopologicalSort;
import io.graphite.algorithm.topology.KahnTopologicalSort;
import io.graphite.benchmark.AbstractBenchmark;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;

public final class TopologyBenchmark extends AbstractBenchmark {

    private static final IGraph DAG =
            GraphPresetGenerator.dag(1000);

    public static void main(String[] args) {
        System.out.println("Vertices : " + DAG.vertexCount());

        for (Edge edge : DAG.getEdges()) {

            if (!DAG.contains(edge.source()))
                System.out.println("Inactive source " + edge);

            if (!DAG.contains(edge.destination()))
                System.out.println("Inactive destination " + edge);
        }
    }

    private TopologyBenchmark() {
    }

    public static void run() {

        benchmark(
                "DFS Topological Sort",
                DAG,
                () -> DFSTopologicalSort.INSTANCE.sort(DAG)
        );

        benchmark(
                "Kahn Topological Sort",
                DAG,
                () -> KahnTopologicalSort.INSTANCE.sort(DAG)
        );
    }
}
