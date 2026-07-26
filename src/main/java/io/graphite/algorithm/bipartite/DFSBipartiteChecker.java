package io.graphite.algorithm.bipartite;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Bipartite;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.validation.GraphPreconditions;
import io.graphite.validation.GraphValidator;

/**
 * Determines whether a graph is bipartite using Depth-First Search.
 *
 * <p>The algorithm recursively colors vertices while traversing the graph.
 * Adjacent vertices must always receive opposite colors.</p>
 *

 * <h2>Requirements</h2>
 * <h3>Requirements</h3>
 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 * </ul>
 *
 * <h2>Complexity</h2>
 * <h3>Complexity</h3>
 *
 * <ul>
 *     <li>Time: O(V + E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *
 * <h2>Applications</h2>
 * <h3>Applications</h3>
 *
 * <ul>
 *     <li>Graph coloring</li>
 *     <li>Matching problems</li>
 *     <li>Constraint satisfaction</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see BFSBipartiteChecker
 * @see Bipartite
 * @see DFS
 * @since 2.0
 */
public class DFSBipartiteChecker
        extends GraphAlgorithm
        implements BipartiteAlgorithm {

    public static final DFSBipartiteChecker INSTANCE = new DFSBipartiteChecker();

    private DFSBipartiteChecker() {
    }

    @Override
    public boolean isBipartite(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        if (GraphValidator.hasSelfLoop(graph)) {
            return false;
        }

        int[] color = ints(graph, -1);

        for (int i : graph.activeVertices()) {
            if (color[i] == -1) {
                if (dfs(graph, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(
            IGraph graph,
            int current,
            int[] color) {

        for (Edge edge : neighbours(graph, current)) {

            int neighbour = edge.destination();

            if (color[neighbour] == -1) {

                color[neighbour] = 1 - color[current];

                if (dfs(graph, neighbour, color)) {
                    return true;
                }
            } else if (color[neighbour] == color[current]) {
                return true;
            }
        }

        return false;
    }
}
