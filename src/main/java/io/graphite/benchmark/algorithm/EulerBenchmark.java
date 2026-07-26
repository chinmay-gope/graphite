package io.graphite.benchmark.algorithm;

import io.graphite.algorithm.euler.Hierholzer;
import io.graphite.benchmark.AbstractBenchmark;
import io.graphite.generator.example.GraphExampleGenerator;
import io.graphite.graph.IGraph;

public final class EulerBenchmark extends AbstractBenchmark {

    private static final IGraph PATH =
            GraphExampleGenerator.eulerPathGraph(1000);

    private static final IGraph CIRCUIT =
            GraphExampleGenerator.eulerCircuitGraph(1000);

    private EulerBenchmark() {
    }

    public static void run() {

        benchmark(
                "Euler Path",
                PATH,
                () -> Hierholzer.INSTANCE.findEulerPath(PATH)
        );

        benchmark(
                "Euler Circuit",
                CIRCUIT,
                () -> Hierholzer.INSTANCE.findEulerCircuit(CIRCUIT)
        );
    }
}
