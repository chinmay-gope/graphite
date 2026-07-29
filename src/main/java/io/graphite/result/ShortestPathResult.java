package io.graphite.result;

import io.graphite.algorithm.shortestpath.BellmanFord;
import io.graphite.algorithm.shortestpath.Dijkstra;
import io.graphite.algorithm.shortestpath.FloydWarshall;
import io.graphite.api.ShortestPath;
import io.graphite.exception.graph.InvalidGraphConfigurationException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the result of the shortest path computation.
 *
 * <p>A {@code ShortestPathResult} contains the computed shortest distances
 * from a source vertex and, when available, predecessor information for
 * path reconstruction.</p>
 *
 *
 * <h2>Contents</h2>
 *
 * <ul>
 *     <li>Distance array</li>
 *     <li>Parent / predecessor array</li>
 * </ul>
 *
 * <h2>Immutability</h2>
 *
 * @param source   source vertex
 * @param distance distance array
 * @param parent   predecessor array
 *
 *
 *                 <p>All internal arrays are defensively copied to preserve immutability.</p>
 * @author Chinmay
 * @version 2.0
 * @see ShortestPath
 * @see Dijkstra
 * @see BellmanFord
 * @see FloydWarshall
 * @since 2.0
 */
public record ShortestPathResult(int source, int[] distance, int[] parent) {

    public ShortestPathResult {
        Objects.requireNonNull(distance, "distances cannot be null");
        Objects.requireNonNull(parent, "parent cannot be null");

        distance = distance.clone();
        parent = parent.clone();

        if (distance.length != parent.length) {
            throw new InvalidGraphConfigurationException("distances and parents must have same length.");
        }
    }

    @Override
    public int[] distance() {
        return distance.clone();
    }

    @Override
    public int[] parent() {
        return parent.clone();
    }

    public int distanceTo(int vertex) {
        return distance[vertex];
    }

    public boolean isReachable(int vertex) {
        return distance[vertex] != Integer.MAX_VALUE;
    }

    public int predecessorOf(int vertex) {
        return parent[vertex];
    }

    public int vertexCount() {
        return distance.length;
    }

    public List<Integer> pathTo(int destination) {

        if (!isReachable(destination)) {
            return List.of();
        }

        LinkedList<Integer> path = new LinkedList<>();

        for (int i = destination; i != -1; i = parent[i]) {

            path.addFirst(i);
        }

        return List.copyOf(path);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Source: ").append(source).append(System.lineSeparator()).append(System.lineSeparator());

        // Header row
        builder.append(String.format("%-8s %-10s %-10s%n", "Vertex", "Distance", "Parent"));

        for (int i = 0; i < distance.length; i++) {

            String dist = (distance[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(distance[i]);
            String parentStr = (parent[i] == -1) ? "-" : String.valueOf(parent[i]);

            builder.append(String.format("%-8d %-10s %-10s%n", i, dist, parentStr));
        }

        return builder.toString();
    }

}
