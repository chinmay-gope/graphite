package io.graphite.algorithm.shortestpath;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.ShortestPath;
import io.graphite.exception.algorithm.NegativeCycleException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.ShortestPathResult;
import io.graphite.validation.GraphPreconditions;

import java.util.List;

/**
 * Implements the Bellman-Ford shortest path algorithm.
 *
 * <p>Bellman-Ford computes the shortest paths from a single source vertex,
 * even when the graph contains negative edge weights. It repeatedly relaxes
 * every edge until no shorter paths can be found and additionally detects
 * negative-weight cycles.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>

 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 *     <li>Negative edge weights are permitted.</li>
 *     <li>The source vertex must exist.</li>
 * </ul>
 *

 * <h2>Algorithm Overview</h2>

 * <h3>Algorithm Overview</h3>

 *
 * <p>The algorithm performs |V|-1 relaxation passes over all edges, followed
 * by one additional pass to determine whether a negative-weight cycle is
 * reachable from the source vertex.</p>
 *

 * <h2>Complexity</h2>

 * <h3>Complexity</h3>

 *
 * <ul>
 *     <li>Time: O(V × E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *

 * <h2>Applications</h2>

 * <h3>Applications</h3>

 *
 * <ul>
 *     <li>Currency arbitrage detection</li>
 *     <li>Network optimization</li>
 *     <li>Graphs containing negative edge weights</li>
 * </ul>
 *

 * <h2>When to Use</h2>

 * <h3>When to Use</h3>

 *
 * <ul>
 *     <li>When negative edge weights may exist.</li>
 *     <li>When negative cycle detection is required.</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>If a negative-weight cycle is detected, this implementation throws
 * {@link NegativeCycleException} because shortest paths are undefined.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Dijkstra
 * @see FloydWarshall
 * @see ShortestPath
 * @see NegativeCycleException
 * @since 2.0
 */
public class BellmanFord extends GraphAlgorithm implements ShortestPathAlgorithm {

    public static final BellmanFord INSTANCE = new BellmanFord();

    private BellmanFord() {
    }

    @Override
    public ShortestPathResult shortestPath(IGraph graph, int source) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireVertex(graph, source);

        int[] distance = ints(graph, Integer.MAX_VALUE);
        int[] parent = ints(graph, -1);

        distance[source] = 0;

        List<Edge> edges = edges(graph);

        relaxEdges(edges, distance, parent, graph.getVertices());

        detectNegativeCycle(edges, distance);

        return new ShortestPathResult(source, distance, parent);
    }

    private void relaxEdges(List<Edge> edges,
                            int[] distance,
                            int[] parent,
                            int vertices) {

        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {

                int u = edge.source();
                int v = edge.destination();
                int wt = edge.weight();

                if (distance[u] != Integer.MAX_VALUE &&
                        distance[u] + wt < distance[v]) {

                    distance[v] = distance[u] + wt;
                    parent[v] = u;
                }
            }
        }
    }

    private void detectNegativeCycle(List<Edge> edges, int[] distance) {
        for (Edge edge : edges) {
            int u = edge.source();
            int v = edge.destination();
            int wt = edge.weight();

            if (distance[u] + wt < distance[v] && distance[u] != Integer.MAX_VALUE) {

                throw new NegativeCycleException();
            }
        }
    }
}
