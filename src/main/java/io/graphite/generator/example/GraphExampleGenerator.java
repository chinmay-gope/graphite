package io.graphite.generator.example;

import io.graphite.builder.Graphs;
import io.graphite.builder.UndirectedGraphBuilder;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;

public class GraphExampleGenerator {

    public static final GraphExampleGenerator INSTANCE = new GraphExampleGenerator();

    public GraphExampleGenerator() {

    }

    public static IGraph eulerCircuitGraph(int vertices) {

        if (vertices < 3) {
            throw new InvalidGraphConfigurationException(
                    "Euler circuit requires at least 3 vertices.");
        }

        UndirectedGraphBuilder builder = Graphs.undirected();

        // Base cycle
        for (int i = 0; i < vertices - 1; i++) {
            builder.addEdge(i, i + 1);
        }

        builder.addEdge(vertices - 1, 0);

        return builder.build();
    }

    public static IGraph eulerPathGraph(int vertices) {

        if (vertices < 2) {
            throw new InvalidGraphConfigurationException(
                    "Euler path requires at least 2 vertices.");
        }

        UndirectedGraphBuilder builder = Graphs.undirected();

        for (int i = 0; i < vertices - 1; i++) {
            builder.addEdge(i, i + 1);
        }

        return builder.build();
    }

    public static IGraph invalidEulerGraph(int vertices) {

        if (vertices < 5) {
            throw new InvalidGraphConfigurationException(
                    "Invalid Euler graph requires at least 5 vertices.");
        }

        UndirectedGraphBuilder builder = Graphs.undirected();

        for (int i = 1; i < vertices; i++) {
            builder.addEdge(0, i);
        }

        return builder.build();
    }

    public static IGraph disconnectedEulerGraph(int vertices) {

        UndirectedGraphBuilder builder = Graphs.undirected();

        int mid = vertices / 2;

        for (int i = 0; i < mid - 1; i++) {
            builder.addEdge(i, i + 1);
        }

        for (int i = mid; i < vertices - 1; i++) {
            builder.addEdge(i, i + 1);
        }

        return builder.build();
    }

    public GraphExampleGenerator examples() {
        return new GraphExampleGenerator();
    }
}
