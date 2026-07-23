package io.graphite.algorithm.connectivity;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Connectivity;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.BiConnectedResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Finds all biconnected components in an undirected graph.
 *
 * <p>A biconnected component is a maximal subgraph that remains connected
 * after the removal of any single vertex.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>The graph must be undirected.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This implementation uses a depth-first search together with Tarjan's
 * low-link values and an edge stack to identify each biconnected
 * component.</p>
 *
<<<<<<< HEAD
 * <h2>Complexity</h2>
=======
 * <h3>Complexity</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Time: O(V + E)</li>
 *     <li>Space: O(V + E)</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Applications</h2>
=======
 * <h3>Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Network decomposition.</li>
 *     <li>Reliability analysis.</li>
 *     <li>Graph connectivity studies.</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see APFinder
 * @see BridgeFinder
 * @see Connectivity
 * @since 2.0
 */
public class BiconnectedComponents extends GraphAlgorithm
        implements BiconnectedAlgorithm {

    public static final BiconnectedComponents INSTANCE = new BiconnectedComponents();

    private BiconnectedComponents() {
    }

    @Override
    public BiConnectedResult findBiconnectedComponents(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        boolean[] visited = booleans(graph);
        int[] disc = ints(graph, 0);
        int[] low = ints(graph, 0);
        int[] parent = ints(graph, -1);

        Stack<Edge> stack = new Stack<>();
        List<List<Edge>> result = new ArrayList<>();

        int time = 0;

        for (int i = 0; i < graph.getVertices(); i++) {

            if (!visited[i]) {

                time = dfs(
                        graph,
                        i,
                        visited,
                        disc,
                        low,
                        parent,
                        stack,
                        result,
                        time
                );

                // Remaining edges belong to one BCC
                if (!stack.isEmpty()) {
                    List<Edge> component = new ArrayList<>();

                    while (!stack.isEmpty()) {
                        component.add(stack.pop());
                    }

                    result.add(component);
                }
            }
        }

        return new BiConnectedResult(result);
    }

    private int dfs(
            IGraph graph,
            int u,
            boolean[] visited,
            int[] disc,
            int[] low,
            int[] parent,
            Stack<Edge> stack,
            List<List<Edge>> result,
            int time
    ) {

        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (Edge edge : graph.getNeighbors(u)) {

            int v = edge.destination();

            Edge graphEdge = new Edge(
                    u,
                    v,
                    edge.weight()
            );

            if (!visited[v]) {
                parent[v] = u;
                stack.push(graphEdge);

                time = dfs(
                        graph,
                        v,
                        visited,
                        disc,
                        low,
                        parent,
                        stack,
                        result,
                        time
                );

                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= disc[u]) {
                    List<Edge> component = new ArrayList<>();

                    while (!stack.isEmpty()) {
                        Edge e = stack.pop();
                        component.add(e);

                        if ((e.source() == u && e.destination() == v)
                                || (e.source() == v && e.destination() == u)) {
                            break;
                        }
                    }

                    result.add(component);
                }

            } else if (v != parent[u] && disc[v] < disc[u]) {
                stack.push(graphEdge);

                low[u] = Math.min(low[u], disc[v]);
            }
        }

        return time;
    }
}
