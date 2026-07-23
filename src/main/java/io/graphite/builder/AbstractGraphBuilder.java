package io.graphite.builder;

<<<<<<< HEAD
=======
import io.graphite.exception.graph.InvalidGraphConfigurationException;
>>>>>>> fec1ea5 (fix: java docs)
import io.graphite.graph.Graph;
import io.graphite.graph.IGraph;
import io.graphite.model.Edge;
import io.graphite.validation.BuilderValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Base implementation for all fluent graph builders.
 *
 * <p>{@code AbstractGraphBuilder} provides the common infrastructure
 * shared by Graphite's graph builders. It manages graph configuration,
 * edge collection, validation, and graph construction while allowing
 * concrete builders to specialize graph creation.</p>
 *
 * <p>Builders follow the Fluent Builder pattern, enabling expressive
 * graph construction through method chaining.</p>
 *
 * <pre>{@code
 * Graphs.undirected()
 *       .vertices(5)
 *       .addEdge(0,1)
 *       .addEdge(1,2)
 *       .build();
 * }</pre>
 *
<<<<<<< HEAD
 * <h2>Responsibilities</h2>
=======
 * <h3>Responsibilities</h3>
>>>>>>> fec1ea5 (fix: java docs)
 *
 * <ul>
 *     <li>Stores graph configuration.</li>
 *     <li>Collects edge definitions.</li>
 *     <li>Performs builder validation.</li>
 *     <li>Delegates graph creation.</li>
 * </ul>
 *
 * <p>Concrete subclasses determine the specific graph type being built.</p>
 *
 * @param <G>    graph implementation produced by this builder
 * @param <SELF> concrete builder type used for fluent chaining
 * @since 2.0
 */
public abstract class AbstractGraphBuilder<G extends Graph, SELF extends AbstractGraphBuilder<G, SELF>> {

    protected final GraphConfiguration configuration = new GraphConfiguration();

    protected final List<Edge> edges =
            new ArrayList<>();

    protected abstract G createGraph();

    @SuppressWarnings("unchecked")
    protected final SELF self() {
        return (SELF) this;
    }

    protected final List<Edge> edges() {
        return edges;
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }

    public int edgeCount() {
        return edges.size();
    }

<<<<<<< HEAD
    public SELF vertices(int vertices) {
        configuration.setVertices(vertices);
=======
    /**
     * Sets the total number of vertices in the graph.
     *
     * <p>If specified, this value becomes a fixed upper bound and
     * all added vertices must lie within the configured range
     * {@code [0, vertices - 1]}.
     *
     * <p>If omitted, the builder automatically sizes the graph
     * based on the highest referenced vertex.
     */
    public SELF vertices(int vertices) {
        configuration.setVertices(vertices);
        configuration.setHasFixedVertexCount(true);
>>>>>>> fec1ea5 (fix: java docs)
        return self();
    }

    public SELF weighted(boolean weighted) {
        configuration.setWeighted(weighted);
        return self();
    }

    public SELF immutable(boolean immutable) {
        configuration.setImmutable(immutable);
        return self();
    }

    public SELF selfLoops(boolean immutable) {
        configuration.setSelfLoops(immutable);
        return self();
    }

    public SELF parallelEdges(boolean immutable) {
        configuration.setParallelEdges(immutable);
        return self();
    }

    public SELF addEdge(
            int source,
            int destination) {

        return addEdge(
                source,
                destination,
                1
        );
    }

    public SELF addEdge(
            int source,
            int destination,
            int weight) {

<<<<<<< HEAD
=======
        updateVertexCount(source, destination);

>>>>>>> fec1ea5 (fix: java docs)
        edges.add(new Edge(
                source,
                destination,
                weight
        ));

<<<<<<< HEAD
        configuration.setVertices(
                Math.max(
                        configuration.getVertices(),
                        Math.max(source, destination) + 1
                )
        );

=======
>>>>>>> fec1ea5 (fix: java docs)
        return self();
    }

    public SELF addEdge(Edge edge) {

<<<<<<< HEAD
        edges.add(edge);

        configuration.setVertices(
                Math.max(
                        configuration.getVertices(),
                        Math.max(edge.source(), edge.destination()) + 1
                )
        );
=======
        updateVertexCount(edge.source(), edge.destination());

        edges.add(edge);
>>>>>>> fec1ea5 (fix: java docs)

        return self();
    }

    public SELF addEdges(Collection<Edge> edges) {

        for (Edge edge : edges) {
            addEdge(edge);
        }

        return self();
    }

    public SELF clear() {

        edges.clear();

        configuration.setVertices(0);

        return self();
    }

    public SELF from(IGraph graph) {

        configuration.setVertices(graph.getVertices());
        configuration.setWeighted(graph.isWeighted());
        configuration.setDirected(graph.isDirected());

        addEdges(graph.getEdges());

        return self();
    }

    public IGraph build() {

        BuilderValidator.validate(configuration);

        G graph = createGraph();

        for (Edge edge : edges) {

            graph.addEdge(
                    edge.source(),
                    edge.destination(),
                    edge.weight());
        }

        return configuration.isImmutable()
                ? graph.asImmutable()
                : graph;
    }
<<<<<<< HEAD
=======

    private void updateVertexCount(int source, int destination) {

        int requiredVertices = Math.max(source, destination) + 1;

        if (configuration.hasFixedVertexCount()) {

            if (requiredVertices > configuration.getVertices()) {
                throw new InvalidGraphConfigurationException(
                        "Edge (%d -> %d) requires at least %d vertices, but graph is configured for %d."
                                .formatted(
                                        source,
                                        destination,
                                        requiredVertices,
                                        configuration.getVertices()
                                )
                );
            }
        } else {

            configuration.setVertices(
                    Math.max(configuration.getVertices(), requiredVertices)
            );

        }
    }
>>>>>>> fec1ea5 (fix: java docs)
}
