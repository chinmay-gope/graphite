package io.graphite.algorithm.traversal;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Traversal;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TraversalResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Implements the Breadth-First Search (BFS) graph traversal algorithm.
 *
 * <p>Breadth-First Search explores a graph level by level, visiting all
 * neighbors of a vertex before continuing to the next level. Starting from
 * a source vertex, the algorithm guarantees that vertices are visited in
 * order of their shortest distance (measured in edges) from the source in
 * an unweighted graph.</p>
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
 * <p>This implementation uses a queue to process vertices in FIFO order.
 * Each vertex is visited at most once, producing an efficient linear-time
 * traversal.</p>
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
 *     <li>Shortest paths in unweighted graphs</li>
 *     <li>Connected component discovery</li>
 *     <li>Level-order graph exploration</li>
 *     <li>Network broadcasting</li>
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
 * @see DFS
 * @see Traversal
 * @see TraversalResult
 * @since 2.0
 */
public class BFS extends GraphAlgorithm implements TraversalAlgorithm {

    public static final BFS INSTANCE = new BFS();

    private BFS() {
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

        Queue<Integer> queue = new ArrayDeque<>();

        List<Integer> traversalOrder = new ArrayList<>();

        visited[source] = true;
        queue.offer(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            traversalOrder.add(current);

            for (Edge edge : neighbours(graph, current)) {
                int neighbor = edge.destination();

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return new TraversalResult(source, traversalOrder);
    }
}
