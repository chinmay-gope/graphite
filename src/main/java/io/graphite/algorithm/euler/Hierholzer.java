package io.graphite.algorithm.euler;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Euler;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.exception.graph.GraphDisconnectedException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.EulerResult;
import io.graphite.util.GraphUtils;
import io.graphite.validation.GraphPreconditions;

import java.util.*;

/**
 * Implements Hierholzer's algorithm for finding Euler paths and Euler
 * circuits.
 *
 * <p>Hierholzer's algorithm constructs a Eulerian traversal by repeatedly
 * following unused edges and merging cycles until every edge has been
 * visited exactly once.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>The graph must satisfy the conditions for an Euler path or
 *     Euler circuit.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>The algorithm incrementally builds the Eulerian traversal using a
 * stack, removing edges as they are traversed and backtracking whenever no
 * unused edges remain.</p>
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
 *     <li>Route inspection</li>
 *     <li>Chinese Postman Problem</li>
 *     <li>Circuit traversal</li>
 *     <li>Genome assembly</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Euler
 * @see EulerResult
 * @since 2.0
 */
public class Hierholzer extends GraphAlgorithm implements EulerAlgorithm {

    public static final Hierholzer INSTANCE = new Hierholzer();

    private Hierholzer() {
    }

    @Override
    public EulerResult findEulerPath(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        if (!GraphUtils.isConnected(graph)) {
            throw new GraphDisconnectedException("Euler path requires a connected graph.");
        }

        int odd = countOddVertices(graph);

        if (odd != 0 && odd != 2) {
            throw new GraphCycleException("Graph does not contain an Euler circuit.");
        }

        int start = findStartVertex(graph);

        return new EulerResult(EulerType.PATH, buildEulerTraversal(graph, start));
    }


    @Override
    public EulerResult findEulerCircuit(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        if (!GraphUtils.isConnected(graph)) {
            throw new GraphDisconnectedException("Euler circuit requires a connected graph.");
        }

        if (countOddVertices(graph) != 0) {
            throw new GraphCycleException("Graph does not contain an Euler circuit.");
        }

        int start = findStartVertex(graph);

        return new EulerResult(EulerType.CIRCUIT, buildEulerTraversal(graph, start));

    }

    private int countOddVertices(IGraph graph) {
        int odd = 0;

        for (int i = 0; i < graph.getVertices(); i++) {

            if (graph.degree(i) % 2 != 0) {
                odd++;
            }
        }

        return odd;
    }

    private int findStartVertex(IGraph graph) {
        for (int i = 0; i < graph.getVertices(); i++) {
            if (graph.degree(i) % 2 != 0) {
                return i;
            }

        }

        for (int i = 0; i < graph.getVertices(); i++) {
            if (graph.degree(i) > 0) {
                return i;
            }
        }

        return 0;
    }

    private List<Integer> hierholzer(IGraph graph, int start) {

        IGraph clone = graph.copy();

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.peek();

            Edge edge = GraphUtils.getFirstNeighbour(clone, current);

            if (edge != null) {
                stack.push(edge.destination());

                clone.removeEdge(current, edge.destination());
            } else {
                path.add(stack.pop());
            }
        }

        Collections.reverse(path);
        return path;
    }

    private List<Integer> buildEulerTraversal(IGraph graph, int start) {

        List<Integer> traversal = hierholzer(graph, start);

        if (traversal.size() != graph.edgeCount() + 1) {
            throw new GraphCycleException("Invalid Euler traversal.");
        }

        return traversal;
    }
}
