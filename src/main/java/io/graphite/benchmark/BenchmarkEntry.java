package io.graphite.benchmark;

public record BenchmarkEntry(
        String name,
        BenchmarkTask task
) {
}
