package io.graphite.builder;

import io.graphite.graph.GraphFactory;
import io.graphite.graph.UndirectedGraph;

/**
 * Builds mutable or immutable undirected graphs using a fluent API.
 *
 * <p>The {@code UndirectedGraphBuilder} simplifies the creation of
 * undirected graphs while providing configuration options for graph
 * structure and edge insertion.</p>
 *

 * <h2>Features</h2>

 * <h3>Features</h3>

 *
 * <ul>
 *     <li>Fluent API</li>
 *     <li>Undirected graph construction</li>
 *     <li>Weighted graphs</li>
 *     <li>Immutable graph creation</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see DirectedGraphBuilder
 * @see GraphFactory
 * @see Graphs
 * @since 2.0
 */
public final class UndirectedGraphBuilder extends AbstractGraphBuilder<UndirectedGraph, UndirectedGraphBuilder> {

    UndirectedGraphBuilder() {
        configuration.setDirected(false);
    }

    @Override
    protected UndirectedGraph createGraph() {
        return GraphFactory.undirected(configuration);
    }
}
