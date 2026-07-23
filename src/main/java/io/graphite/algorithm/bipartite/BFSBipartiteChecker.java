package io.graphite.algorithm.bipartite;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.traversal.BFS;
import io.graphite.api.Bipartite;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.validation.GraphPreconditions;
import io.graphite.validation.GraphValidator;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Determines whether a graph is bipartite using Breadth-First Search.
 *
 * <p>The algorithm colors vertices using two colors while performing BFS.
 * If two adjacent vertices receive the same color, the graph is not
 * bipartite.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 * </ul>
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
 *     <li>Matching problems</li>
 *     <li>Scheduling</li>
 *     <li>Graph coloring</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see DFSBipartiteChecker
 * @see Bipartite
 * @see BFS
 * @since 2.0
 */
public class BFSBipartiteChecker extends GraphAlgorithm implements BipartiteAlgorithm {

    public static final BFSBipartiteChecker INSTANCE = new BFSBipartiteChecker();

    private BFSBipartiteChecker() {

    }

    /**
     * Determines whether the graph is bipartite.
     *
     * <p>A graph is bipartite if its vertices can be divided into two
     * disjoint sets such that every edge connects vertices from
     * different sets.</p>
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
     * @return {@code true} if the graph is bipartite;
     * {@code false} otherwise
     * @throws NullGraphException if the graph is {@code null}
     * @since 2.0
     */
    @Override
    public boolean isBipartite(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        if (GraphValidator.hasSelfLoop(graph)) {
            return false;
        }

        int[] color = ints(graph, -1);

        for (int i = 0; i < graph.getVertices(); i++) {
            if (color[i] == -1) {
                if (!bfs(graph, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(IGraph graph, int source, int[] color) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        color[source] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : neighbours(graph, current)) {
                int neighbour = edge.destination();

                // Not booleans yet
                if (color[neighbour] == -1) {
                    // Assign opposite color
                    color[neighbour] = 1 - color[current];
                    queue.offer(neighbour);
                }

                // Already booleans and same color
                else if (color[neighbour] == color[current]) {
                    return false;
                }

            }
        }

        return true;
    }
}
