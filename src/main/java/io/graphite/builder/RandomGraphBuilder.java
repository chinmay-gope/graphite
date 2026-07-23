package io.graphite.builder;

import io.graphite.generator.RandomGraphGenerator;
import io.graphite.graph.IGraph;

/**
 * Fluent builder for generating configurable random graphs.
 *
 * <p>The random graph builder supports construction of directed,
 * undirected, weighted, connected, immutable, and custom-density
 * graphs through a fluent configuration API.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.random()
 *         .undirected()
 *         .vertices(1000)
 *         .edges(3000)
 *         .connected()
 *         .weighted()
 *         .weightRange(1, 50)
 *         .immutable()
 *         .build();
 * }</pre>
 *
 * <h3>Supported Configuration</h3>
 *
 * <ul>
 *     <li>Directed or undirected graphs.</li>
 *     <li>Weighted or unweighted edges.</li>
 *     <li>Connected graph generation.</li>
 *     <li>Immutable graph creation.</li>
 *     <li>Configurable edge count.</li>
 *     <li>Custom weight ranges.</li>
 *     <li>Optional self-loops.</li>
 *     <li>Optional parallel edges.</li>
 * </ul>
 * <p>
 *  * <h3>Implementation Notes</h3>
 * <p>
 *  * <p>The builder delegates graph generation to the internal random graph
 *  * generation framework.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see RandomGraphGenerator
 * @see GraphConfiguration
 * @see Graphs
 * @since 2.0
 */
public final class RandomGraphBuilder {
    private final GraphConfiguration configuration =
            new GraphConfiguration();

    public RandomGraphBuilder() {
    }

    public RandomGraphBuilder directed() {
        configuration.setDirected(true);
        return this;
    }

    public RandomGraphBuilder undirected() {
        configuration.setDirected(false);
        return this;
    }

    public RandomGraphBuilder vertices(int vertices) {
        configuration.setVertices(vertices);
        return this;
    }

    public RandomGraphBuilder edges(int edges) {
        configuration.setEdges(edges);
        return this;
    }

    public RandomGraphBuilder weighted() {
        configuration.setWeighted(true);
        return this;
    }

    public RandomGraphBuilder unweighted() {
        configuration.setWeighted(false);
        return this;
    }

    public RandomGraphBuilder connected() {
        configuration.setConnected(true);
        return this;
    }

    public RandomGraphBuilder disconnected() {
        configuration.setConnected(false);
        return this;
    }

    public RandomGraphBuilder allowSelfLoops() {
        configuration.setSelfLoops(true);
        return this;
    }

    public RandomGraphBuilder allowParallelEdges() {
        configuration.setParallelEdges(true);
        return this;
    }

    public RandomGraphBuilder weightRange(int min, int max) {
        configuration.setMinWeight(min);
        configuration.setMaxWeight(max);
        return this;
    }

    public RandomGraphBuilder mutable() {
        configuration.setImmutable(false);
        return this;
    }

    public RandomGraphBuilder immutable() {
        configuration.setImmutable(true);
        return this;
    }

    public IGraph build() {

        return new RandomGraphGenerator<>(configuration)
                .generate();
    }
}
