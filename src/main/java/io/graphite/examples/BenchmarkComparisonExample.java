package io.graphite.examples;

import io.graphite.benchmark.Benchmarks;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;
import io.graphite.result.BenchmarkComparison;

public class
BenchmarkComparisonExample {

    public static void main(String[] args) {

        IGraph graph = GraphPresetGenerator.traversalGraph(8);

        BenchmarkComparison comparison =
                Benchmarks.compare()
                        .warmup(10)
                        .iterations(100)
                        .add("DFS", () -> graph.traversal().dfs(0))
                        .add("BFS", () -> graph.traversal().bfs(0))
                        .add("Dijkstra", () -> graph.shortestPath().dijkstra(0))
                        .run();

        System.out.println(comparison);
    }
}
