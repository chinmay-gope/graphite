package io.graphite.algorithm.mst;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.MST;
import io.graphite.exception.graph.GraphDisconnectedException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.model.MSTNode;
import io.graphite.result.MSTEdge;
import io.graphite.result.MSTResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implements Prim's Minimum Spanning Tree (MST) algorithm.
 *
 * <p>Prim's algorithm constructs a minimum spanning tree by repeatedly
 * selecting the minimum-weight edge that connects a visited vertex to an
 * unvisited vertex. Starting from an arbitrary source, the tree grows until
 * all vertices have been included.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>The graph must be undirected.</li>
 *     <li>The graph must be connected.</li>
 *     <li>Edge weights are required.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This implementation uses a priority queue to efficiently select the
 * minimum-weight candidate edge at each step. Vertices already included in
 * the spanning tree are skipped to avoid cycles.</p>
 *
<<<<<<< HEAD
 * <h2>Complexity</h2>
=======
 * <h3>Complexity</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Time: O(E log V)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>When to Use</h2>
=======
 * <h3>When to Use</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>When the graph is connected and weighted.</li>
 *     <li>For sparse graphs using an adjacency-list representation.</li>
 *     <li>When building an MST from a chosen starting vertex.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Applications</h2>
=======
 * <h3>Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Communication network design</li>
 *     <li>Power distribution networks</li>
 *     <li>Road and railway planning</li>
 *     <li>Approximation algorithms</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Implementation Notes</h2>
=======
 * <h3>Implementation Notes</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This implementation validates that the graph is connected,
 * undirected, and weighted before execution.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Kruskal
 * @see MST
 * @see MSTResult
 * @see MSTEdge
 * @since 2.0
 */
public class Prim extends GraphAlgorithm {
    public static final Prim INSTANCE = new Prim();

    private Prim() {
    }

    public MSTResult findMST(IGraph graph, int source) {
        GraphPreconditions.requireGraph(graph);
<<<<<<< HEAD
=======
        GraphPreconditions.requireActiveVertex(graph, source);
>>>>>>> fec1ea5 (fix: java docs)
        GraphPreconditions.requireUndirected(graph);

        boolean[] visited = booleans(graph);

        PriorityQueue<MSTNode> queue = new PriorityQueue<>();

        List<MSTEdge> mst = new ArrayList<>();

        int cost = 0;
        queue.offer(new MSTNode(-1, source, 0));

        while (!queue.isEmpty()) {
            MSTNode current = queue.poll();

            int parent = current.parent();
            int vertex = current.vertex();
            int weight = current.weight();

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;

            if (parent != -1) {
                mst.add(new MSTEdge(parent, vertex, weight));
                cost += weight;
            }

            for (Edge edge : neighbours(graph, vertex)) {
                if (!visited[edge.destination()]) {

                    queue.offer(new MSTNode(vertex, edge.destination(), edge.weight()));
                }
            }
        }

        for (boolean vertexVisited : visited) {
            if (!vertexVisited) {
                throw new GraphDisconnectedException("Minimum spanning tree requires a connected graph.");
            }
        }

        return new MSTResult(cost, mst);
    }
}
