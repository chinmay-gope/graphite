package io.graphite.builder;

import io.graphite.benchmark.Benchmarks;
import io.graphite.benchmark.BenchmarkConfig;
import io.graphite.benchmark.BenchmarkTask;
import io.graphite.graph.IGraph;

public final class BenchmarkBuilder {

    private String name = "Benchmarks";

    private BenchmarkTask task;
    private int warmup = 5;
    private int iterations = 20;
    private boolean measureMemory = false;

    private IGraph graph;

    public BenchmarkBuilder graph(IGraph graph) {
        this.graph = graph;
        return this;
    }

    public BenchmarkBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BenchmarkBuilder task(BenchmarkTask task) {
        this.task = task;
        return this;
    }

    public BenchmarkBuilder warmup(int warmup) {
        this.warmup = warmup;
        return this;
    }

    public BenchmarkBuilder iterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public BenchmarkBuilder measureMemory(boolean measureMemory) {
        this.measureMemory = measureMemory;
        return this;
    }

    public Benchmarks build() {

        BenchmarkConfig config =
                new BenchmarkConfig(
                        warmup,
                        iterations,
                        measureMemory
                );

        return new Benchmarks(
                name,
                task,
                config
        );
    }
}
