package io.graphite.algorithm.connectivity;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Connectivity;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.BridgeResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements Kosaraju's algorithm for finding Strongly Connected Components
 * (SCCs) in directed graphs.
 *
 * <p>Kosaraju's algorithm identifies maximal groups of vertices where every
 * vertex is reachable from every other vertex in the same group. The
 * algorithm performs two depth-first searches: one to compute vertex finish
 * times and another on the transposed graph to discover strongly connected
 * components.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>
 *
 * <ul>
 *     <li>The graph must be directed.</li>
 * </ul>
 *
 * <h2>Algorithm Overview</h2>
 * <h3>Algorithm Overview</h3>
 *
 * <p>The algorithm performs a DFS to compute finishing order, reverses all
 * graph edges, and performs a second DFS in decreasing finish-time order to
 * identify each strongly connected component.</p>
 *
 * <h2>Complexity</h2>
 * <h3>Complexity</h3>
 *
 * <ul>
 *     <li>Time: O(V + E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *
 * <h2>When to Use</h2>
 * <h3>When to Use</h3>
 *
 * <ul>
 *     <li>Finding strongly connected components.</li>
 *     <li>Analyzing dependency graphs.</li>
 *     <li>Compiler optimization.</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Connectivity
 * @see DFS
 * @since 2.0
 */
public class BridgeFinder extends GraphAlgorithm implements BridgeAlgorithm {
    public static final BridgeFinder INSTANCE = new BridgeFinder();
    private int time;

    private BridgeFinder() {
    }

    @Override
    public BridgeResult findBridges(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        boolean[] visited = booleans(graph);

        int[] discovery = ints(graph, 0);
        int[] low = ints(graph, 0);

        List<Edge> bridges = new ArrayList<>();

        for (int vertex : graph.activeVertices()) {
            if (!visited[vertex]) {
                dfs(
                        graph,
                        vertex,
                        -1,
                        visited,
                        discovery,
                        low,
                        bridges
                );
            }
        }
        return new BridgeResult(bridges);
    }

    private void dfs(
            IGraph graph,
            int current,
            int parent,
            boolean[] visited,
            int[] discovery,
            int[] low,
            List<Edge> bridges) {
        visited[current] = true;
        discovery[current] = low[current] = time++;

        for (Edge edge : neighbours(graph, current)) {
            int neighbour = edge.destination();

            if (neighbour == parent) {
                continue;
            }

            if (!visited[neighbour]) {
                dfs(
                        graph,
                        neighbour,
                        current,
                        visited,
                        discovery,
                        low,
                        bridges
                );

                // Update the lowest discovery time reachable.
                low[current] = Math.min(low[current], low[neighbour]);

                // If the neighbor cannot reach an ancestor of current,
                // then current -> neighbor is a bridge.
                if (low[neighbour] > discovery[current]) {
                    bridges.add(new Edge(
                            current,
                            neighbour,
                            edge.weight()
                    ));
                }

            } else {
                // Back edge found.
                low[current] = Math.min(low[current], discovery[neighbour]);
            }
        }
    }
}
