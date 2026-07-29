package io.graphite.graph.transform;

import io.graphite.graph.IGraph;

public final class GraphTransformFactory {

    public static final GraphTransformFactory INSTANCE = new GraphTransformFactory();

    private GraphTransformFactory() {
    }

    public IGraph copy(IGraph graph) {
        return graph.copy();
    }

    public IGraph transpose(IGraph graph) {
        return graph.transposed();
    }

    public IGraph merge(IGraph first,
                        IGraph second) {

        return new MergeTransformer()
                .transform(first, second);
    }

    public IGraph union(IGraph first,
                        IGraph second) {

        return new UnionTransformer()
                .transform(first, second);
    }

    public IGraph intersection(IGraph first,
                               IGraph second) {

        return new IntersectionTransformer()
                .transform(first, second);
    }

    public IGraph difference(IGraph first,
                             IGraph second) {

        return new DifferenceTransformer()
                .transform(first, second);
    }

    public IGraph compose(IGraph first,
                          IGraph second) {

        return new CompositionTransformer()
                .transform(first, second);
    }

    public IGraph matrixProduct(IGraph first,
                                IGraph second) {

        return new MatrixProductTransformer()
                .transform(first, second);
    }
}
