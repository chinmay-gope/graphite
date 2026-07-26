package io.graphite.result;

import io.graphite.algorithm.traversal.BFS;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Traversal;

import java.util.List;

/**
 * Represents the result of a graph traversal.
 *
 * <p>A {@code TraversalResult} stores the order in which vertices were
 * visited during algorithms such as Breadth-First Search (BFS),
 * Depth-First Search (DFS), and Topological Sort.</p>
 *

 * <h2>Contents</h2>

 * <br>Contents</br>

 *
 * <ul>
 *     <li>Traversal order</li>
 * </ul>
 *

 * <h2>Immutability</h2>
 *
 * <p>This result object is immutable and safely shareable between threads.</p>
 *
 * <h2>Usage</h2>

 * @param source source
 * @param traversalOrder traversal result
 *
 * <br>Usage</br>

 *
 * <pre>{@code
 * TraversalResult result = graph.traversal().bfs(0);
 * }</pre>
 *
 * @author Chinmay
 * @version 2.0
 * @see Traversal
 * @see BFS
 * @see DFS
 * @since 2.0
 */
public record TraversalResult(int source, List<Integer> traversalOrder) implements Colors {
    public TraversalResult {
        traversalOrder = List.copyOf(traversalOrder);
    }

    @Override
    public String toString() {
        return "TraversalResult{" +
                "source=" + GREEN + source + RESET +
                ", traversalOrder=" + MAGENTA + traversalOrder + RESET +
                '}';
    }
}
