package io.graphite.graph;

import io.graphite.builder.Graphs;
import io.graphite.exception.graph.InvalidVertexException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphQueryValidationTest {

    @Test
    void shouldRejectDegreeOnInactiveVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.degree(0)
        );
    }

    @Test
    void shouldRejectNeighborsOnInactiveVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.neighbors(0)
        );
    }

    @Test
    void shouldRejectHasEdgeWhenSourceInactive() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.hasEdge(0, 4)
        );
    }

    @Test
    void shouldRejectHasEdgeWhenDestinationInactive() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertThrows(
                InvalidVertexException.class,
                () -> graph.hasEdge(2, 0)
        );
    }

    @Test
    void shouldDetectExistingEdge() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertTrue(graph.hasEdge(2, 4));
    }

    @Test
    void shouldReturnFalseForMissingEdgeBetweenActiveVertices() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .addEdge(4, 3)
                .build();

        assertFalse(graph.hasEdge(2, 3));
    }

    @Test
    void shouldReturnCorrectDegreeForActiveVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(2,4)
                .addEdge(2,3)
                .build();

        assertEquals(2, graph.degree(2));
    }
}
