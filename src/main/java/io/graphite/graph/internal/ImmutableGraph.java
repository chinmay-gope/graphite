package io.graphite.graph.internal;

import io.graphite.api.*;
import io.graphite.api.analysis.GraphAnalysis;
import io.graphite.builder.GraphConfiguration;
import io.graphite.exception.graph.ImmutableGraphException;
import io.graphite.graph.Graph;
import io.graphite.graph.IGraph;
import io.graphite.io.writer.GraphWriterService;
import io.graphite.model.Edge;

import java.util.List;

/**
 * Immutable implementation of {@link IGraph}.
 *
 * <p>An immutable graph cannot be structurally modified after creation.
 * Any attempt to add or remove vertices or edges results in an
 * {@link ImmutableGraphException}.</p>
 *
 * <p>Immutable graphs are ideal for sharing graph instances safely between
 * algorithms, threads, or application components without the risk of
 * accidental modification.</p>
 *
 * <p>Instances are typically created through the builder API using
 * {@code immutable()} or by calling {@link IGraph#asImmutable()}.</p>
 *

 * <h2>Characteristics</h2>

 * <h3>Characteristics</h3>
 *
 * <ul>
 *     <li>Read-only graph structure.</li>
 *     <li>Supports all graph algorithms.</li>
 *     <li>Safe for concurrent read operations.</li>
 *     <li>Shares the same service-oriented API as mutable graphs.</li>
 * </ul>
 *
 * @see Graph
 * @see IGraph
 * @since 2.0
 */
public final class ImmutableGraph implements IGraph {

    private final IGraph delegate;

    public ImmutableGraph(IGraph delegate) {
        this.delegate = delegate;
    }

    private ImmutableGraphException immutable() {
        return new ImmutableGraphException();
    }

    @Override
    public GraphWriterService write() {
        return delegate.write();
    }

    // ==========================================================
    // Mutating Operations
    // ==========================================================
    @Override
    public void addEdge(int source, int destination, int weight) {
        throw immutable();
    }

    @Override
    public void addEdge(int source, int destination) {
        throw immutable();
    }

    @Override
    public void removeEdge(int source, int destination) {
        throw immutable();
    }

    @Override
    public void clear() {
        throw immutable();
    }

    // ==========================================================
    // Queries
    // ==========================================================

    @Override
    public boolean hasEdge(int source, int destination) {
        return delegate.hasEdge(source, destination);
    }

    @Override
    public boolean containsVertex(int vertex) {
        return delegate.containsVertex(vertex);
    }

    @Override
    public int degree(int vertex) {
        return delegate.degree(vertex);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean isUsedVertex(int vertex) {
        return delegate.isUsedVertex(vertex);
    }

    @Override
    public Iterable<Integer> activeVertices() {
        return delegate.activeVertices();
    }

    @Override
    public int activeVertexCount() {
        return delegate.activeVertexCount();
    }

    @Override
    public int firstActiveVertex() {
        return delegate.firstActiveVertex();
    }

    @Override
    public int randomActiveVertex() {
        return delegate.randomActiveVertex();
    }

    @Override
    public boolean isWeighted() {
        return delegate.isWeighted();
    }

    @Override
    public boolean isDirected() {
        return delegate.isDirected();
    }


    @Override
    public boolean isUndirected() {
        return delegate.isUndirected();
    }

    @Override
    public List<Edge> getNeighbors(int vertex) {
        return delegate.getNeighbors(vertex);
    }

    @Override
    public List<Edge> getEdges() {
        return delegate.getEdges();
    }

    @Override
    public int getVertices() {
        return delegate.getVertices();
    }

    // ==========================================================
    // Metadata
    // ==========================================================

    @Override
    public int vertexCount() {
        return delegate.vertexCount();
    }

    @Override
    public int edgeCount() {
        return delegate.edgeCount();
    }

    // ==========================================================
    // Copy
    // ==========================================================

    @Override
    public IGraph copy() {
        return delegate.copy().asImmutable();
    }

    @Override
    public IGraph transposed() {
        return delegate.transposed().asImmutable();
    }

//    _____________________services______________________

    @Override
    public Bipartite bipartite() {
        return delegate.bipartite();
    }

    @Override
    public Connectivity connectivity() {
        return delegate.connectivity();
    }

    @Override
    public Cycle cycle() {
        return delegate.cycle();
    }

    @Override
    public Euler euler() {
        return delegate.euler();
    }

    @Override
    public ShortestPath shortestPath() {
        return delegate.shortestPath();
    }

    @Override
    public Topology topology() {
        return delegate.topology();
    }

    @Override
    public MST mst() {
        return delegate.mst();
    }

    @Override
    public Traversal traversal() {
        return delegate.traversal();
    }

    @Override
    public GraphAnalysis analysis() {
        return delegate.analysis();
    }

    @Override
    public IGraph asImmutable() {
        return this;
    }

}
