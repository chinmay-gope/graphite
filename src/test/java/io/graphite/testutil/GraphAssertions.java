package io.graphite.testutil;

import io.graphite.result.TraversalResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class GraphAssertions {

    private GraphAssertions() {
    }

    public static void assertTraversal(
            List<Integer> expected,
            TraversalResult actual) {

        assertEquals(expected, actual.traversalOrder());
    }

    public static void assertVisitsExactly(
            TraversalResult result,
            Integer... vertices) {

        assertEquals(
                Set.of(vertices),
                new HashSet<>(result.traversalOrder())
        );
    }

    public static void assertTraversalSize(
            int expected,
            TraversalResult result) {

        assertEquals(expected,
                result.traversalOrder().size());
    }

    public static void assertStartsFrom(
            int source,
            TraversalResult result) {

        assertEquals(source,
                result.traversalOrder().getFirst());
    }
}
