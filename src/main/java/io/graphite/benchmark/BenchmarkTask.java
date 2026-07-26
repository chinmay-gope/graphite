package io.graphite.benchmark;

import io.graphite.builder.BenchmarkBuilder;

/**
 * Represents a benchmarkable unit of work.
 *
 * <p>{@code BenchmarkTask} is a functional interface whose implementation
 * encapsulates the operation being measured during benchmark execution.</p>
 *
 * <p>Typical tasks include graph algorithms such as BFS, DFS, Dijkstra,
 * Prim, and other library operations.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Benchmarks
 * @see BenchmarkBuilder
 * @since 2.0
 */
@FunctionalInterface
public interface BenchmarkTask {
    void execute();
}
