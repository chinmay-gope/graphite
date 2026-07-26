package io.graphite.builder;


import io.graphite.generator.RandomGraphBuilder;


import io.graphite.graph.DirectedGraph;
import io.graphite.graph.GraphFactory;

/**
 * Builds mutable or immutable directed graphs using a fluent API.
 *
 * <p>The {@code DirectedGraphBuilder} provides a convenient and readable way
 * to construct directed graphs by incrementally adding vertices, edges, and
 * configuration options before creating the final graph instance.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.directed()
 *         .vertices(5)
 *         .addEdge(0, 1)
 *         .addEdge(1, 2)
 *         .immutable()
 *         .build();
 * }</pre>
 *

 * <h2>Features</h2>

 * <h3>Features</h3>

 *
 * <ul>
 *     <li>Fluent builder API</li>
 *     <li>Directed graph construction</li>
 *     <li>Weighted and unweighted edges</li>
 *     <li>Mutable or immutable graphs</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>Builder instances are mutable and are intended for single-use during
 * graph construction.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see UndirectedGraphBuilder
 * @see RandomGraphBuilder
 * @see GraphFactory
 * @see Graphs
 * @since 2.0
 */
public final class DirectedGraphBuilder extends AbstractGraphBuilder<DirectedGraph, DirectedGraphBuilder> {

    DirectedGraphBuilder() {
        configuration.setDirected(true);
    }

    @Override
    protected DirectedGraph createGraph() {
        return GraphFactory.directed(configuration);
    }
}
