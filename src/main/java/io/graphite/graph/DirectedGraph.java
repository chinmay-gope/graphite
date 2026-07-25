package io.graphite.graph;

import io.graphite.builder.GraphConfiguration;
import io.graphite.model.Edge;

public final class DirectedGraph extends Graph {

    public DirectedGraph(GraphConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void addEdge(int source, int destination, int weight) {

<<<<<<< HEAD
        validateVertex(source);
        validateVertex(destination);
=======
        validateVertexIndex(source);
        validateVertexIndex(destination);

        activeVertices[source] = true;
        activeVertices[destination] = true;
>>>>>>> fec1ea5 (fix: java docs)

        adjacencyList.get(source)
                .add(new Edge(source, destination, weight));

<<<<<<< HEAD
=======

>>>>>>> fec1ea5 (fix: java docs)
        edgeCount++;
    }

    @Override
    public void removeEdge(int source, int destination) {

<<<<<<< HEAD
        validateVertex(source);
        validateVertex(destination);
=======
        validateVertexIndex(source);
        validateVertexIndex(destination);
>>>>>>> fec1ea5 (fix: java docs)

        boolean removed = adjacencyList.get(source)
                .removeIf(edge -> edge.destination() == destination);

        if (removed) {
            edgeCount--;
        }
    }
}
