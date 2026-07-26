package io.graphite.validation;

import io.graphite.exception.algorithm.NegativeWeightException;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.exception.algorithm.UnsupportedWeightedGraphException;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.exception.graph.UnsupportedGraphTypeException;
import io.graphite.graph.IGraph;

/**
 * Provides reusable precondition checks for graph algorithms.
 *
 * <p>{@code GraphPreconditions} centralizes validation logic that verifies
 * algorithm requirements before execution. These checks ensure that graphs
 * satisfy the structural constraints expected by individual algorithms,
 * resulting in consistent error reporting throughout the library.</p>
 *

 * <h2>Supported Validations</h2>

 * <h3>Supported Validations</h3>

 *
 * <ul>
 *     <li>Null graph validation</li>
 *     <li>Directed and undirected graph requirements</li>
 *     <li>Connected graph verification</li>
 *     <li>Weighted graph verification</li>
 *     <li>Negative edge detection</li>
 *     <li>Vertex index validation</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>This class contains only static utility methods and cannot be
 * instantiated.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see GraphValidator
 * @see BuilderValidator
 * @since 2.0
 */
public final class GraphPreconditions {

    private GraphPreconditions() {
    }

    public static void requireGraph(IGraph graph) {

        if (graph == null) {
            throw new NullGraphException();
        }
    }

    public static void requireVertex(
            IGraph graph,
            int vertex) {

        requireGraph(graph);

        if (vertex < 0 || vertex >= graph.getVertices()) {
            throw new InvalidVertexException(vertex);
        }
    }



    public static void requireActiveVertex(
            IGraph graph,
            int vertex
    ) {

        if (!graph.isUsedVertex(vertex)) {
            throw new InvalidVertexException(vertex);
        }

    }

    public static void requireValidVertexIndex(
            IGraph graph,
            int vertex) {

        if (!graph.containsVertex(vertex)) {
            throw new InvalidVertexException(vertex);
        }
    }


    public static void requireDirected(
            IGraph graph) {

        requireGraph(graph);

        if (!graph.isDirected()) {
            throw new UnsupportedGraphTypeException(
                    true,
                    graph.isDirected());
        }
    }

    public static void requireUndirected(
            IGraph graph) {

        requireGraph(graph);

        if (!graph.isUndirected()) {
            throw new UnsupportedGraphTypeException(
                    false,
                    graph.isDirected());
        }
    }

    public static void requireWeighted(
            IGraph graph) {

        requireGraph(graph);

        if (!graph.isWeighted()) {
            throw new UnsupportedWeightedGraphException();
        }
    }

    public static void requireNonNegative(
            IGraph graph) {

        requireGraph(graph);

        if (GraphValidator.hasNegativeEdges(graph)) {
            throw new NegativeWeightException("Algorithm cannot be applied to graphs with negative edge weights.");
        }
    }
}
