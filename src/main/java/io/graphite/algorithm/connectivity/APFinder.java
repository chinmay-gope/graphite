package io.graphite.algorithm.connectivity;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Connectivity;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.APResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds all articulation points (cut vertices) in an undirected graph.
 *
 * <p>An articulation point is a vertex whose removal disconnects the graph
 * or increases the number of connected components.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>
 *
 * <ul>
 *     <li>The graph must be undirected.</li>
 * </ul>
 *
 * <h2>Algorithm Overview</h2>
 * <h3>Algorithm Overview</h3>
 *
 * <p>This implementation applies Tarjan's low-link algorithm during
 * depth-first traversal to determine critical vertices.</p>
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
 *     <li>Fault tolerance analysis.</li>
 *     <li>Network resilience.</li>
 *     <li>Infrastructure planning.</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see BridgeFinder
 * @see Connectivity
 * @since 2.0
 */
public class APFinder extends GraphAlgorithm implements APAlgorithm {

    public static final APFinder INSTANCE = new APFinder();
    private int time;

    private APFinder() {
    }

    @Override
    public APResult findArticulationPoints(IGraph graph) {
        time = 0;
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        boolean[] visited = booleans(graph);

        int[] discovery = ints(graph, 0);
        int[] low = ints(graph, 0);

        boolean[] articulation = booleans(graph);

        for (int vertex : graph.activeVertices()) {
            if (!visited[vertex]) {
                dfs(
                        graph,
                        vertex,
                        -1,
                        visited,
                        discovery,
                        low,
                        articulation
                );
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < articulation.length; i++) {
            if (articulation[i]) {
                result.add(i);
            }
        }
        return new APResult(result);
    }

    private void dfs(
            IGraph graph,
            int current,
            int parent,
            boolean[] visited,
            int[] discovery,
            int[] low,
            boolean[] articulation) {

        visited[current] = true;
        discovery[current] = low[current] = ++time;

        int children = 0;

        for (Edge edge : neighbours(graph, current)) {
            int neighbour = edge.destination();

            if (neighbour == parent) {
                continue;
            }

            if (!visited[neighbour]) {
                children++;

                dfs(
                        graph,
                        neighbour,
                        current,
                        visited,
                        discovery,
                        low,
                        articulation
                );

                low[current] = Math.min(low[current], low[neighbour]);

                if (parent == -1 && children > 1) {
                    articulation[current] = true;
                }

                if (parent != -1 && low[neighbour] >= discovery[current]) {
                    articulation[current] = true;
                }

            } else {
                low[current] = Math.min(
                        low[current],
                        discovery[neighbour]
                );
            }
        }
    }
}
