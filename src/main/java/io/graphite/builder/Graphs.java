package io.graphite.builder;

<<<<<<< HEAD
import io.graphite.generator.RandomGraphBuilder;
=======
>>>>>>> fec1ea5 (fix: java docs)
import io.graphite.generator.example.GraphExampleGenerator;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.PatternGraphBuilder;
import io.graphite.graph.transform.GraphTransformFactory;
import io.graphite.io.reader.GraphReaderService;


/**
 * Entry point for constructing, generating, and obtaining graph instances.
 *
 * <p>{@code Graphs} provides a collection of static factory methods that
 * expose Graphite's builder and generator framework through a unified API.</p>
 *
 * <pre>{@code
 * Graphs.undirected();
 *
 * Graphs.directed();
 *
 * Graphs.random();
 *
 * Graphs.patterns();
 *
 * Graphs.presets();
 *
 * Graphs.examples();
 * }</pre>
 *
 * <p>This class should be the preferred starting point for creating graphs.
 * Users are encouraged to use these factory methods instead of directly
 * instantiating graph implementations.</p>
 *
 * @since 2.0
 */
public final class Graphs {

    private Graphs() {
    }

    /**
     * Creates a builder for constructing directed graphs.
     *
     * <p>The returned builder provides a fluent API for configuring
     * vertices, edges, weights, mutability, and other graph options
     * before building the graph instance.</p>
     *
     * @return a directed graph builder
     * @see DirectedGraphBuilder
     * @see #undirected()
     * @since 2.0
     */
    public static DirectedGraphBuilder directed() {
        return new DirectedGraphBuilder();
    }

    /**
     * Creates a builder for constructing undirected graphs.
     *
     * <p>The returned builder provides a fluent API for creating
     * weighted or unweighted undirected graph instances.</p>
     *
     * @return an undirected graph builder
     * @see UndirectedGraphBuilder
     * @see #directed()
     * @since 2.0
     */
    public static UndirectedGraphBuilder undirected() {
        return new UndirectedGraphBuilder();
    }

    /**
     * Creates a builder for generating random graphs.
     *
     * <p>The builder supports configurable graph size, density,
     * connectivity, edge weights, and graph type.</p>
     *
     * @return a random graph builder
     * @see RandomGraphBuilder
     * @since 2.0
     */
    public static RandomGraphBuilder random() {
        return new RandomGraphBuilder();
    }

    public static GraphTransformFactory transform() {
        return GraphTransformFactory.INSTANCE;
    }

    public static GraphPresetGenerator presets() {

        return GraphPresetGenerator.INSTANCE;
    }

    public static GraphExampleGenerator examples() {

        return GraphExampleGenerator.INSTANCE;
    }

    /**
     * Creates a builder for generating predefined graph patterns.
     *
     * <p>Supported patterns include complete graphs, trees,
     * stars, wheels, grids, complete bipartite graphs,
     * and directed acyclic graphs.</p>
     *
     * @return a pattern graph builder
     * @see PatternGraphBuilder
     * @since 2.0
     */
    public static PatternGraphBuilder patterns() {
        return PatternGraphBuilder.INSTANCE;
    }

    public static GraphReaderService read() {
        return GraphReaderService.INSTANCE;
    }

}
