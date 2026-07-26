package io.graphite.print.formatter;

import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.print.GraphFormatter;
import io.graphite.print.GraphPrinter;
import io.graphite.result.Colors;

/**
 * Formats a graph using Graphite's default compact representation.
 *
 * <p>The compact formatter produces a concise, human-readable view of a
 * graph by displaying each vertex together with its adjacent vertices. It is
 * intended for console output, debugging, examples, and general inspection
 * of graph structure.</p>
 *

 * <h2>Output Characteristics</h2>

 * <h3>Output Characteristics</h3>

 *
 * <ul>
 *     <li>Readable adjacency-list layout</li>
 *     <li>Supports directed and undirected graphs</li>
 *     <li>Displays edge weights when available</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see GraphPrinter
 * @see GraphFormatter
 * @since 2.0
 */
public final class CompactFormatter implements GraphFormatter, Colors {

    @Override
    public String format(IGraph graph) {
        StringBuilder out = new StringBuilder();

        for (int vertex = 0; vertex < graph.vertexCount(); vertex++) {
            // Vertex styled in bold cyan
            out.append(BOLD).append(CYAN).append(vertex).append(RESET)
                    .append(" ").append(MAGENTA).append("-> ").append(RESET);

            var neighbours = graph.getNeighbors(vertex);

            for (int i = 0; i < neighbours.size(); i++) {
                Edge edge = neighbours.get(i);

                // Destination styled in green
                out.append(GREEN).append(edge.destination()).append(RESET);

                if (graph.isWeighted()) {
                    // Weight styled in yellow
                    out.append(YELLOW).append("(")
                            .append(edge.weight())
                            .append(")").append(RESET);
                }

                if (i < neighbours.size() - 1) {
                    // Separator styled in white
                    out.append(WHITE).append(", ").append(RESET);
                }
            }

            out.append('\n');
        }

        return out.toString();
    }

}
