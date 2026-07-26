package io.graphite.exception.graph;

import io.graphite.exception.GraphException;

public class GraphEmptyException extends GraphException {
    public GraphEmptyException(String message) {
        super(message);
    }
}
