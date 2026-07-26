package io.graphite.graph;

import io.graphite.builder.GraphConfiguration;
import io.graphite.builder.Graphs;

/**
 * Factory responsible for creating concrete graph implementations.
 *
 * <p>{@code GraphFactory} centralizes graph instantiation, ensuring that
 * graph construction remains consistent throughout the framework.
 * Instead of exposing constructors directly, builders and generators
 * delegate object creation to this factory.</p>
 *
 * <p>The factory selects the appropriate graph implementation based on
 * the supplied {@link GraphConfiguration}, including directed,
 * undirected, mutable, and immutable variants.</p>
 *
 * <pre>{@code
 * Graph graph = GraphFactory.undirected(configuration);
 * }</pre>
 *

 * <h2>Design</h2>

 * <h3>Design</h3>

 *
 * <ul>
 *     <li>Centralizes graph creation.</li>
 *     <li>Encapsulates implementation selection.</li>
 *     <li>Supports the Factory design pattern.</li>
 *     <li>Used internally by builders and generators.</li>
 * </ul>
 *
 * <p>This class is intended primarily for internal framework use.
 * Applications should create graphs through {@link Graphs}.</p>
 *
 * @see Graphs
 * @see GraphConfiguration
 * @see Graph
 * @since 2.0
 */
public final class GraphFactory {

    public final GraphFactory INSTANCE = new GraphFactory();

    private GraphFactory() {
    }

    public static DirectedGraph directed(
            GraphConfiguration configuration) {

        return new DirectedGraph(configuration);
    }

    public static UndirectedGraph undirected(
            GraphConfiguration configuration) {

        return new UndirectedGraph(configuration);
    }

    public static IGraph create(GraphConfiguration configuration) {
        return configuration.isDirected()
                ? directed(configuration)
                : undirected(configuration);
    }
}
