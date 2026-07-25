package io.graphite.generator.pattern;

import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import io.graphite.graph.PatternGraphBuilder;

/**
 * Generates complete graphs.
 *
 * <p>A complete graph contains an edge between every pair of distinct
 * vertices. Complete graphs represent the maximum possible connectivity
 * for a graph of a given size.</p>
 *
<<<<<<< HEAD
 * <h2>Characteristics</h2>
=======
 * <h3>Characteristics</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Every vertex is adjacent to every other vertex.</li>
 *     <li>Contains n(n-1)/2 edges for undirected graphs.</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see PatternGraphBuilder
 * @since 2.0
 */
public final class CompleteGraphGenerator {
    private CompleteGraphGenerator() {
    }

    public static IGraph generate(int vertices) {

        if (vertices <= 0) {
            throw new InvalidGraphConfigurationException(
                    "Complete graph requires at least one vertex.");
        }

        var builder = Graphs
                .undirected()
                .vertices(vertices);

        for (int i = 0; i < vertices; i++)
            for (int j = i + 1; j < vertices; j++)
                builder.addEdge(i, j);

        return builder.build();
    }
}
