package io.graphite.print;

import io.graphite.exception.algorithm.NullGraphException;
import io.graphite.graph.IGraph;
import io.graphite.print.formatter.*;

/**
 * Utility class for rendering graphs in multiple textual formats.
 *
 * <p>{@code GraphPrinter} provides a unified entry point for Graphite's
 * formatting framework, allowing graphs to be displayed in compact,
 * tabular, visualization, and structured data formats.</p>
 *
 * <pre>{@code
 * GraphPrinter.compact(graph);
 * GraphPrinter.matrix(graph);
 * GraphPrinter.statistics(graph);
 * GraphPrinter.dot(graph);
 * GraphPrinter.mermaid(graph);
 * GraphPrinter.json(graph);
 * }</pre>
 *

 * <h2>Supported Formats</h2>

 * <h3>Supported Formats</h3>

 *
 * <ul>
 *     <li>Compact</li>
 *     <li>Edge List</li>
 *     <li>Adjacency Matrix</li>
 *     <li>Statistics</li>
 *     <li>DOT (Graphviz)</li>
 *     <li>Mermaid</li>
 *     <li>JSON</li>
 * </ul>
 *
 * <p>All formatting implementations are stateless singleton instances,
 * allowing them to be reused without additional allocation.</p>
 *
 * @since 2.0
 */
public final class GraphPrinter {

    private static final GraphFormatter COMPACT = new CompactFormatter();
    private static final GraphFormatter TREE = new TreeFormatter();
    private static final GraphFormatter EDGE_LIST = new EdgeListFormatter();
    private static final GraphFormatter MATRIX = new MatrixFormatter();
    private static final GraphFormatter STATISTICS = new StatisticsFormatter();
    private static final GraphFormatter DOT = new DotFormatter();
    private static final GraphFormatter MERMAID = new MermaidFormatter();
    private static final GraphFormatter JSON = new JsonFormatter();

    private GraphPrinter() {
    }

    /**
     * Prints the graph using the compact formatter.
     *
     * <p>This is the default textual representation of a graph and
     * is intended for debugging and console output.</p>
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see CompactFormatter
     * @since 2.0
     */
    public static void compact(IGraph graph) {
        System.out.println(COMPACT.format(graph));
    }

    /**
     * Prints the graph using a tree-style representation.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see TreeFormatter
     * @since 2.0
     */
    public static void tree(IGraph graph) {
        System.out.println(TREE.format(graph));
    }

    /**
     * Prints the graph as an edge list.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see EdgeListFormatter
     * @since 2.0
     */
    public static void edgeList(IGraph graph) {
        System.out.println(EDGE_LIST.format(graph));
    }

    /**
     * Prints the graph as an adjacency matrix.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see MatrixFormatter
     * @since 2.0
     */
    public static void matrix(IGraph graph) {
        System.out.println(MATRIX.format(graph));
    }

    /**
     * Prints graph statistics and structural information.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see StatisticsFormatter
     * @since 2.0
     */
    public static void statistics(IGraph graph) {
        System.out.println(STATISTICS.format(graph));
    }


    /**
     * Prints the graph in Graphviz DOT format.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see DotFormatter
     * @since 2.0
     */
    public static void dot(IGraph graph) {
        System.out.println(DOT.format(graph));
    }

    /**
     * Prints the graph using Mermaid diagram syntax.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see MermaidFormatter
     * @since 2.0
     */
    public static void mermaid(IGraph graph) {
        System.out.println(MERMAID.format(graph));
    }

    /**
     * Prints the graph as a JSON document.
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see JsonFormatter
     * @since 2.0
     */
    public static void json(IGraph graph) {
        System.out.println(JSON.format(graph));
    }

    /**
     * Prints the graph using the default compact formatter.
     *
     * <p>This method is retained for backward compatibility and is
     * equivalent to calling {@link #compact(IGraph)}.</p>
     *
     * @param graph the graph to print
     * @throws NullGraphException if the graph is {@code null}
     * @see #compact(IGraph)
     * @since 2.0
     */
    public static void println(IGraph graph) {
        compact(graph);
    }
}
