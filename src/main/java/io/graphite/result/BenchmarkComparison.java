package io.graphite.result;

import java.util.List;

public record BenchmarkComparison(
        List<BenchmarkResult> results
) implements Colors {

    public BenchmarkComparison {
        results = List.copyOf(results);
    }

    @Override
    public List<BenchmarkResult> results() {
        return List.copyOf(results);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(BOLD)
                .append(CYAN)
                .append("============================================================\n")
                .append("Benchmarks BenchmarkComparisonExample\n")
                .append("============================================================\n")
                .append(RESET);

        builder.append(String.format(
                "%-25s %-10s %-10s %-10s %-10s%n",
                "Algorithm",
                "Avg(ms)",
                "Min",
                "Max",
                "StdDev"
        ));

        builder.append("------------------------------------------------------------\n");

        BenchmarkResult fastest = null;

        for (BenchmarkResult result : results) {

            if (fastest == null ||
                    result.averageMillis() < fastest.averageMillis()) {
                fastest = result;
            }

            builder.append(String.format(
                    "%-25s %-10.3f %-10.3f %-10.3f %-10.3f%n",
                    result.name(),
                    result.averageMillis(),
                    result.minimumMillis(),
                    result.maximumMillis(),
                    result.standardDeviation()
            ));
        }

        if (fastest != null) {

            builder.append("\n")
                    .append(GREEN)
                    .append("🏆 Fastest : ")
                    .append(fastest.name())
                    .append(RESET);
        }

        return builder.toString();
    }
}
