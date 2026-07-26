package io.graphite.algorithm.mst;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.api.MST;
import io.graphite.exception.graph.GraphDisconnectedException;
import io.graphite.graph.IGraph;
import io.graphite.model.DSU;
import io.graphite.model.Edge;
import io.graphite.result.MSTEdge;
import io.graphite.result.MSTResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implements Kruskal's Minimum Spanning Tree (MST) algorithm.
 *
 * <p>Kruskal's algorithm constructs a minimum spanning tree by sorting all
 * edges in non-decreasing order of weight and repeatedly selecting the next
 * lightest edge that does not create a cycle.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>

 *
 * <ul>
 *     <li>The graph must be undirected.</li>
 *     <li>The graph must be connected.</li>
 *     <li>Edge weights are required.</li>
 * </ul>
 *

 * <h2>Algorithm Overview</h2>

 * <h3>Algorithm Overview</h3>

 *
 * <p>This implementation sorts the edge set once and uses the Union-Find
 * (Disjoint Set Union) data structure to efficiently detect cycles while
 * constructing the spanning tree.</p>
 *

 * <h2>Complexity</h2>

 * <h3>Complexity</h3>

 *
 * <ul>
 *     <li>Time: O(E log E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *

 * <h2>When to Use</h2>

 * <h3>When to Use</h3>

 *
 * <ul>
 *     <li>For sparse weighted graphs.</li>
 *     <li>When the complete edge set is readily available.</li>
 *     <li>When Union-Find provides efficient cycle detection.</li>
 * </ul>
 *

 * <h2>Applications</h2>

 * <h3>Applications</h3>

 *
 * <ul>
 *     <li>Network optimization</li>
 *     <li>Infrastructure planning</li>
 *     <li>Image segmentation</li>
 *     <li>Hierarchical clustering</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>

 *
 * <p>This implementation relies on the Union-Find data structure with path
 * compression and union by rank to efficiently detect cycles.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see Prim
 * @see DSU
 * @see MST
 * @see MSTResult
 * @since 2.0
 */
public class Kruskal extends GraphAlgorithm {

    public static final Kruskal INSTANCE = new Kruskal();

    private Kruskal() {

    }

    public MSTResult findMST(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireUndirected(graph);

        DSU dsu = new DSU(graph.getVertices());
        List<Edge> edges = new ArrayList<>(edges(graph));
        List<MSTEdge> mst = new ArrayList<>();

//        mutable copy
        edges.sort(Comparator.comparingInt(Edge::weight));

        int cost = 0;

        for (Edge edge : edges) {
            int u = edge.source();
            int v = edge.destination();

            if (!dsu.connected(u, v)) {
                dsu.union(u, v);
                mst.add(new MSTEdge(
                        u,
                        v,
                        edge.weight()
                ));
                cost += edge.weight();
            }
        }

        if (mst.size() != graph.getVertices() - 1) {
            throw new GraphDisconnectedException(
                    "Minimum spanning tree requires a connected graph.");
        }

        return new MSTResult(cost, mst);
    }


}
