package io.graphite.graph;

import io.graphite.api.*;
import io.graphite.api.analysis.GraphAnalysis;
import io.graphite.api.analysis.GraphAnalysisService;
import io.graphite.api.internal.GraphAPI;
import io.graphite.builder.GraphConfiguration;
import io.graphite.builder.Graphs;
import io.graphite.exception.GraphEmptyException;
import io.graphite.exception.graph.InvalidVertexException;
import io.graphite.graph.internal.GraphAPIType;
import io.graphite.graph.internal.ImmutableGraph;
import io.graphite.io.writer.GraphWriterService;
import io.graphite.model.Edge;

import java.util.*;
import java.util.function.Supplier;


/**
 * Default mutable implementation of {@link IGraph}.
 *
 * <p>{@code Graph} provides the core implementation used throughout the
 * Graphite framework. It stores the graph structure, manages graph metadata,
 * and acts as the gateway to all algorithm services.</p>
 *
 * <p>Rather than exposing algorithms directly, this class follows a
 * service-oriented architecture. Each algorithm category is accessed through
 * a dedicated service that is created lazily and cached for reuse.</p>
 *
 * <pre>{@code
 * IGraph graph = Graphs.undirected()
 *         .addEdge(0, 1)
 *         .addEdge(1, 2)
 *         .build();
 *
 * graph.traversal().bfs(0);
 * graph.shortestPath().dijkstra(0);
 * graph.mst().prim();
 * }</pre>
 *

 * <h2>Features</h2>

 * <h3>Features</h3>

 *
 * <ul>
 *     <li>Mutable graph implementation.</li>
 *     <li>Adjacency-list based storage.</li>
 *     <li>Lazy initialization of algorithm services.</li>
 *     <li>Cached service instances.</li>
 *     <li>Supports graph transformation and formatting.</li>
 * </ul>
 *
 * <h2>Service Delegation</h2>
 * <h3>Service Delegation</h3>
 *
 * <p>Algorithm execution is delegated to specialized service objects such as
 * {@code Traversal}, {@code ShortestPath}, and {@code MST}. Services are
 * created on first access and reused for the lifetime of the graph,
 * minimizing object allocation while keeping the public API clean.</p>
 *
 * <h2>Thread Safety</h2>
 * <h3>Thread Safety</h3>
 *
 * <p>This implementation is mutable and therefore not inherently
 * thread-safe. Concurrent modifications should be externally synchronized.</p>
 *
 * <p>For read-only usage, consider converting the graph to an immutable
 * instance using {@link #asImmutable()}.</p>
 *
 * @see IGraph
 * @see ImmutableGraph
 * @see Graphs
 * @since 2.0
 */
public abstract class Graph implements IGraph {

    // ==========================================================
    // Fields
    // ==========================================================


    protected int edgeCount;
    protected final boolean[] usedVertices;

    protected final List<List<Edge>> adjacencyList;
    protected final GraphConfiguration configuration;
    private final EnumMap<GraphAPIType, GraphAPI> cache =
            new EnumMap<>(GraphAPIType.class);


