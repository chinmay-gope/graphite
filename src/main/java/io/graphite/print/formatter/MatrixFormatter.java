package io.graphite.print.formatter;

import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.print.GraphFormatter;
import io.graphite.print.GraphPrinter;
import io.graphite.result.Colors;

/**
 * Formats a graph as an adjacency matrix.
 *
 * <p>The adjacency matrix represents graph connectivity using rows and
 * columns corresponding to vertices. Matrix entries indicate whether an
 * edge exists and may include edge weights for weighted graphs.</p>
 *
<<<<<<< HEAD
 * <h2>Applications</h2>
=======
 * <h3>Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Dense graph visualization</li>
 *     <li>Matrix-based algorithms</li>
 *     <li>Educational examples</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see GraphPrinter
 * @since 2.0
 */
public final class MatrixFormatter implements GraphFormatter, Colors {

    @Override
    public String format(IGraph graph) {
        StringBuilder out = new StringBuilder();
        int n = graph.vertexCount();

        // Title in bold cyan
        out.append(BOLD).append(CYAN).append("Adjacency Matrix").append(RESET).append("\n\n");

        // Header row (vertex indices)
        out.append("    ");
        for (int i = 0; i < n; i++) {
            out.append(BOLD).append(BLUE)
                    .append(String.format("%4d", i))
                    .append(RESET);
        }
        out.append('\n');

        // Matrix rows
        for (int i = 0; i < n; i++) {
            // Row label in bold magenta
            out.append(BOLD).append(MAGENTA)
                    .append(String.format("%3d ", i))
                    .append(RESET);

            for (int j = 0; j < n; j++) {
                int value = 0;

                for (Edge edge : graph.getNeighbors(i)) {
                    if (edge.destination() == j) {
                        value = graph.isWeighted() ? edge.weight() : 1;
                        break;
                    }
                }

                // Color-coded cell values
                if (value == 0) {
                    out.append(WHITE).append(String.format("%4d", value)).append(RESET);
                } else if (value == 1) {
                    out.append(GREEN).append(String.format("%4d", value)).append(RESET);
                } else {
                    out.append(YELLOW).append(String.format("%4d", value)).append(RESET);
                }
            }

            out.append('\n');
        }

        return out.toString();
    }
}
