package io.graphite.graph;

import io.graphite.builder.Graphs;
import io.graphite.generator.pattern.*;
import io.graphite.generator.preset.GraphPresetGenerator;

/**
 * Builds predefined graph structures using a fluent API.
 *
 * <p>The {@code PatternGraphBuilder} creates commonly used graph topologies
 * such as complete graphs, trees, stars, wheels, grids, bipartite graphs,
 * and directed acyclic graphs.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.patterns()
 *         .completeGraph(20);
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Available Patterns</h2>
=======
 * <h3>Available Patterns</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Complete Graph</li>
 *     <li>Complete Bipartite Graph</li>
 *     <li>Tree</li>
 *     <li>Grid</li>
 *     <li>Wheel</li>
 *     <li>Star</li>
 *     <li>Directed Acyclic Graph (DAG)</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Graphs
 * @see GraphPresetGenerator
 * @since 2.0
 */
public final class PatternGraphBuilder {

    public static final PatternGraphBuilder INSTANCE = new PatternGraphBuilder();

    private PatternGraphBuilder() {
    }

    // Patterns
    public IGraph complete(int vertices) {
        return CompleteGraphGenerator.generate(vertices);
    }

    public IGraph cycle(int vertices) {
        return CycleGraphGenerator.generate(vertices);
    }

    public IGraph dag(int vertices) {
        return DAGGenerator.generate(vertices, vertices * 2);
    }

    public IGraph completeBipartite(int left, int right) {
        return CompleteBipartiteGraphGenerator.generate(left, right);
    }

    public IGraph tree(int vertices) {
        return TreeGraphGenerator.generate(vertices);
    }

    public IGraph star(int vertices) {
        return StarGraphGenerator.generate(vertices);
    }

    public IGraph wheel(int vertices) {
        return WheelGraphGenerator.generate(vertices);
    }

    public IGraph grid(int rows, int cols) {
        return GridGraphGenerator.generate(rows, cols);
    }

    public IGraph bipartite(int left, int right) {
        return BipartiteGraphGenerator.generate(left, right, left + right);
    }
}
