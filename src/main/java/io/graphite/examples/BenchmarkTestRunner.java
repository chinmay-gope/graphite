package io.graphite.examples;

import io.graphite.benchmark.algorithm.*;

public final class BenchmarkTestRunner {
    private BenchmarkTestRunner() {
    }

    public static void main(String[] args) {
        TraversalBenchmark.run();
        CycleBenchmark.run();
        MSTBenchmark.run();
        ShortestPathBenchmark.run();
        ConnectivityBenchmark.run();
        TopologyBenchmark.run();
        BipartiteBenchmark.run();
        EulerBenchmark.run();
    }
}
