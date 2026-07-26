package io.graphite.api;

import io.graphite.algorithm.mst.Kruskal;
import io.graphite.algorithm.mst.Prim;
import io.graphite.api.internal.GraphAPI;
import io.graphite.exception.graph.GraphDisconnectedException;
import io.graphite.exception.graph.GraphEmptyException;
import io.graphite.exception.graph.UnsupportedGraphTypeException;
import io.graphite.graph.IGraph;
import io.graphite.result.MSTResult;

/**
 * Provides Minimum Spanning Tree (MST) algorithms.
 *
 * <p>The {@code MST} service computes a minimum spanning tree for connected,
 * weighted, undirected graphs. A minimum spanning tree connects every vertex
 * using the minimum possible total edge weight without forming cycles.</p>
 *
 * <pre>{@code
 * MSTResult result = graph.mst().prim();
 *
 * MSTResult result = graph.mst().kruskal();
 * }</pre>
 *

 * <h2>Available Algorithms</h2>

 * <h3>Available Algorithms</h3>

 * <ul>
 *     <li>Prim's Algorithm</li>
 *     <li>Kruskal's Algorithm</li>
 * </ul>
 *

 * <h2>Typical Applications</h2>

 * <h3>Typical Applications</h3>

 * <ul>
 *     <li>Network design</li>
 *     <li>Road and utility planning</li>
 *     <li>Infrastructure optimization</li>
 *     <li>Hierarchical clustering</li>
 * </ul>
 *
 * <p>This service delegates execution to stateless singleton algorithm
 * implementations.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Prim
 * @see Kruskal
 * @see MSTResult
 * @see IGraph#mst()
 * @since 2.0
 */
public final class MST extends GraphAPI {
    public MST(IGraph graph) {
        super(graph);
    }

    /**
     * Computes a Minimum Spanning Tree (MST) using Prim's algorithm.
     *
     * <p>Prim's algorithm grows the spanning tree by repeatedly selecting
     * the minimum-weight edge that connects a visited vertex to an
     * unvisited vertex.</p>
     *
     * <p>This algorithm requires a connected, weighted, undirected graph.</p>
     *

     * <h2>Complexity</h2>

     * <h3>Complexity</h3>

     *
     * <ul>
     *     <li>Time: O((V + E) log V)</li>
     *     <li>Space: O(V)</li>
     * </ul>
     *
     * @return the computed minimum spanning tree
     * @throws GraphEmptyException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is directed
     * @throws GraphDisconnectedException    if the graph is disconnected
     * @see #kruskal()
     * @since 2.0
     */
    public MSTResult prim(int source) {
        return Prim.INSTANCE.findMST(graph, source);
    }

    /**
     * Computes a Minimum Spanning Tree (MST) using Kruskal's algorithm.
     *
     * <p>Kruskal's algorithm constructs the spanning tree by processing
     * edges in ascending order of weight while avoiding cycles using the
     * Union-Find data structure.</p>
     *
     * <p>This algorithm requires a connected, weighted, undirected graph.</p>
     *

     * <h2>Complexity</h2>

     * <h3>Complexity</h3>

     *
     * <ul>
     *     <li>Time: O(E log E)</li>
     *     <li>Space: O(V)</li>
     * </ul>
     *
     * @return the computed minimum spanning tree
     * @throws GraphEmptyException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is directed
     * @throws GraphDisconnectedException    if the graph is disconnected
     * @see #prim(int)
     * @since 2.0
     */
    public MSTResult kruskal() {
        return Kruskal.INSTANCE.findMST(graph);
    }
}
