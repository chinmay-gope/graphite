package io.graphite.benchmark;

import io.graphite.result.BenchmarkResult;

/**
 * Executes benchmark tasks and collects execution statistics.
 *
 * <p>{@code BenchmarkRunner} performs the warm-up and measurement phases
 * of a benchmark before producing a {@link BenchmarkResult} containing
 * timing statistics.</p>
 *
<<<<<<< HEAD
 * <h2>Responsibilities</h2>
=======
 * <h3>Responsibilities</h3>
>>>>>>> fec1ea5 (fix: java docs)
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
 * @see Benchmark
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
        // Benchmark
        // ---------------------------------------------------------

        double[] times = new double[config.iterations()];
        long totalNanos = 0;

        for (int i = 0; i < config.iterations(); i++) {

            long start = System.nanoTime();

            task.execute();

            long end = System.nanoTime();

            long elapsed = end - start;

            totalNanos += elapsed;

            times[i] = (end - start) / 1_000_000.0;
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

                totalNanos,

                average,
                minimum,
                maximum,
                deviation,
                operationsPerSecond
        );
    }
}
