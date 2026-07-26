package io.graphite.result;

import io.graphite.algorithm.euler.EulerType;
import io.graphite.algorithm.euler.Hierholzer;
import io.graphite.api.Euler;

import java.util.List;

/**
 * Represents the result of an Euler path or Euler circuit computation.
 *
 * <p>An {@code EulerResult} stores the Eulerian traversal produced by the
 * algorithm together with information describing its validity.</p>
 *

 * <h2>Contents</h2>

 * <br>Contents</br>

 *
 * <ul>
 *     <li>Euler path or circuit</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Euler
 * @see Hierholzer
 * @since 2.0
 */
public record EulerResult(
        EulerType type,
        List<Integer> traversal) {

    public EulerResult {
        traversal = List.copyOf(traversal);
    }

    public boolean isCircuit() {
        return type == EulerType.CIRCUIT;
    }

    public boolean isPath() {
        return type == EulerType.PATH;
    }

    public int start() {
        return traversal.getFirst();
    }

    public int end() {
        return traversal.getLast();
    }

    public int edgeCount() {
        return traversal.size() - 1;
    }

    @Override
    public List<Integer> traversal() {
        return List.copyOf(traversal);
    }

    @Override
    public String toString() {
        return type + ": " + traversal;
    }
}