    protected Graph(GraphConfiguration configuration) {
        this.configuration = configuration;

        adjacencyList = new ArrayList<>();

        usedVertices = new boolean[configuration.getVertices()];

        for (int i = 0; i < configuration.getVertices(); i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }


    protected GraphConfiguration configuration() {
        return configuration;
    }

    @Override

    public boolean isUsedVertex(int vertex) {

        validateVertexIndex(vertex);

        return usedVertices[vertex];
    }

    protected final void markUsed(int vertex) {
        usedVertices[vertex] = true;
    }

    // ==========================================================
    // Queries
    // ==========================================================


    protected void validateVertexIndex(int vertex) {

        if (!containsVertex(vertex)) {
            throw new InvalidVertexException(vertex);
        }
    }

    @Override
    public Iterable<Integer> activeVertices() {

        List<Integer> active = new ArrayList<>();

        for (int v = 0; v < vertexCount(); v++) {
            if (usedVertices[v]) {
                active.add(v);
            }
        }

        return List.copyOf(active);
    }

    @Override
    public int activeVertexCount() {

        int count = 0;

        for (boolean used : usedVertices) {
            if (used) count++;
        }

        return count;
    }

    @Override
    public int firstActiveVertex() {

        for (int v = 0; v < vertexCount(); v++) {

            if (usedVertices[v]) {
                return v;
            }
        }

        throw new GraphEmptyException(
                "Graph contains no active vertices."
        );
    }


    private static final Random RANDOM = new Random();

    @Override
    public int randomActiveVertex() {

        List<Integer> active = new ArrayList<>();

        activeVertices().forEach(active::add);

        if (active.isEmpty()) {
            throw new GraphEmptyException(
                    "Graph contains no active vertices."
            );
        }

        return active.get(
                RANDOM.nextInt(active.size())
        );
    }

    protected void validateActiveVertex(int vertex) {

        if (!isUsedVertex(vertex)) {
            throw new GraphEmptyException(vertex + " is not used.");
        }
    }


    @Override
    public boolean containsVertex(int vertex) {
        return vertex >= 0 && vertex < configuration.getVertices();
    }


    @Override
    public int degree(int vertex) {

        validateVertexIndex(vertex);
        validateActiveVertex(vertex);

        return adjacencyList.get(vertex).size();
    }

    // ==========================================================
    // Views
    // ==========================================================

    @Override
    public boolean hasEdge(int source, int destination) {

        validateVertexIndex(source);
        validateVertexIndex(destination);

        validateActiveVertex(source);
        validateActiveVertex(destination);


        return adjacencyList.get(source).stream().anyMatch(edge -> edge.destination() == destination);
    }

    @Override
    public List<Edge> getNeighbors(int vertex) {


        validateVertexIndex(vertex);

        validateActiveVertex(vertex);


        return Collections.unmodifiableList(adjacencyList.get(vertex));
    }

    @Override
    public List<Edge> getEdges() {

        List<Edge> edges = new ArrayList<>();

        for (List<Edge> neighbours : adjacencyList) {
            edges.addAll(neighbours);
        }

        return List.copyOf(edges);
    }

    @Override
    public int getVertices() {
        return configuration.getVertices();
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    @Override
    public boolean isWeighted() {
        return configuration.isWeighted();
    }

    @Override
    public boolean isEmpty() {
        return configuration.getVertices() == 0;
    }

    @Override
    public boolean isDirected() {
        return configuration.isDirected();
    }

    @Override
    public boolean isUndirected() {
        return configuration.isUndirected();
    }

    @Override
    public void clear() {

        adjacencyList.forEach(List::clear);


        Arrays.fill(usedVertices, false);


        edgeCount = 0;
    }

    @Override
    public IGraph asImmutable() {
        return new ImmutableGraph(this);
    }

    @Override
    public abstract void addEdge(int source, int destination, int weight);

    @Override
    public abstract void removeEdge(int source, int destination);

    @Override
    public IGraph copy() {
        return GraphCopier.copy(this);
    }

    @Override
    public IGraph transpose() {
        return GraphTransposer.transpose(this);
    }

    @Override
    public List<Edge> neighbors(int vertex) {
        return getNeighbors(vertex);
    }

    @Override
    public List<Edge> edges() {
        return getEdges();
    }

    @Override
    public int vertexCount() {
        return getVertices();
    }

    @Override
    public boolean contains(int vertex) {
        return containsVertex(vertex);
    }

    // ==========================================================
    // Services
    // ==========================================================

    @Override
    public Iterable<Integer> vertices() {

        List<Integer> vertices = new ArrayList<>();

        for (int i = 0; i < getVertices(); i++) {
            if (isUsedVertex(i)) {
                vertices.add(i);
            }
        }

        return Collections.unmodifiableList(vertices);
    }

    /**
     * Returns a lazily-created API module.
     */
    @SuppressWarnings("unchecked")
    private <T extends GraphAPI> T service(
            GraphAPIType type,
            Supplier<T> factory
    ) {
        return (T) cache.computeIfAbsent(
                type,
                key -> factory.get()
        );
    }

    @Override
    public GraphWriterService write() {
        return service(GraphAPIType.WRITER, () -> new GraphWriterService(this));
    }

    @Override
    public Traversal traversal() {
        return service(GraphAPIType.TRAVERSAL, () -> new Traversal(this));
    }

    @Override
    public MST mst() {
        return service(GraphAPIType.MST, () -> new MST(this));
    }

    @Override
    public ShortestPath shortestPath() {
        return service(GraphAPIType.SHORTEST_PATH, () -> new ShortestPath(this));
    }

    @Override
    public Connectivity connectivity() {
        return service(GraphAPIType.CONNECTIVITY, () -> new Connectivity(this));
    }

    @Override
    public Cycle cycle() {
        return service(GraphAPIType.CYCLE, () -> new Cycle(this));
    }

    @Override
    public Euler euler() {
        return service(GraphAPIType.EULER, () -> new Euler(this));
    }

    @Override
    public Topology topology() {
        return service(GraphAPIType.TOPOLOGY, () -> new Topology(this));
    }

    @Override
    public Bipartite bipartite() {
        return service(GraphAPIType.BIPARTITE, () -> new Bipartite(this));
    }

    @Override
    public GraphAnalysis analysis() {
        return service(GraphAPIType.ANALYSIS, () -> new GraphAnalysisService(this));
    }

}
