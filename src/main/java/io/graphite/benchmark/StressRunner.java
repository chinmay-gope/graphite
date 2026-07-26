package io.graphite.benchmark;

import io.graphite.examples.ExamplePrinter;
import io.graphite.graph.IGraph;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Executes large-scale stress tests for graph algorithms.
 *
 * <p>{@code StressRunner} repeatedly executes graph algorithms over graphs
 * of varying sizes to evaluate scalability, stability, and performance
 * under increasing workloads.</p>
 *

 * <h2>Typical Uses</h2>

 * <h3>Typical Uses</h3>

 *
 * <ul>
 *     <li>Algorithm scalability testing</li>
 *     <li>Performance regression detection</li>
 *     <li>Benchmarks comparison</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Benchmarks
 * @see BenchmarkRunner
 * @since 2.0
 */
public final class StressRunner {

    private StressRunner() {
    }

    public static void run(
            String title,
            StressConfig config,
            Function<Integer, IGraph> graphFactory,
            Consumer<IGraph> algorithm) {

        ExamplePrinter.execute(title, () -> {
            long total = 0;

            for (int vertices : config.vertices()) {

                long start = System.nanoTime();

                IGraph graph = graphFactory.apply(vertices);

                for (int i = 0; i < config.iterations(); i++) {

                    algorithm.accept(graph);
                }

                long end = System.nanoTime();

                total += end - start;

                System.out.printf(
                        "✓ %-5d vertices (%d graphs)%n",
                        vertices,
                        config.iterations()
                );
            }

            System.out.printf(
                    "%nTotal Time : %.3f ms%n",
                    total / 1_000_000.0
            );
        });
    }

    public static int randomSource(IGraph graph) {

        return ThreadLocalRandom.current().nextInt(graph.getVertices());
    }
}
