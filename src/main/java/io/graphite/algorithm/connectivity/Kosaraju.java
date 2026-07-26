package io.graphite.algorithm.connectivity;

import io.graphite.algorithm.GraphAlgorithm;
import io.graphite.algorithm.traversal.DFS;
import io.graphite.api.Connectivity;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.result.SCCResult;
import io.graphite.validation.GraphPreconditions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Implements Kosaraju's algorithm for finding Strongly Connected Components
 * (SCCs) in directed graphs.
 *
 * <p>Kosaraju's algorithm identifies maximal groups of vertices where every
 * vertex is reachable from every other vertex in the same group. The
 * algorithm performs two depth-first searches: one to compute vertex finish
 * times and another on the transposed graph to discover strongly connected
 * components.</p>
 *

 * <h2>Requirements</h2>

 * <h3>Requirements</h3>

 *
 * <ul>
 *     <li>The graph must be directed.</li>
 * </ul>
 *

 * <h2>Algorithm Overview</h2>

 * <h3>Algorithm Overview</h3>

 *
 * <p>The algorithm performs a DFS to compute finishing order, reverses all
 * graph edges, and performs a second DFS in decreasing finish-time order to
 * identify each strongly connected component.</p>
 *

 * <h2>Complexity</h2>

 * <h3>Complexity</h3>

 *
 * <ul>
 *     <li>Time: O(V + E)</li>
 *     <li>Space: O(V)</li>
 * </ul>
 *

 * <h2>When to Use</h2>

 * <h3>When to Use</h3>

 *
 * <ul>
 *     <li>Finding strongly connected components.</li>
 *     <li>Analyzing dependency graphs.</li>
 *     <li>Compiler optimization.</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see Connectivity
 * @see DFS
 * @since 2.0
 */
public class Kosaraju extends GraphAlgorithm implements SCCAlgorithm {

    public static final Kosaraju INSTANCE = new Kosaraju();

    private Kosaraju() {
    }

    @Override
    public SCCResult findSCCs(IGraph graph) {
        GraphPreconditions.requireGraph(graph);
        GraphPreconditions.requireDirected(graph);

        boolean[] visited = booleans(graph);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int vertex : graph.activeVertices()) {

            if (!visited[vertex]) {
                fillOrder(graph, vertex, visited, stack);
            }
        }

        IGraph transpose = graph.transpose();

        visited = booleans(graph);

        List<List<Integer>> components = new ArrayList<>();

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                List<Integer> component = new ArrayList<>();
                dfs(transpose, vertex, visited, component);
                components.add(component);
            }
        }

        return new SCCResult(components);
    }

    private void fillOrder(
            IGraph graph,
            int current,
            boolean[] visited,
            Deque<Integer> stack) {

        visited[current] = true;

        for (Edge edge : neighbours(graph, current)) {
            int neighbour = edge.destination();

            if (!visited[neighbour]) {
                fillOrder(graph, neighbour, visited, stack);
            }
        }
        stack.push(current);
    }

    private void dfs(
            IGraph graph,
            int current,
            boolean[] visited,
            List<Integer> component) {
        visited[current] = true;
        component.add(current);

        for (Edge edge : neighbours(graph, current)) {
            int neighbour = edge.destination();
            if (!visited[neighbour]) {
                dfs(graph, neighbour, visited, component);
            }
        }
    }
}
