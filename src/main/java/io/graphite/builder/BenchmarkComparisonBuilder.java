package io.graphite.builder;

import io.graphite.benchmark.BenchmarkConfig;
import io.graphite.benchmark.BenchmarkEntry;
import io.graphite.benchmark.BenchmarkRunner;
import io.graphite.benchmark.BenchmarkTask;
import io.graphite.result.BenchmarkComparison;
import io.graphite.result.BenchmarkResult;

import java.util.ArrayList;
import java.util.List;

public final class BenchmarkComparisonBuilder {

    private int warmup = 5;

    private int iterations = 20;

    private boolean measureMemory;

    private final List<BenchmarkEntry> entries =
            new ArrayList<>();

    public BenchmarkComparisonBuilder warmup(int warmup) {
        this.warmup = warmup;
        return this;
    }

    public BenchmarkComparisonBuilder iterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public BenchmarkComparisonBuilder measureMemory(boolean value) {
        this.measureMemory = value;
        return this;
    }

    public BenchmarkComparisonBuilder add(
            String name,
            BenchmarkTask task
    ) {

        entries.add(
                new BenchmarkEntry(
                        name,
                        task
                )
        );

        return this;
    }

    public BenchmarkComparison run() {

        BenchmarkConfig config =
                new BenchmarkConfig(
                        warmup,
                        iterations,
                        measureMemory
                );

        List<BenchmarkResult> results =
                new ArrayList<>();

        for (BenchmarkEntry entry : entries) {

            results.add(
                    BenchmarkRunner.run(
                            entry.name(),
                            entry.task(),
                            config
                    )
            );
        }

        return new BenchmarkComparison(results);
    }
}
