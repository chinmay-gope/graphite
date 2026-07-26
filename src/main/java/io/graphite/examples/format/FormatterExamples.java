package io.graphite.examples.format;

import io.graphite.builder.Graphs;
import io.graphite.examples.ExamplePrinter;
import io.graphite.graph.IGraph;
import io.graphite.print.GraphPrinter;

public class FormatterExamples {
    private static final IGraph GRAPH =
            Graphs.undirected()
                    .weighted(true)
                    .addEdge(0, 1, 5)
                    .addEdge(0, 2, 2)
                    .addEdge(1, 3, 7)
                    .addEdge(2, 3, 4)
                    .addEdge(3, 4, 3)
                    .build();

    private static void compact() {

        ExamplePrinter.feature("Compact Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.compact(graph)"
        );

        GraphPrinter.compact(GRAPH);
    }

    private static void tree() {

        ExamplePrinter.feature("Tree Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.tree(graph)"
        );

        GraphPrinter.tree(GRAPH);
    }

    private static void edgeList() {

        ExamplePrinter.feature("EdgeList Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.edgeList(graph)"
        );

        GraphPrinter.edgeList(GRAPH);
    }

    private static void matrix() {

        ExamplePrinter.feature("Matrix Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.matrix(graph)"
        );

        GraphPrinter.matrix(GRAPH);
    }

    private static void json() {

        ExamplePrinter.feature("Json Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.json(graph)"
        );

        GraphPrinter.json(GRAPH);
    }

    private static void dot() {

        ExamplePrinter.feature("Dot Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.dot(graph)"
        );

        GraphPrinter.dot(GRAPH);
    }

    private static void mermaid() {

        ExamplePrinter.feature("Mermaid Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.mermaid(graph)"
        );

        GraphPrinter.mermaid(GRAPH);
    }

    private static void stats() {

        ExamplePrinter.feature("Statistics Formatter");

        ExamplePrinter.graph(GRAPH);

        ExamplePrinter.api(
                "GraphPrinter.statistics(graph)"
        );

        GraphPrinter.statistics(GRAPH);
    }

    public static void run() {
        ExamplePrinter.title("Graph Formatter Examples");

        compact();
        tree();
        edgeList();
        matrix();

        json();

        dot();
        mermaid();

        stats();
    }

    public static void main(String[] args) {
        run();
    }

}
