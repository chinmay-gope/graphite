package io.graphite.generator.pattern;

import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import io.graphite.graph.PatternGraphBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates Directed Acyclic Graphs (DAGs).
 *
 * <p>A Directed Acyclic Graph contains directed edges without forming any
 * directed cycles. DAGs are widely used to model dependency relationships,
 * scheduling problems, and compilation pipelines.</p>
 *

 * <h2>Applications</h2>

 * <h3>Applications</h3>

 *
 * <ul>
 *     <li>Task scheduling</li>
 *     <li>Dependency management</li>
 *     <li>Build systems</li>
 *     <li>Workflow modeling</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see PatternGraphBuilder
 * @since 2.0
 */
public final class DAGGenerator {

    private DAGGenerator() {
    }

    public static IGraph generate(
            int vertices,
            int edges) {

        if (vertices <= 0) {
            throw new InvalidGraphConfigurationException(
                    "DAG requires at least one vertex.");
        }

        int maxEdges = vertices * (vertices - 1) / 2;

        if (edges < vertices - 1) {
            throw new InvalidGraphConfigurationException(
                    "Connected DAG requires at least " + (vertices - 1) + " edges.");
        }

        if (edges > maxEdges) {
            throw new InvalidGraphConfigurationException(
                    "Maximum edges = " + maxEdges);
        }

        var builder = Graphs.directed()
                .vertices(vertices);

        ThreadLocalRandom random = ThreadLocalRandom.current();

        Set<EdgeKey> used = new HashSet<>();

        // Step 1: Build a connected backbone
        for (int i = 0; i < vertices - 1; i++) {
            builder.addEdge(i, i + 1);
            used.add(new EdgeKey(i, i + 1));
        }

        System.out.println("Generated edges = " + used.size());

        // Step 2: Add remaining forward edges
        while (used.size() < edges) {

            int source = random.nextInt(vertices - 1);
            int destination = random.nextInt(source + 1, vertices);

            EdgeKey edge = new EdgeKey(source, destination);

            if (used.add(edge)) {
                builder.addEdge(source, destination);
            }
        }

        return builder.build();
    }

    private record EdgeKey(
            int source,
            int destination) {
    }
}
