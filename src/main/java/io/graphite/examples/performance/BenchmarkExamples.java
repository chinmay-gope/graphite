package io.graphite.examples.performance;

import io.graphite.benchmark.Benchmarks;
import io.graphite.examples.ExamplePrinter;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;
import io.graphite.result.BenchmarkResult;

public final class BenchmarkExamples {

    private BenchmarkExamples() {
    }

    private static void traversalBenchmark() {

        IGraph graph = GraphPresetGenerator.traversalGraph(1000);

        BenchmarkResult result = Benchmarks.builder()
                .name("Breadth First Search")
                .task(() -> graph.traversal().bfs(0))
                .warmup(10)
                .iterations(100)
                .build()
                .run();

        ExamplePrinter.feature("Traversal Benchmarks");

        ExamplePrinter.api("""
                Benchmarks.builder()
                         .name("Breadth First Search")
                         .task(() -> graph.traversal().bfs(0))
                         .warmup(10)
                         .iterations(100)
                         .build()
                         .run()
                """);

        System.out.println(result);
    }

    private static void shortestPathBenchmark() {

        IGraph graph = GraphPresetGenerator.weightedGraph(1000);

        BenchmarkResult result = Benchmarks.builder()
                .name("Dijkstra")
                .task(() -> graph.shortestPath().dijkstra(0))
                .warmup(10)
                .iterations(100)
                .build()
                .run();

        ExamplePrinter.feature("Shortest Path Benchmarks");

        System.out.println(result);
    }

    private static void mstBenchmark() {

        IGraph graph = GraphPresetGenerator.mstGraph(1000);

        BenchmarkResult result = Benchmarks.builder()
                .name("Prim")
                .task(() -> graph.mst().prim(0))
                .warmup(10)
                .iterations(100)
                .build()
                .run();

        ExamplePrinter.feature("MST Benchmarks");

        System.out.println(result);
    }

    public static void run() {

        ExamplePrinter.title("Benchmarks Examples");

        traversalBenchmark();

        shortestPathBenchmark();

        mstBenchmark();
    }
}
