package io.graphite.validation;

import io.graphite.graph.IGraph;
import io.graphite.model.Edge;

/**
 * Provides utility methods for inspecting graph properties.
 *
 * <p>{@code GraphValidator} performs structural analysis of graphs,
 * allowing algorithms and applications to query characteristics such as
 * connectivity, edge weights, self-loops, and graph emptiness.</p>
 *
<<<<<<< HEAD
 * <h2>Supported Checks</h2>
=======
 * <h3>Supported Checks</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Empty graph detection</li>
 *     <li>Weighted graph detection</li>
 *     <li>Negative edge detection</li>
 *     <li>Self-loop detection</li>
 *     <li>Vertex validation</li>
 * </ul>
 *
 * <p>Unlike {@link GraphPreconditions}, this class reports graph
 * properties without enforcing algorithm-specific requirements.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see GraphPreconditions
 * @since 2.0
 */
public final class GraphValidator {
    private GraphValidator() {
    }

    public static boolean hasSelfLoop(IGraph graph) {
        for (int i = 0; i < graph.getVertices(); i++) {
            for (Edge edge : graph.getNeighbors(i)) {

                if (i == edge.destination()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasNegativeEdges(IGraph graph) {
        for (int i = 0; i < graph.getVertices(); i++) {
            for (Edge edge : graph.getNeighbors(i)) {
                if (edge.weight() < 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
