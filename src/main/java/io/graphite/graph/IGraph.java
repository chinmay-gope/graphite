package io.graphite.graph;

import io.graphite.api.*;
import io.graphite.api.analysis.GraphAnalysis;
import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.internal.ImmutableGraph;
import io.graphite.io.writer.GraphWriterService;
import io.graphite.model.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the primary graph abstraction in the Graphite framework.
 *
 * <p>{@code IGraph} defines the common contract implemented by all graph
 * representations, regardless of whether they are directed, undirected,
 * mutable, immutable, weighted, or unweighted.</p>
 *
 * <p>Beyond basic graph operations, this interface serves as the central
 * entry point to Graphite's service-oriented architecture. Algorithms are
 * organized into dedicated services that can be accessed directly from a
 * graph instance, providing a fluent and discoverable API.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.undirected()
 *         .addEdge(0, 1)
 *         .addEdge(1, 2)
 *         .addEdge(2, 3)
 *         .build();
 *
 * graph.traversal().bfs(0);
 * graph.shortestPath().dijkstra(0);
 * graph.mst().prim();
 * }</pre>
 *

 * <h2>Characteristics</h2>

 * <h3>Characteristics</h3>

 *
 * <ul>
 *     <li>Supports directed and undirected graphs.</li>
 *     <li>Supports weighted and unweighted edges.</li>
 *     <li>Provides mutable and immutable implementations.</li>
 *     <li>Exposes graph algorithms through dedicated service APIs.</li>
 *     <li>Supports graph formatting, transformation, validation, and analysis.</li>
 * </ul>
 *

 * <h2>Implementation Notes</h2>

 * <h3>Implementation Notes</h3>
 *
 * <p>Implementations are expected to maintain a valid graph state and perform
 * necessary validation before structural modifications or algorithm execution.
 * Individual implementations may choose different internal storage strategies,
 * but all must preserve the behavior defined by this interface.</p>
 *
 * <p>The preferred way to create graph instances is through the
 * {@link Graphs} builder and generator APIs rather than implementing this
 * interface directly.</p>
 *
 * @see Graph
 * @see ImmutableGraph
 * @see Graphs
 * @since 2.0
 */
public interface IGraph {

    GraphWriterService write();

    // ========= Mutation =========

    void addEdge(int source, int destination, int weight);

    default void addEdge(int source, int destination) {
        addEdge(source, destination, 1);
    }

    void removeEdge(int source, int destination);

    void clear();

    /**
     * Returns an immutable view of this graph.
     *
     * <p>If this graph is already immutable, implementations may simply return
     * the current instance.</p>
     *
     * @return an immutable graph
     */
    IGraph asImmutable();

    // ========= Queries =========

    boolean hasEdge(int source, int destination);


    /**
     * Returns whether the given vertex index is valid.
     *
     * <p>This does not imply that the vertex participates in the graph.
     * Use {@link #isUsedVertex(int)} to determine whether the vertex
     * currently exists in the graph.</p>
     */

    boolean containsVertex(int vertex);

    int degree(int vertex);

    boolean isEmpty();

    /**
     * Returns whether the given vertex is present in this graph.
     *
     * <p>A vertex is considered used if it was explicitly added
     * or appears as the source or destination of at least one edge.</p>
     *
     * @param vertex vertex index
     * @return {@code true} if the vertex exists in the graph,
     * {@code false} otherwise
     */
    boolean isUsedVertex(int vertex);

    /**
     * Returns whether this graph contains weighted edges.
     *
     * @return {@code true} if edge weights are significant;
     * {@code false} otherwise
     */
    boolean isWeighted();

    /**
     * Returns whether this graph is directed.
     *
     * @return {@code true} if the graph is directed;
     * {@code false} otherwise
     */
    boolean isDirected();

    /**
     * Returns whether this graph is undirected.
     *
     * @return {@code true} if the graph is undirected;
     * {@code false} otherwise
     */
    boolean isUndirected();


    /**
     * Returns the neighboring vertices adjacent to the specified vertex.
     *
     * <p>For directed graphs, this method returns the outgoing neighbors of the
     * supplied vertex.</p>
     *
     * @param vertex the source vertex
     * @return the neighboring vertices
     * @throws InvalidVertexException if the vertex does not exist
     */
    List<Edge> getNeighbors(int vertex);

    default List<Edge> neighbors(int vertex) {
        return getNeighbors(vertex);
    }

    /**
     * Returns all edges contained in this graph.
     *
     * <p>The returned collection represents the current edge set of the graph.
     * The iteration order is implementation dependent.</p>
     *
     * @return an immutable view or snapshot of the graph edges
     */
    List<Edge> getEdges();

    default List<Edge> edges() {
        return getEdges();
    }

    /**
     * Returns the total number of vertices contained in this graph.
     *
     * <p>Vertices are identified by zero-based integer indices ranging from
     * {@code 0} to {@code getVertices() - 1}.</p>
     *
     * @return the number of vertices in this graph
     */
    int getVertices();

    default int vertexCount() {
        return getVertices();
    }

    int edgeCount();

    default Iterable<Integer> vertices() {

        List<Integer> vertices = new ArrayList<>();

        for (int i = 0; i < vertexCount(); i++) {
            vertices.add(i);
        }

        return Collections.unmodifiableList(vertices);
    }

    default boolean contains(int vertex) {
        return containsVertex(vertex);
    }

    Iterable<Integer> activeVertices();

    int activeVertexCount();

    int firstActiveVertex();

    int randomActiveVertex();

    // ========= Copy =========

    IGraph copy();


    // ========= Transform =========

    IGraph transposed();

    // ========= Services =========

    Bipartite bipartite();

    Connectivity connectivity();

    Cycle cycle();

    Euler euler();

    ShortestPath shortestPath();

    Topology topology();

    MST mst();

    Traversal traversal();

    GraphAnalysis analysis();
}
