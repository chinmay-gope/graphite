package io.graphite.generator.pattern;

import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import io.graphite.graph.PatternGraphBuilder;

/**
 * Generates two-dimensional grid graphs.
 *
 * <p>Grid graphs arrange vertices in a rectangular lattice where each
 * vertex is connected to its adjacent horizontal and vertical neighbors.</p>
 *
<<<<<<< HEAD
 * <h2>Applications</h2>
=======
 * <h3>Applications</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Pathfinding</li>
 *     <li>Maze generation</li>
 *     <li>Robotics</li>
 *     <li>Game development</li>
 * </ul>
 *
 * @author Chinmay
 * @version 2.0
 * @see PatternGraphBuilder
 * @since 2.0
 */
public final class GridGraphGenerator {

    private GridGraphGenerator() {

    }

    public static IGraph generate(int rows, int cols) {

        if (rows <= 0 || cols <= 0) {
            throw new InvalidGraphConfigurationException(
                    "Grid dimensions must be positive.");
        }

        int vertices = rows * cols;

        var builder =
                Graphs.undirected()
                        .vertices(vertices);

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                int current = r * cols + c;

                if (c + 1 < cols)
                    builder.addEdge(current, current + 1);

                if (r + 1 < rows)
                    builder.addEdge(current, current + cols);
            }
        }

        return builder.build();
    }
}
