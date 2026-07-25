package io.graphite.algorithm.topology;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.Topology;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.TopologicalSortResult;
import io.graphite.validation.GraphPreconditions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements Kahn's algorithm for topological sorting.
 *
 * <p>Kahn's algorithm repeatedly removes vertices with zero incoming edges,
 * producing a valid topological ordering of a directed acyclic graph.</p>
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
 * <p>The algorithm maintains the in-degree of every vertex and processes
 * vertices whose in-degree becomes zero until all vertices have been
 * ordered.</p>
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
 *     <li>Dependency management</li>
 *     <li>Compilation order</li>
 *     <li>Course scheduling</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see DFSTopologicalSort
 * @see Topology
 * @since 2.0
 */
public class KahnTopologicalSort extends GraphAlgorithm implements TopologicalAlgorithm {

    public static final KahnTopologicalSort INSTANCE = new KahnTopologicalSort();

    private KahnTopologicalSort() {
    }

    @Override
    public TopologicalSortResult sort(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireDirected(graph);

        int vertices = graph.getVertices();

        int[] indegree = ints(graph, 0);
//        Step 1:Calculate indegree of every vertex
        for (int u = 0; u < vertices; u++) {
            indegree[u] = 0;
            for (Edge edge : graph.getNeighbors(u)) {
                indegree[edge.destination()]++;
            }
        }
//        Step 2: Add all vertices with indegree 0
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> order = new LinkedList<>();

//         Step 3: Queue Processing
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);

            for (Edge edge : graph.getNeighbors(u)) {
                indegree[edge.destination()]--;

                if (indegree[edge.destination()] == 0) {
                    queue.add(edge.destination());
                }

            }
        }

//         Step 4: Cycle detection
        if (order.size() != vertices) {
            throw new GraphCycleException();
        }

        return new TopologicalSortResult(order);
    }
}
