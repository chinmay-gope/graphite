package io.graphite.examples;

import io.graphite.benchmark.stress.*;

public class StressTestRunner {

    private StressTestRunner() {
    }

    public static void main(String[] args) {
        TraversalStress.run();
        CycleStress.run(); // both dir & un-dir
        MSTStress.run();
        ShortestPathStress.run();
        TopologyStress.run();
        BipartiteStress.run(); // both bfs & dfs
        ConnectivityStress.run(); //  sparse, traversal, mst, dense
        EulerStress.run(); // path, circuit
    }
}
