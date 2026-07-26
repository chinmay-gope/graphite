package io.graphite.api;

import io.graphite.algorithm.topology.DFSTopologicalSort;
import io.graphite.algorithm.topology.KahnTopologicalSort;
import io.graphite.api.internal.GraphAPI;
import io.graphite.exception.algorithm.GraphCycleException;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.exception.graph.UnsupportedGraphTypeException;
import io.graphite.graph.IGraph;
import io.graphite.result.TopologicalSortResult;

/**
 * Provides topological ordering algorithms.
 *
 * <p>The {@code Topology} service computes a valid topological ordering
 * of directed acyclic graphs (DAGs). If the graph contains a cycle,
 * an exception is thrown.</p>
 *
 * <pre>{@code
 * TraversalResult order =
 *         graph.topology().topologicalSort();
 * }</pre>
 *

 * <h2>Available Algorithms</h2>

 * <h3>Available Algorithms</h3>

 * <ul>
 *     <li>DFS Topological Sort</li>
 *     <li>Kahn's Algorithm</li>
 * </ul>
 *

 * <h2>Typical Applications</h2>

 * <h3>Typical Applications</h3>

 * <ul>
 *     <li>Task scheduling</li>
 *     <li>Dependency resolution</li>
 *     <li>Build systems</li>
 *     <li>Course prerequisite planning</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see DFSTopologicalSort
 * @see KahnTopologicalSort
 * @see IGraph#topology()
 * @since 2.0
 */
public final class Topology extends GraphAPI {
    public Topology(IGraph graph) {
        super(graph);
    }

    /**
     * Computes a topological ordering of a directed acyclic graph (DAG)
     * using depth-first search.
     *
     * <p>A topological ordering arranges vertices so that every directed
     * edge points from an earlier vertex to a later vertex.</p>
     *
     * <p>This algorithm is applicable only to directed acyclic graphs.</p>
     *

     * <h2>Complexity</h2>

     * <h3>Complexity</h3>

     *
     * <ul>
     *     <li>Time: O(V + E)</li>
     *     <li>Space: O(V)</li>
     * </ul>
     *
     * @return the topological ordering
     * @throws NullGraphException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is undirected
     * @throws GraphCycleException       if the graph contains a cycle
     * @see #kahn()
     * @since 2.0
     */
    public TopologicalSortResult dfs() {
        return DFSTopologicalSort.INSTANCE.sort(graph);
    }

    /**
     * Computes a topological ordering using Kahn's algorithm.
     *
     * <p>Kahn's algorithm repeatedly removes vertices with zero
     * incoming edges until all vertices have been processed.</p>
     *

     * <h2>Complexity</h2>

     * <h3>Complexity</h3>

     *
     * <ul>
     *     <li>Time: O(V + E)</li>
     *     <li>Space: O(V)</li>
     * </ul>
     *
     * @return the topological ordering
     * @throws NullGraphException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is undirected
     * @throws GraphCycleException       if the graph contains a cycle
     * @see #dfs()
     * @since 2.0
     */
    public TopologicalSortResult kahn() {
        return KahnTopologicalSort.INSTANCE.sort(graph);
    }
}
