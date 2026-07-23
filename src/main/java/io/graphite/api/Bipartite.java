package io.graphite.api;

import io.graphite.algorithm.bipartite.BFSBipartiteChecker;
import io.graphite.algorithm.bipartite.DFSBipartiteChecker;
import io.graphite.api.internal.GraphAPI;
import io.graphite.graph.IGraph;

/**
 * Provides bipartite graph algorithms.
 *
 * <p>The {@code Bipartite} service determines whether a graph can be
 * partitioned into two disjoint vertex sets such that every edge connects
 * vertices from different partitions.</p>
 *
 * <pre>{@code
 * BipartiteResult result =
 *         graph.bipartite().check();
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Typical Applications</h2>
=======
 * <h3>Typical Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 * <ul>
 *     <li>Matching problems</li>
 *     <li>Scheduling</li>
 *     <li>Assignment optimization</li>
 *     <li>Network partitioning</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see BFSBipartiteChecker
 * @see DFSBipartiteChecker
 * @see IGraph#bipartite()
 * @since 2.0
 */
public final class Bipartite extends GraphAPI {
    public Bipartite(IGraph graph) {
        super(graph);
    }

    public boolean bfs() {
        return BFSBipartiteChecker.INSTANCE.isBipartite(graph);
    }

    public boolean dfs() {
        return DFSBipartiteChecker.INSTANCE.isBipartite(graph);
    }
}
