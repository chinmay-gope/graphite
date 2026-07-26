package io.graphite.benchmark;

import io.graphite.builder.BenchmarkBuilder;
import io.graphite.builder.BenchmarkComparisonBuilder;
import io.graphite.result.BenchmarkResult;

/**
 * Entry point for Graphite's benchmarking framework.
 *
 * <p>{@code Benchmarks} provides a fluent API for measuring algorithm
 * execution time under controlled conditions using configurable warmup
 * and measurement iterations.</p>
 *
 * <pre>{@code
 * Benchmarks.builder()
 *         .name("BFS")
 *         .task(() -> graph.traversal().bfs(0))
 *         .warmup(10)
 *         .iterations(100)
 *         .build()
 *         .run();
 * }</pre>
 *
 * @since 2.0
 */
public final class Benchmarks {

    private final String name;
    private final BenchmarkTask task;
    private final BenchmarkConfig configuration;


    public Benchmarks(String name, BenchmarkTask task, BenchmarkConfig configuration) {
        this.name = name;
        this.task = task;
        this.configuration = configuration;
    }

    public static BenchmarkBuilder builder() {
        return new BenchmarkBuilder();
    }

    public BenchmarkResult run() {
        return BenchmarkRunner.run(
                name,
                task,
                configuration
        );
    }

    public static BenchmarkComparisonBuilder compare() {

        return new BenchmarkComparisonBuilder();
    }
}
