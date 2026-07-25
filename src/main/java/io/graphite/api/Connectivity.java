package io.graphite.api;

import io.graphite.algorithm.connectivity.APFinder;
import io.graphite.algorithm.connectivity.BiconnectedComponents;
import io.graphite.algorithm.connectivity.BridgeFinder;
import io.graphite.algorithm.connectivity.Kosaraju;
import io.graphite.api.internal.GraphAPI;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.exception.graph.UnsupportedGraphTypeException;
import io.graphite.graph.IGraph;
import io.graphite.result.APResult;
import io.graphite.result.BiConnectedResult;
import io.graphite.result.BridgeResult;
import io.graphite.result.SCCResult;

/**
 * Provides graph connectivity algorithms.
 *
 * <p>The {@code Connectivity} service analyzes the structural connectivity
 * of graphs, including connected components, strongly connected components,
 * bridges, articulation points, and biconnected components.</p>
 *
 * <pre>{@code
 * graph.connectivity().connectedComponents();
 *
 * graph.connectivity().stronglyConnectedComponents();
 *
 * graph.connectivity().bridges();
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Available Algorithms</h2>
=======
 * <h3>Available Algorithms</h3>
>>>>>>> fec1ea5 (fix: java docs)
 * <ul>
 *     <li>Connected Components</li>
 *     <li>Strongly Connected Components (Kosaraju)</li>
 *     <li>Bridge Detection</li>
 *     <li>Articulation Points</li>
 *     <li>Biconnected Components</li>
 * </ul>
 *
<<<<<<< HEAD
 * <h2>Typical Applications</h2>
=======
 * <h3>Typical Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 * <ul>
 *     <li>Network reliability</li>
 *     <li>Critical infrastructure analysis</li>
 *     <li>Dependency analysis</li>
 *     <li>Social network analysis</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Kosaraju
 * @see BridgeFinder
 * @see APFinder
 * @see BiconnectedComponents
 * @see IGraph#connectivity()
 * @since 2.0
 */
public final class Connectivity extends GraphAPI {

    public Connectivity(IGraph graph) {
        super(graph);
    }


    public APResult articulationPoints() {
        return APFinder.INSTANCE.findArticulationPoints(graph);
    }

    /**
     * Identifies all bridges in the graph.
     *
     * <p>A bridge is an edge whose removal increases the number of connected
     * components in the graph.</p>
     *
     * <p>This algorithm is applicable only to undirected graphs.</p>
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
     * @return all bridge edges
     * @throws NullGraphException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is directed
     * @since 2.0
     */
    public BridgeResult bridges() {
        return BridgeFinder.INSTANCE.findBridges(graph);
    }

    /**
     * Computes the connected components of an undirected graph.
     *
     * <p>A connected component is a maximal set of vertices in which every
     * vertex is reachable from every other vertex.</p>
     *
     * <p>This algorithm is applicable only to undirected graphs.</p>
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
     * @return the connected components of the graph
     * @throws NullGraphException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is directed
     * @see #stronglyConnectedComponents()
     * @since 2.0
     */
    public BiConnectedResult biconnectedComponents() {
        return BiconnectedComponents.INSTANCE.findBiconnectedComponents(graph);
    }

    /**
     * Computes the strongly connected components (SCCs) of a directed graph
     * using Kosaraju's algorithm.
     *
     * <p>A strongly connected component is a maximal set of vertices in
     * which every vertex is reachable from every other vertex.</p>
     *
     * <p>This algorithm is applicable only to directed graphs.</p>
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
     * @return the strongly connected components
     * @throws NullGraphException        if the graph is {@code null}
     * @throws UnsupportedGraphTypeException if the graph is undirected
     * @see #biconnectedComponents()
     * @since 2.0
     */
    public SCCResult stronglyConnectedComponents() {
        return Kosaraju.INSTANCE.findSCCs(graph);
    }
}
