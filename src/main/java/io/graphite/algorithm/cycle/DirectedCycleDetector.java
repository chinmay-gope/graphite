package io.graphite.algorithm.cycle;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Cycle;
import io.graphite.api.Topology;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.validation.GraphPreconditions;

/**
 * Detects cycles in directed graphs.
 *
 * <p>This implementation performs a depth-first search while maintaining
 * a recursion stack. A cycle is detected whenever a back edge points to a
 * vertex that is currently on the recursion stack.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>The graph must be directed.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>The algorithm traverses each unvisited vertex using DFS. During
 * traversal, vertices currently being explored are tracked in a recursion
 * stack. Encountering a vertex already on the stack indicates a directed
 * cycle.</p>
 *
<<<<<<< HEAD
 * <h2>Complexity</h2>
=======
 * <h3>Complexity</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Time: O(V + E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Applications</h2>
=======
 * <h3>Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Dependency validation</li>
 *     <li>Deadlock detection</li>
 *     <li>Build systems</li>
 *     <li>Topological sort validation</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see UndirectedCycleDetector
 * @see Cycle
 * @see DFS
 * @since 2.0
 */
public class DirectedCycleDetector extends GraphAlgorithm implements CycleDetectionAlgorithm {

    public static final DirectedCycleDetector INSTANCE = new DirectedCycleDetector();

    private DirectedCycleDetector() {
    }

    /**
     * Determines whether the graph contains a cycle.
     *
     * <p>The appropriate cycle detection algorithm is selected
     * automatically based on the graph type.</p>
     *
<<<<<<< HEAD
     * <h2>Complexity</h2>
=======
     * <h3>Complexity</h3>
>>>>>>> fec1ea5 (fix: java docs)
     *
     * <ul>
     *     <li>Time: O(V + E)</li>
     *     <li>Space: O(V)</li>
     * </ul>
     *
     * @return {@code true} if the graph contains a cycle;
     * {@code false} otherwise
     * @throws NullGraphException if the graph is {@code null}
     * @see Topology#dfs()
     * @since 2.0
     */
    @Override
    public boolean hasCycle(IGraph graph) {
        GraphPreconditions.requireGraph(graph);

        boolean[] visited = booleans(graph);
        boolean[] recursionStack = booleans(graph);

        for (int i = 0; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                if (hasCycle(graph, i, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycle(IGraph graph, int current, boolean[] visited, boolean[] recursionStack) {
        visited[current] = true;
        recursionStack[current] = true;

        for (Edge edge : neighbours(graph, current)) {
            int neighbor = edge.destination();

            if (!visited[neighbor]) {
                if (hasCycle(graph, neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack[neighbor]) {
                return true;
            }
        }

        recursionStack[current] = false;
        return false;
    }
}
