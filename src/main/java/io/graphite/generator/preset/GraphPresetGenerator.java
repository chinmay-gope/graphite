package io.graphite.generator.preset;

import io.graphite.builder.Graphs;
import io.graphite.generator.RandomGraphBuilder;
import io.graphite.generator.pattern.BipartiteGraphGenerator;
import io.graphite.graph.IGraph;
import io.graphite.graph.PatternGraphBuilder;

/**
 * Provides a collection of predefined graphs commonly used for testing,
 * benchmarking, demonstrations, and algorithm evaluation.
 *
 * <p>{@code GraphPresetGenerator} offers factory methods for constructing
 * frequently used graph configurations such as sparse graphs, dense graphs,
 * weighted graphs, trees, directed acyclic graphs (DAGs), and bipartite
 * graphs. These presets provide consistent graph structures suitable for
 * examples, benchmarks, and automated testing.</p>
 *
 * <h2>Available Presets</h2>
 * <h3>Available Presets</h3>
 *
 * <ul>
 *     <li>Traversal graphs</li>
 *     <li>Shortest path graphs</li>
 *     <li>Minimum spanning tree graphs</li>
 *     <li>Dense and sparse graphs</li>
 *     <li>Weighted graphs</li>
 *     <li>Directed graphs</li>
 *     <li>Tree graphs</li>
 *     <li>Directed acyclic graphs</li>
 *     <li>Bipartite graphs</li>
 * </ul>
 *
 * <h2>Implementation Notes</h2>
 * <h3>Implementation Notes</h3>
 *
 * <p>Each preset delegates graph construction to the appropriate builder
 * or pattern generator while applying sensible default parameters.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Graphs
 * @see RandomGraphBuilder
 * @see PatternGraphBuilder
 * @since 2.0
 */
public class GraphPresetGenerator {

    public static final GraphPresetGenerator INSTANCE = new GraphPresetGenerator();
    private static final double DENSITY = 0.75;

    private GraphPresetGenerator() {

    }

    public static IGraph traversalGraph(int vertices) {

        return Graphs.random()
                .undirected()
                .immutable()
                .vertices(vertices)
                .edges(vertices * 2)
                .connected()
                .build();
    }

    public static IGraph mstGraph(int vertices) {
        return Graphs.random()
                .undirected()
                .immutable()
                .vertices(vertices)
                .edges(vertices * 3)
                .weighted()
                .connected()
                .build();
    }

    public static IGraph denseGraph(int vertices) {

        return denseGraph(vertices, DENSITY);
    }

    public static IGraph directedDenseGraph(int vertices) {

        int maximumEdges = vertices * (vertices - 1) / 2;

        int edges = (int) (maximumEdges * 0.75);

        return Graphs.random()
                .directed()
                .immutable()
                .vertices(vertices)
                .edges(edges)
                .connected()
                .build();
    }

    public static IGraph denseGraph(int vertices, double density) {

        int maximumEdges = vertices * (vertices - 1) / 2;

        int edges = (int) (maximumEdges * density);

        return Graphs.random()
                .immutable()
                .undirected()
                .vertices(vertices)
                .edges(edges)
                .connected()
                .build();
    }

    public static IGraph denseWeightedGraph(int vertices) {

        int maxEdges = vertices * (vertices - 1) / 2;

        int edges = (int) (maxEdges * DENSITY);

        return Graphs.random()
                .immutable()
                .undirected()
                .vertices(vertices)
                .edges(edges)
                .connected()
                .weightRange(1, 50)
                .build();
    }

    public static IGraph weightedGraph(int vertices) {

        return Graphs.random()
                .immutable()
                .undirected()
                .vertices(vertices)
                .edges(vertices * 2)
                .connected()
                .weightRange(1, 50)
                .build();
    }

    public static IGraph directedGraph(int vertices) {

        return Graphs.random()
                .immutable()
                .directed()
                .vertices(vertices)
                .edges(vertices * 2)
                .build();
    }

    public static IGraph sparseGraph(int vertices) {

        return Graphs.random()
                .immutable()
                .undirected()
                .vertices(vertices)
                .edges((int) (vertices * 1.3))
                .connected()
                .build();
    }

    public static IGraph directedSparseGraph(int vertices) {

        return Graphs.random()
                .immutable()
                .directed()
                .vertices(vertices)
                .edges((int) (vertices * 1.3))
                .connected()
                .build();
    }

    public static IGraph treeGraph(int vertices) {

        return Graphs.random()
                .immutable()
                .undirected()
                .vertices(vertices)
                .edges(vertices - 1)
                .connected()
                .build();
    }

    public static IGraph dag(int vertices) {

        return Graphs.patterns().dag(vertices);
    }

    public static IGraph bipartiteGraph(int vertices) {

        int left = vertices / 2;
        int right = vertices - left;

        return BipartiteGraphGenerator.generate(
                left,
                right,
                left + right
        );
    }

    public static IGraph bipartiteGraph(int left, int right) {

        return BipartiteGraphGenerator.generate(
                left,
                right,
                left + right
        );
    }

    public static IGraph bipartiteGraph(int left, int right, int edges) {

        return BipartiteGraphGenerator.generate(
                left,
                right,
                edges
        );
    }

    public GraphPresetGenerator presets() {
        return new GraphPresetGenerator();
    }

}
