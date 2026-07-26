package io.graphite.algorithm.shortestpath;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.ShortestPath;
import io.graphite.exception.algorithm.NegativeCycleException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.AllPairsShortestPathResult;
import io.graphite.result.ShortestPathResult;
import io.graphite.validation.GraphPreconditions;

import java.util.Arrays;

/**
 * Implements the Floyd-Warshall all-pairs shortest path algorithm.
 *
 * <p>Floyd-Warshall computes the shortest distances between every pair of
 * vertices in a weighted graph. Unlike single-source algorithms, it produces
 * a complete distance matrix in a single execution.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>

 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 *     <li>Negative edge weights are permitted.</li>
 *     <li>Negative-weight cycles must not exist.</li>
 * </ul>
 *

 * <h2>Algorithm Overview</h2>

 * <h3>Algorithm Overview</h3>

 *
 * <p>The algorithm applies dynamic programming by progressively considering
 * each vertex as an intermediate point between every pair of vertices,
 * updating the shortest known distances whenever a shorter path is found.</p>
 *

 * <h2>Complexity</h2>

 * <h3>Complexity</h3>

 *
 * <ul>
 *     <li>Time: O(V³)</li>
 *     <li>Space: O(V²)</li>
 * </ul>
 *

 * <h2>Applications</h2>

 * <h3>Applications</h3>

 *
 * <ul>
 *     <li>Complete network analysis</li>
 *     <li>Routing tables</li>
 *     <li>Distance matrix computation</li>
 *     <li>Graph analytics</li>
 * </ul>
 *

 * <h2>When to Use</h2>

 * <h3>When to Use</h3>

 * <ul>
 *     <li>When shortest paths are needed between every pair of vertices.</li>
 *     <li>For small or dense graphs where an all-pairs solution is preferable.</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>This algorithm is best suited for small to medium-sized dense graphs
 * where the shortest paths between every pair of vertices are required.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Dijkstra
 * @see BellmanFord
 * @see ShortestPath
 * @see ShortestPathResult
 * @since 2.0
 */
public class FloydWarshall extends GraphAlgorithm implements AllPairsShortestPathAlgorithm {

    public static final FloydWarshall INSTANCE = new FloydWarshall();
    public static final int INF = Integer.MAX_VALUE;

    private FloydWarshall() {
    }

    @Override
    public AllPairsShortestPathResult shortestPaths(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        int vertices = graph.getVertices();

        int[][] distance = createDistanceMatrix(graph);

        for (int via = 0; via < vertices; via++) {
            for (int from = 0; from < vertices; from++) {
                for (int to = 0; to < vertices; to++) {

                    if (distance[from][via] == INF || distance[via][to] == INF) {
                        continue;
                    }

                    if (distance[from][via] + distance[via][to] < distance[from][to]) {
                        distance[from][to] = distance[from][via] + distance[via][to];
                    }
                }
            }
        }

        detectNegativeCycle(distance);
        return new AllPairsShortestPathResult(distance);
    }

    private void detectNegativeCycle(int[][] distance) {

        for (int i = 0; i < distance.length; i++) {
            if (distance[i][i] < 0) {
                throw new NegativeCycleException();
            }
        }
    }

    private int[][] createDistanceMatrix(IGraph graph) {
        int V = graph.getVertices();
        int[][] distance = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;

            for (Edge edge : neighbours(graph, i)) {
                distance[i][edge.destination()] = edge.weight();
            }
        }
        return distance;
    }
}
