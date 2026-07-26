package io.graphite.examples;

import io.graphite.examples.algorithm.*;
import io.graphite.examples.core.BasicExamples;
import io.graphite.examples.core.BuilderExamples;
import io.graphite.examples.core.GeneratorExamples;
import io.graphite.examples.format.FormatterExamples;
import io.graphite.examples.io.GraphIOExamples;
import io.graphite.examples.performance.BenchmarkExamples;
import io.graphite.examples.performance.StressExamples;

import java.io.IOException;

public final class MainExamples {

    public MainExamples() {
    }

    public static void main(String[] args) throws IOException {

        TraversalExamples.run();
        BasicExamples.run();
        BuilderExamples.run();
        GeneratorExamples.run();
        CycleExamples.run();
        ShortestPathExamples.run();
        MSTExamples.run();
        ConnectivityExamples.run();
        TopologyExamples.run();
        BipartiteExamples.run();
        EulerExamples.run();
        FormatterExamples.run();
        BenchmarkExamples.run();
        StressExamples.run();

        GraphIOExamples.run();
    }
}
