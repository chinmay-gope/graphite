package io.graphite.graph;

import io.graphite.builder.GraphConfiguration;
import io.graphite.model.Edge;

public final class DirectedGraph extends Graph {

    public DirectedGraph(GraphConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void addEdge(int source, int destination, int weight) {


        validateVertexIndex(source);
        validateVertexIndex(destination);

        markUsed(source);
        markUsed(destination);

        adjacencyList.get(source)
                .add(new Edge(source, destination, weight));


        edgeCount++;
    }

    @Override
    public void removeEdge(int source, int destination) {


        validateVertexIndex(source);
        validateVertexIndex(destination);

        boolean removed = adjacencyList.get(source)
                .removeIf(edge -> edge.destination() == destination);

        if (removed) {
            edgeCount--;
        }
    }
}
