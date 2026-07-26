package io.graphite.api;

import io.graphite.algorithm.cycle.DirectedCycleDetector;
import io.graphite.algorithm.cycle.UndirectedCycleDetector;
import io.graphite.api.internal.GraphAPI;
import io.graphite.graph.IGraph;

/**
 * Provides graph cycle detection algorithms.
 *
 * <p>The {@code Cycle} service determines whether directed or undirected
 * graphs contain cycles. Separate algorithms are used depending on the
 * graph type.</p>
 *
 * <pre>{@code
 * boolean cycle = graph.cycle().hasCycle();
 * }</pre>
 *

 * <h2>Typical Applications</h2>

 * <h3>Typical Applications</h3>

 * <ul>
 *     <li>Dependency validation</li>
 *     <li>Deadlock detection</li>
 *     <li>Topological validation</li>
 *     <li>Graph verification</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see DirectedCycleDetector
 * @see UndirectedCycleDetector
 * @see IGraph#cycle()
 * @since 2.0
 */
public final class Cycle extends GraphAPI {
    public Cycle(IGraph graph) {
        super(graph);
    }

    public boolean directed() {
        return DirectedCycleDetector.INSTANCE.hasCycle(graph);
    }

    public boolean undirected() {
        return UndirectedCycleDetector.INSTANCE.hasCycle(graph);
    }
}
