package io.graphite.api;

import io.graphite.algorithm.traversal.BFS;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.internal.GraphAPI;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.IGraph;
import io.graphite.result.TraversalResult;

/**
 * Provides graph traversal algorithms.
 *
 * <p>The {@code Traversal} service exposes algorithms for exploring graph
 * structures from a specified source vertex. Traversal algorithms form the
 * foundation for many higher-level graph algorithms, including connectivity,
 * cycle detection, shortest paths, and spanning tree construction.</p>
 *
 * <p>Instances of this service are obtained directly from an
 * {@link IGraph} and are created lazily. The service itself is lightweight
 * and delegates algorithm execution to stateless singleton implementations.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.undirected()
 *         .addEdge(0,1)
 *         .addEdge(1,2)
 *         .build();
 *
 * TraversalResult bfs = graph.traversal().bfs(0);
 *
 * TraversalResult dfs = graph.traversal().dfs(0);
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Available Algorithms</h2>
=======
 * <h3>Available Algorithms</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Breadth-First Search (BFS)</li>
 *     <li>Depth-First Search (DFS)</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Typical Applications</h2>
=======
 * <h3>Typical Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Graph exploration</li>
 *     <li>Reachability analysis</li>
 *     <li>Connected component discovery</li>
 *     <li>Tree traversal</li>
 *     <li>Path reconstruction</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Implementation Notes</h2>
=======
 * <h3>Implementation Notes</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <p>This service contains no algorithmic state.
 * All algorithms are implemented as singleton instances and are safe to
 * invoke repeatedly.</p>
 *
 * @see BFS
 * @see DFS
 * @see TraversalResult
 * @since 2.0
 */
public final class Traversal extends GraphAPI {

    public Traversal(IGraph graph) {
        super(graph);
    }


    /**
     * Performs Breadth-First Search (BFS).
     *
     * <p>BFS explores vertices level by level beginning from the supplied
     * source vertex.</p>
     *
     * <p>Time Complexity: O(V + E)</p>
     *
     * <p>Space Complexity: O(V)</p>
     *
     * @param source starting vertex
     * @return traversal order produced by BFS
     * @throws InvalidVertexException if the source vertex is invalid
     */
    public TraversalResult bfs(int source) {
        return BFS.INSTANCE.traverse(graph, source);
    }


    /**
     * Performs Depth-First Search (DFS).
     *
     * <p>DFS explores the graph by visiting one branch completely before
     * backtracking.</p>
     *
     * <p>Time Complexity: O(V + E)</p>
     *
     * <p>Space Complexity: O(V)</p>
     *
     * @param source starting vertex
     * @return traversal order produced by DFS
     * @throws InvalidVertexException if the source vertex is invalid
     */
    public TraversalResult dfs(int source) {
        return DFS.INSTANCE.traverse(graph, source);
    }
}
