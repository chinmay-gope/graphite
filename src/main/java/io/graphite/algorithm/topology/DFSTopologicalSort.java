package io.graphite.algorithm.topology;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.cycle.CycleDetectionAlgorithm;
import io.graphite.algorithm.cycle.DirectedCycleDetector;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Topology;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TopologicalSortResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Implements topological sorting using Depth-First Search.
 *
 * <p>This algorithm produces a valid topological ordering of a directed
 * acyclic graph (DAG) by recording vertices in reverse order of their DFS
 * finishing times.</p>
 *
<<<<<<< HEAD
 * <h2>Requirements</h2>
=======
 * <h3>Requirements</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>The graph must be directed.</li>
 *     <li>The graph must be acyclic.</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Algorithm Overview</h2>
=======
 * <h3>Algorithm Overview</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>Vertices are explored using DFS. Each vertex is added to the ordering
 * after all of its outgoing neighbors have been processed.</p>
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
 *     <li>Task scheduling</li>
 *     <li>Build systems</li>
 *     <li>Dependency resolution</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see KahnTopologicalSort
 * @see Topology
 * @see DFS
 * @since 2.0
 */
public class DFSTopologicalSort extends GraphAlgorithm implements TopologicalAlgorithm {

    public static final DFSTopologicalSort INSTANCE = new DFSTopologicalSort();

    private DFSTopologicalSort() {
    }

    @Override
    public TopologicalSortResult sort(IGraph graph) {
        GraphPreconditions.requireDirected(graph);

        CycleDetectionAlgorithm detector = DirectedCycleDetector.INSTANCE;

        if (detector.hasCycle(graph)) {
            throw new GraphCycleException("Topological sort requires a Directed Acyclic Graph (DAG).");
        }

        boolean[] visited = booleans(graph);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                topo(graph, i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return new TopologicalSortResult(result);
    }

    private void topo(IGraph graph, int i, boolean[] visited, Deque<Integer> stack) {
        visited[i] = true;
        for (Edge edge : neighbours(graph, i)) {
            int neighbor = edge.destination();
            if (!visited[neighbor]) {
                topo(graph, neighbor, visited, stack);
            }
        }
        stack.push(i);
    }
}
