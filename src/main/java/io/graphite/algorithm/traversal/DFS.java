package io.graphite.algorithm.traversal;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Traversal;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TraversalResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Depth-First Search (DFS) graph traversal algorithm.
 *
 * <p>Depth-First Search explores a graph by following one branch as far as
 * possible before backtracking. It forms the basis of numerous graph
 * algorithms including cycle detection, topological sorting, bridge
 * detection, articulation point discovery, and strongly connected
 * components.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 *     <li>The source vertex must exist.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This implementation performs recursive depth-first traversal while
 * maintaining a visited set to prevent revisiting vertices.</p>
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
 *     <li>Cycle detection</li>
 *     <li>Topological sorting</li>
 *     <li>Connectivity analysis</li>
 *     <li>Path discovery</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Implementation Notes</h2>
=======
 * <h3>Implementation Notes</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This implementation is stateless and exposed as a singleton through
 * {@code INSTANCE}.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see BFS
 * @see Traversal
 * @see TraversalResult
 * @since 2.0
 */
public class DFS extends GraphAlgorithm implements TraversalAlgorithm {
    public static final DFS INSTANCE = new DFS();

    private DFS() {
    }

    @Override
    public TraversalResult traverse(IGraph graph, int source) {
        GraphPreconditions.requireGraph(graph);
<<<<<<< HEAD
        GraphPreconditions.requireVertex(graph, source);
=======
        GraphPreconditions.requireActiveVertex(graph, source);
>>>>>>> fec1ea5 (fix: java docs)

        boolean[] visited = booleans(graph);

        List<Integer> traversalOrder = new ArrayList<>();

        dfs(graph, source, visited, traversalOrder);

        return new TraversalResult(source, traversalOrder);
    }

    private void dfs(IGraph graph,
                     int source,
                     boolean[] visited,
                     List<Integer> traversalOrder) {
        visited[source] = true;
        traversalOrder.add(source);

        for (Edge edge : neighbours(graph, source)) {
            int neighbor = edge.destination();

            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, traversalOrder);
            }
        }
    }
}
