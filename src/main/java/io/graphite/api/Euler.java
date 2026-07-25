package io.graphite.api;

import io.graphite.algorithm.euler.Hierholzer;
import io.graphite.api.internal.GraphAPI;
import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.graph.IGraph;
import io.graphite.result.EulerResult;

/**
 * Provides Euler path and circuit algorithms.
 *
 * <p>The {@code Euler} service determines whether a graph contains an
 * Euler path or Euler circuit and constructs one when it exists.</p>
 *
 * <pre>{@code
 * EulerResult result =
 *         graph.euler().findEulerCircuit();
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Typical Applications</h2>
=======
 * <h3>Typical Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 * <ul>
 *     <li>Route planning</li>
 *     <li>Chinese Postman Problem</li>
 *     <li>Network traversal</li>
 *     <li>Circuit design</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Hierholzer
 * @see EulerResult
 * @see IGraph#euler()
 * @since 2.0
 */
public final class Euler extends GraphAPI {

    public Euler(IGraph graph) {
        super(graph);
    }

    /**
     * Determines whether the graph contains an Euler path.
     *
     * <p>An Euler path visits every edge exactly once.</p>
     *
     * <p>
     * Computes an Euler path using Hierholzer's algorithm.
     * </p>
     *
     * <p>If an Euler path exists, the returned traversal visits every
     * edge exactly once.</p>
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
     * @return {@code true} if an Euler path exists;
     * {@code false} otherwise
     * @throws NullGraphException if the graph is {@code null}
     * @see #circuit()
     * @since 2.0
     */
    public EulerResult path() {
        return Hierholzer.INSTANCE.findEulerPath(graph);
    }

    /**
     * Determines whether the graph contains an Euler circuit.
     * <p>
     * Computes an Euler circuit using Hierholzer's algorithm.
     * <p>
     * <p>
     * If an Euler circuit exists, the returned traversal starts and
     * ends at the same vertex while visiting every edge exactly once.</p>
     *
     * <p>An Euler circuit is an Euler path that starts and ends at the
     * same vertex.</p>
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
     * @return {@code true} if an Euler circuit exists;
     * {@code false} otherwise
     * @throws NullGraphException if the graph is {@code null}
     * @see #path()
     * @since 2.0
     */
    public EulerResult circuit() {
        return Hierholzer.INSTANCE.findEulerCircuit(graph);
    }
}
