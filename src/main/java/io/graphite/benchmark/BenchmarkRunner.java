package io.graphite.benchmark;

import io.graphite.exception.GraphException;
import io.graphite.result.BenchmarkResult;

/**
 * Executes benchmark tasks and collects execution statistics.
 *
 * <p>{@code BenchmarkRunner} performs the warm-up and measurement phases
 * of a benchmark before producing a {@link BenchmarkResult} containing
 * timing statistics.</p>
 *

 * <h2>Responsibilities</h2>

 * <h3>Responsibilities</h3>

 *
 * <ul>
 *     <li>Warm-up execution</li>
 *     <li>Timed iterations</li>
 *     <li>Result aggregation</li>
 *     <li>Statistical computation</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Benchmarks
 * @see BenchmarkStatistics
 * @since 2.0
 */
public final class BenchmarkRunner {

    private BenchmarkRunner() {
    }

    public static BenchmarkResult run(
            String name,
            BenchmarkTask task,
            BenchmarkConfig config
    ) {

        // ---------------------------------------------------------
        // Warmup
        // ---------------------------------------------------------

        for (int i = 0; i < config.warmup(); i++) {
            task.execute();
        }

        // ---------------------------------------------------------
        // Benchmarks
        // ---------------------------------------------------------

        double[] times = new double[config.iterations()];

        long totalNanos = 0;

        int completed = 0;

        int skipped = 0;
        while (completed < config.iterations()) {

            try {

                long start = System.nanoTime();

                task.execute();

                long end = System.nanoTime();

                long elapsed = end - start;

                totalNanos += elapsed;

                times[completed++] = elapsed / 1_000_000.0;

            } catch (GraphException ignored) {

                skipped++;
            }
        }

        // ---------------------------------------------------------
        // Statistics
        // ---------------------------------------------------------

        double average = BenchmarkStatistics.average(times);
        double minimum = BenchmarkStatistics.minimum(times);
        double maximum = BenchmarkStatistics.maximum(times);
        double deviation = BenchmarkStatistics.standardDeviation(times);

        double operationsPerSecond =
                config.iterations() / (totalNanos / 1_000_000_000.0);

        return new BenchmarkResult(
                name,

                config.warmup(),
                config.iterations(),
                skipped,

                totalNanos,

                average,
                minimum,
                maximum,
                deviation,
                operationsPerSecond
        );
    }
}
