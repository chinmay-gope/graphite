package io.graphite.benchmark;

import io.graphite.graph.IGraph;
import io.graphite.result.BenchmarkResult;

/**
 * Provides common functionality for Graphite benchmark examples.
 *
 * <p>{@code AbstractBenchmark} centralizes shared benchmark configuration,
 * including default warm-up iterations, measurement iterations, and helper
 * methods for executing benchmark tasks.</p>
 *
 * <p>This class is intended as a convenience base class for benchmark
 * examples and demonstrations.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Benchmarks
 * @see BenchmarkResult
 * @since 2.0
 */
public abstract class AbstractBenchmark {

    protected static final int WARMUP = 10;
    protected static final int ITERATIONS = 100;

    protected static BenchmarkResult benchmark(
            String name,
            IGraph graph,
            BenchmarkTask task
    ) {
        BenchmarkResult result = Benchmarks.builder()
                .name(name)
                .graph(graph)
                .task(task)
                .warmup(WARMUP)
                .iterations(ITERATIONS)
                .build()
                .run();

        System.out.println(result);

        return result;
    }
}
