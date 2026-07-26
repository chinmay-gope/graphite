package io.graphite.io.reader;

import io.graphite.builder.AbstractGraphBuilder;
import io.graphite.builder.Graphs;
import io.graphite.graph.IGraph;
import io.graphite.io.GraphReader;
import io.graphite.io.writer.PrettyEdgeListWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads graphs from an edge list representation.
 *
 * <p>{@code EdgeListReader} constructs graph instances by parsing edge list
 * data from supported input sources. Each record describes a connection
 * between two vertices and may optionally include an edge weight.</p>
 *
 * <p>The reader supports both directed and undirected graphs and performs
 * validation while constructing the resulting graph.</p>
 *

 * <h2>Supported Features</h2>

 * <h3>Supported Features</h3>

 *
 * <ul>
 *     <li>Directed and undirected graphs</li>
 *     <li>Weighted and unweighted edges</li>
 *     <li>Automatic graph construction</li>
 *     <li>Input validation</li>
 * </ul>
 *

 * <h2>Input Format</h2>

 * <h3>Input Format</h3>

 *
 * <pre>
 * source destination
 * source  weight
 * </pre>
 *
 * @author Chinmay
 * @version 2.0
 * @see PrettyEdgeListWriter
 * @see IGraph
 * @since 2.0
 */
public final class EdgeListReader implements GraphReader {

    @Override
    public IGraph read(Path path) throws IOException {

        List<String> lines = Files.readAllLines(path);

        int vertices = 0;
        boolean directed = false;
        boolean weighted = false;

        // ------------------------------------
        // Read Graph Metadata
        // ------------------------------------

        for (String line : lines) {

            line = line.trim();

            if (line.startsWith("Vertices")) {

                vertices = Integer.parseInt(
                        line.split(":")[1].trim()
                );

            } else if (line.startsWith("Directed")) {

                directed = line
                        .split(":")[1]
                        .trim()
                        .equalsIgnoreCase("Yes");

            } else if (line.startsWith("Weighted")) {

                weighted = line
                        .split(":")[1]
                        .trim()
                        .equalsIgnoreCase("Yes");
            }
        }

        // ------------------------------------
        // Create Builder
        // ------------------------------------

        AbstractGraphBuilder<?, ?> builder =
                directed
                        ? Graphs.directed()
                        : Graphs.undirected();

        builder.vertices(vertices)
                .weighted(weighted);

        // ------------------------------------
        // Read Edge List
        // ------------------------------------

        boolean readingEdges = false;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("Index")) {
                readingEdges = true;
                continue;
            }

            if (line.startsWith("---")) {
                continue;
            }

            if (!readingEdges || line.isBlank()) {
                continue;
            }

            // Example: [001] 0 1 5
            String[] tokens = line
                    .replace("[", "")
                    .replace("]", "")
                    .trim()
                    .split("\\s+");

            if (tokens.length < 3) {
                continue;
            }

            int source = Integer.parseInt(tokens[1]);
            int destination = Integer.parseInt(tokens[2]);

            if (weighted) {
                int weight = Integer.parseInt(tokens[3]);
                builder.addEdge(source, destination, weight);
            } else {
                builder.addEdge(source, destination);
            }
        }


        return builder.build();
    }
}
