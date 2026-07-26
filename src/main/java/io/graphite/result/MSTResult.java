package io.graphite.result;

import io.graphite.algorithm.mst.Kruskal;
import io.graphite.algorithm.mst.Prim;
import io.graphite.api.MST;

import java.util.List;

/**
 * Represents the result of a Minimum Spanning Tree computation.
 *
 * <p>An {@code MSTResult} contains every edge included in the spanning tree
 * together with its total weight.</p>
 *

 * <h2>Contents</h2>

 * <br>Contents</br>

 *
 * <ul>
 *     <li>MST edges</li>
 *     <li>Total weight</li>
 * </ul>
 *

 * <h2>Immutability</h2>

 * <br>Immutability</br>

 *
 * <p>This result object is immutable.</p>
 *
 * @author Chinmay
 * @version 2.0
 * @see MST
 * @see Prim
 * @see Kruskal
 * @see MSTEdge
 * @since 2.0
 */
public record MSTResult(int cost, List<MSTEdge> edges) implements Colors {
    public MSTResult {
        edges = List.copyOf(edges);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(CYAN)
                .append("═══════════════ Minimum Spanning Tree ═══════════════")
                .append(RESET)
                .append('\n');

        builder.append("Total Cost : ")
                .append(GREEN)
                .append(cost)
                .append(RESET)
                .append('\n');

        builder.append("Edges")
                .append(MAGENTA)
                .append(" (")
                .append(edges.size())
                .append(")")
                .append(RESET)
                .append('\n');


        if (!edges.isEmpty()) {
            int maxWeightWidth = edges.stream()
                    .map(MSTEdge::weight)
                    .map(String::valueOf)
                    .mapToInt(String::length)
                    .max()
                    .orElse(1);

            builder
                    .append("─────────────────────────────────────────────────────")
                    .append('\n');

            for (MSTEdge edge : edges) {
                builder.append("  ")
                        .append(YELLOW)
                        .append("• ")
                        .append(String.format(
                                "%d ──(%" + maxWeightWidth + "d)──> %d",
                                edge.source(),
                                edge.weight(),
                                edge.destination())
                        )
                        .append(RESET)
                        .append('\n');
            }
            builder.append("─────────────────────────────────────────────────────");
        }

        return builder.toString();
    }
}
