package io.graphite.graph;

import io.graphite.builder.Graphs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActiveVertexTest {

    @Test
    void shouldTrackOnlyVerticesPresentInEdges() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertFalse(graph.isActiveVertex(0));
        assertFalse(graph.isActiveVertex(1));
        assertTrue(graph.isActiveVertex(2));
        assertFalse(graph.isActiveVertex(3));
        assertTrue(graph.isActiveVertex(4));
    }

    @Test
    void shouldContainAllocatedVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertTrue(graph.containsVertex(0));
        assertTrue(graph.containsVertex(4));
        assertFalse(graph.containsVertex(5));
    }

    @Test
    void shouldReturnOnlyActiveVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        List<Integer> vertices = new ArrayList<>();

        graph.vertices().forEach(vertices::add);

        assertEquals(List.of(2, 4), vertices);
    }

    @Test
    void shouldClearActiveVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        graph.clear();

        assertFalse(graph.isActiveVertex(2));
        assertFalse(graph.isActiveVertex(4));
    }
}
