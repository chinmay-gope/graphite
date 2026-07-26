package io.graphite.graph;

import io.graphite.builder.GraphConfiguration;
import io.graphite.model.Edge;

import java.util.ArrayList;
import java.util.List;

public final class UndirectedGraph extends Graph {

    public UndirectedGraph(GraphConfiguration configuration) {
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

        adjacencyList.get(destination)
                .add(new Edge(destination, source, weight));

        edgeCount++;
    }

    @Override
    public List<Edge> getEdges() {

        List<Edge> edges = new ArrayList<>();

        for (List<Edge> neighbours : adjacencyList) {
            for (Edge edge : neighbours) {
//                unique edges
                if (edge.source() < edge.destination()) {
                    edges.add(edge);
                }
            }
        }

        return List.copyOf(edges);
    }

    @Override
    public void removeEdge(int source, int destination) {

        validateVertexIndex(source);
        validateVertexIndex(destination);

        boolean removed1 = adjacencyList.get(source)
                .removeIf(edge -> edge.destination() == destination);

        boolean removed2 = adjacencyList.get(destination)
                .removeIf(edge -> edge.destination() == source);

        if (removed1 && removed2) {
            edgeCount--;
        }
    }
}
