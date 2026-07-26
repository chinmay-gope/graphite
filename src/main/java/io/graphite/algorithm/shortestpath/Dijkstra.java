package io.graphite.algorithm.shortestpath;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.ShortestPath;
import io.graphite.exception.algorithm.NegativeWeightException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.model.VertexCost;
import io.graphite.result.ShortestPathResult;
import io.graphite.validation.GraphPreconditions;

import java.util.PriorityQueue;

/**
 * Implements Dijkstra's shortest path algorithm.
 *
 * <p>Dijkstra's algorithm computes the shortest paths from a single source
 * vertex to every reachable vertex in a weighted graph with non-negative
 * edge weights. It repeatedly selects the unvisited vertex with the smallest
 * known distance and relaxes its outgoing edges until all reachable vertices
 * have been processed.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>

 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 *     <li>All edge weights must be non-negative.</li>
 *     <li>The source vertex must exist.</li>
 * </ul>
 *

 * <h2>Algorithm Overview</h2>

 * <h3>Algorithm Overview</h3>

 *
 * <p>This implementation uses a priority queue (min-heap) to efficiently
 * select the next closest vertex. Whenever a shorter path is discovered,
 * the corresponding distance is updated through edge relaxation.</p>
 *

 * <h2>Complexity</h2>

 * <h3>Complexity</h3>

 *
 * <ul>
 *     <li>Time: O((V + E) log V)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *

 * <h2>Applications</h2>

 * <h3>Applications</h3>

 *
 * <ul>
 *     <li>GPS and navigation systems</li>
 *     <li>Network routing</li>
 *     <li>Game pathfinding</li>
 *     <li>Transportation planning</li>
 * </ul>
 *

 * <h2>When to Use</h2>

 * <h3>When to Use</h3>

 *
 * <ul>
 *     <li>When all edge weights are non-negative.</li>
 *     <li>When shortest paths are required from a single source.</li>
 *     <li>When performance is important on sparse graphs.</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>This implementation validates that the graph contains no negative edge
 * weights before execution. If a negative edge is detected, a
 * {@link NegativeWeightException} is thrown.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see BellmanFord
 * @see FloydWarshall
 * @see ShortestPath
 * @see ShortestPathResult
 * @since 2.0
 */
public class Dijkstra extends GraphAlgorithm implements ShortestPathAlgorithm {

    public static final Dijkstra INSTANCE = new Dijkstra();

    private Dijkstra() {
    }

    /**
     * Computes shortest paths using Dijkstra's algorithm.
     *
     * <p>This algorithm requires all edge weights to be non-negative.</p>
     *
     * <p>Time Complexity:
     * O((V + E) log V)</p>
     *
     * @throws NegativeWeightException if the graph contains negative edge weights
     */
    @Override
    public ShortestPathResult shortestPath(IGraph graph, int source) {
        GraphPreconditions.requireGraph(graph);

        GraphPreconditions.requireVertex(graph, source);

        GraphPreconditions.requireActiveVertex(graph, source);

        GraphPreconditions.requireNonNegative(graph);

        int[] distance = ints(graph, Integer.MAX_VALUE);
        int[] parent = ints(graph, -1);

        distance[source] = 0;

        PriorityQueue<VertexCost> queue = new PriorityQueue<>();

        queue.offer(new VertexCost(source, 0));

        while (!queue.isEmpty()) {
            VertexCost current = queue.poll();

            int u = current.vertex();

            /*
             *   Java's PriorityQueue doesn't support decrease-key.
             *   Instead of updating an existing entry, a new one is added.
             *   Ignore stale entries whose cost is no longer optimal.
             */
            if (current.cost() > distance[u]) {
                continue;
            }

            for (Edge edge : neighbours(graph, u)) {
                int v = edge.destination();
                int wt = edge.weight();

                if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {
                    distance[v] = distance[u] + wt;
                    parent[v] = u;

                    queue.offer(new VertexCost(v, distance[v]));
                }
            }
        }

        return new ShortestPathResult(source, distance, parent);
    }
}
