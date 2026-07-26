package io.graphite;

import io.graphite.examples.BenchmarkComparisonExample;
import io.graphite.examples.BenchmarkTestRunner;
import io.graphite.examples.MainExamples;
import io.graphite.examples.StressTestRunner;
import io.graphite.examples.format.FormatterExamples;

import java.io.IOException;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            usage();
            return;
        }

        switch (args[0].toLowerCase()) {

            case "examples" -> MainExamples
                    .main(new String[0]);

            case "stress" -> StressTestRunner
                    .main(new String[0]);

            case "format" -> FormatterExamples.run();

            case "benchmark" -> {

                if (args.length > 1 &&
                        args[1].equalsIgnoreCase(" -c")) {

                    BenchmarkComparisonExample.main(new String[0]);

                } else {

                    BenchmarkTestRunner.main(new String[0]);
                }
            }

            case "help" -> usage();

            default -> {

                System.out.println(
                        "Unknown command: " + args[0]);

                usage();
            }
        }
    }

    private static void usage() {

        System.out.println("""
                Graphite CLI
                
                Usage:
                  graphite examples
                  graphite stress
                  graphite benchmark
                  graphite benchmark -c
                  graphite help
                """);
    }
}
