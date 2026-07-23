package io.graphite.builder;

import io.graphite.exception.graph.InvalidGraphConfigurationException;
import io.graphite.graph.IGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BuilderValidationTest {
    @Test
    void shouldInferVertexCountFromLargestVertex() {

        IGraph graph = Graphs.undirected()
                .addEdge(2, 4)
                .build();

        assertEquals(5, graph.getVertices());
    }

    @Test
    void shouldRespectExplicitVertexCount() {

        IGraph graph = Graphs.undirected()
                .vertices(5)
                .addEdge(2, 4)
                .build();

        assertEquals(5, graph.getVertices());
    }

    @Test
    void shouldRejectEdgeOutsideConfiguredVertexCount() {

        assertThrows(
                InvalidGraphConfigurationException.class,
                () -> Graphs.undirected()
                        .vertices(3)
                        .addEdge(2, 4)
                        .build()
        );
    }

    @Test
    void shouldAllowEdgeExactlyAtUpperBoundary() {

        IGraph graph = Graphs.undirected()
                .vertices(5)
                .addEdge(4, 3)
                .build();

        assertEquals(5, graph.getVertices());
    }
}
