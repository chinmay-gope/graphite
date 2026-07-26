package io.graphite.result;

import io.graphite.benchmark.BenchmarkRunner;
import io.graphite.benchmark.Benchmarks;

/**
 * Represents the outcome of a benchmark execution.
 *
 * <p>A {@code BenchmarkResult} stores execution statistics collected during
 * repeated benchmark iterations.</p>
 *

 * <h2>
 * Contents
 * </h2>
 * <br>
 * Contents
 * </br>
 *
 * <ul>
 *     <li>Average execution time</li>
 *     <li>Minimum execution time</li>
 *     <li>Maximum execution time</li>
 *     <li>Iteration count</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Benchmarks
 * @see BenchmarkRunner
 * @since 2.0
 */
public record BenchmarkResult(
        // ---------------------------------------------------------
        // Benchmarks
        // ---------------------------------------------------------
        String name,
        int warmup,
        int iterations,
        int skipped,
        // ---------------------------------------------------------
        // Performance
        // ---------------------------------------------------------
        double totalNanos,
        double averageMillis,
        double minimumMillis,
        double maximumMillis,
        double standardDeviation,
        double operationsPerSecond
) implements Colors {

    @Override
    public String toString() {
        return String.format(
                "%n%s%s▶ %s%s%n" +
                        "%-20s : %s%d%s%n" +
                        "%-20s : %s%d%s%n" +
                        "%-20s : %s%d%s%n" +
                        "%-20s : %s%.3f ms%s%n" +
                        "%-20s : %s%.3f ms%s%n" +
                        "%-20s : %s%.3f ms%s%n" +
                        "%-20s : %s%.3f ms%s%n" +
                        "%-20s : %s%.3f ops/sec%s%n",
                BOLD, CYAN, name, RESET,
                "Warmup", YELLOW, warmup, RESET,
                "Iterations", YELLOW, iterations, RESET,
                "Skipped Iterations", YELLOW, skipped, RESET,
                "Average", CYAN, averageMillis, RESET,
                "Minimum", GREEN, minimumMillis, RESET,
                "Maximum", RED, maximumMillis, RESET,
                "Std Deviation", MAGENTA, standardDeviation, RESET,
                "Ops/sec", BLUE, operationsPerSecond, RESET
        );
    }
}
