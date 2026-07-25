package io.graphite.examples;

<<<<<<< HEAD
import io.graphite.api.analysis.GraphAnalysisResult;
import io.graphite.builder.Graphs;
import io.graphite.exception.GraphException;
import io.graphite.generator.preset.GraphPresetGenerator;
import io.graphite.graph.IGraph;
import io.graphite.print.GraphPrinter;

import java.io.IOException;
import java.nio.file.Path;

public final class MainExamples {

    private static final String PATH = "graph.txt";

    static void main() throws IOException {
        new MainExamples().executeThemAll();
        readFromFile();
        writeToFile();
    }

    private static void writeToFile() throws IOException {
        IGraph graph = Graphs.undirected().vertices(6).addEdge(0, 1).addEdge(1, 2).addEdge(2, 3).addEdge(3, 4).addEdge(4, 5).build();

        graph.write().edgeList(Path.of(PATH));
    }

    private static void readFromFile() throws IOException {
        IGraph restored = Graphs.read().edgeList(Path.of(PATH));

        System.out.println("Restored Graph:");
        GraphPrinter.compact(restored);
    }

    private static void immutableDemo() {

        header("Immutable Graph Demo");

        IGraph graph = Graphs
                .undirected()
                .vertices(4)
                .immutable(true)
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .build();

        System.out.println("Immutable Graph:");
        print(graph);

        System.out.println();

        System.out.println("Trying addEdge()...");
        try {
            graph.addEdge(0, 3);
            System.out.println("FAILED");
        } catch (GraphException e) {
            System.out.println("PASS : " + e.getMessage());
        }

        System.out.println();

        System.out.println("Trying removeEdge()...");
        try {
            graph.removeEdge(1, 2);
            System.out.println("FAILED");
        } catch (GraphException e) {
            System.out.println("PASS : " + e.getMessage());
        }

        System.out.println();

        System.out.println("Trying clear()...");
        try {
            graph.clear();
            System.out.println("FAILED");
        } catch (GraphException e) {
            System.out.println("PASS : " + e.getMessage());
        }

        System.out.println();

        System.out.println("Graph is still intact:");
        print(graph);
    }

    // ---------------------------------------------------------
    // Graph Transformation
    // ---------------------------------------------------------
    private static void graphTransformDemo() {
        IGraph g1 = Graphs
                .undirected()
                .vertices(5)
                .weighted(true)
                .addEdge(0, 1, 5)
                .addEdge(1, 2, 7)
                .addEdge(3, 4, 2)
                .build();

        IGraph g2 = Graphs
                .undirected()
                .vertices(5)
                .weighted(true)
                .addEdge(1, 2, 7)
                .addEdge(2, 3, 4)
                .addEdge(0, 4, 9)
                .build();

        header("Original G1");
        GraphPrinter.statistics(g1);

        header("Original G2");
        GraphPrinter.statistics(g2);

        header("Copy of G1");
        GraphPrinter.statistics(Graphs.transform().copy(g1));

        header("Merge G1 + G2");
        GraphPrinter.statistics(Graphs.transform().merge(g1, g2));

        header("Union G1 ∪ G2");
        GraphPrinter.statistics(Graphs.transform().union(g1, g2));

        header("Intersection G1 ∩ G2");
        GraphPrinter.statistics(Graphs.transform().intersection(g1, g2));

        header("Difference G1 − G2");
        GraphPrinter.statistics(Graphs.transform().difference(g1, g2));

        header("MatrixProduct G1 ∘ G2");
        GraphPrinter.statistics(Graphs.transform().matrixProduct(g1, g2));

        header("Composition |E(G)| × |V(H)|²  |V(G)| × |E(H)|");
        GraphPrinter.statistics(Graphs.transform().matrixProduct(g1, g2));
    }

    // ---------------------------------------------------------
    // Services
    // ---------------------------------------------------------
    private static void servicesDemo() {

        header("Services Demo");

        // ==========================================================
        // Traversal
        // ==========================================================

        IGraph traversal = Graphs.undirected().vertices(6).addEdge(0, 1).addEdge(0, 2).addEdge(1, 3).addEdge(2, 4).addEdge(4, 5).build();

        System.out.println("\nTraversal");
        print(traversal);
        System.out.println(traversal.traversal().bfs(0));
        System.out.println(traversal.traversal().dfs(0));


        // ==========================================================
        // Cycle Detection
        // ==========================================================

        IGraph cycle = Graphs.undirected().vertices(5).addEdge(0, 1).addEdge(1, 2).addEdge(2, 0).addEdge(2, 3).addEdge(3, 4).build();

        System.out.println("\nCycle Detection");
        print(cycle);
        System.out.println(cycle.cycle().undirected());


        // ==========================================================
        // Bipartite
        // ==========================================================

        IGraph bipartite = Graphs.undirected().vertices(6).addEdge(0, 3).addEdge(0, 4).addEdge(1, 3).addEdge(1, 5).addEdge(2, 4).addEdge(2, 5).build();

        System.out.println("\nBipartite");
        print(bipartite);
        System.out.println(bipartite.bipartite().bfs());
        System.out.println(bipartite.bipartite().dfs());


        // ==========================================================
        // Minimum Spanning Tree
        // ==========================================================

        IGraph mst = Graphs.undirected().weighted(true).vertices(5).addEdge(0, 1, 2).addEdge(0, 2, 6).addEdge(1, 2, 3).addEdge(1, 3, 8).addEdge(1, 4, 5).addEdge(2, 4, 7).addEdge(3, 4, 9).build();

        System.out.println("\nMinimum Spanning Tree");
        print(mst);
        System.out.println(mst.mst().prim(0));
        System.out.println(mst.mst().kruskal());


        // ==========================================================
        // Shortest Path
        // ==========================================================

        IGraph shortest = Graphs.directed().weighted(true).vertices(5).addEdge(0, 1, 10).addEdge(0, 3, 5).addEdge(1, 2, 1).addEdge(1, 3, 2).addEdge(2, 4, 4).addEdge(3, 1, 3).addEdge(3, 2, 9).addEdge(3, 4, 2).addEdge(4, 0, 7).addEdge(4, 2, 6).build();

        System.out.println("\nShortest Path");
        print(shortest);
        System.out.println(shortest.shortestPath().dijkstra(0));
        System.out.println(shortest.shortestPath().bellmanFord(0));
        System.out.println(shortest.shortestPath().floydWarshall());


        // ==========================================================
        // Connectivity
        // ==========================================================

        IGraph connectivity = Graphs.undirected().vertices(7).addEdge(0, 1).addEdge(1, 2).addEdge(2, 0).addEdge(1, 3).addEdge(3, 4).addEdge(3, 5).addEdge(5, 6).build();

        System.out.println("\nConnectivity");
        print(connectivity);
        System.out.println(connectivity.connectivity().articulationPoints());
        System.out.println(connectivity.connectivity().bridges());
        System.out.println(connectivity.connectivity().biconnectedComponents());


        // ==========================================================
        // Strongly Connected Components
        // ==========================================================

        IGraph scc = Graphs.directed().vertices(5).addEdge(0, 1).addEdge(1, 2).addEdge(2, 0).addEdge(1, 3).addEdge(3, 4).build();

        System.out.println("\nStrongly Connected Components");
        print(scc);
        System.out.println(scc.connectivity().stronglyConnectedComponents());


        // ==========================================================
        // Topological Sorting
        // ==========================================================

        IGraph dag = Graphs.directed().vertices(6).addEdge(5, 2).addEdge(5, 0).addEdge(4, 0).addEdge(4, 1).addEdge(2, 3).addEdge(3, 1).build();

        System.out.println("\nTopological Sort");
        print(dag);
        System.out.println(dag.topology().kahn()); // 3, 4, 5, 1, 2, 0 or
        System.out.println(dag.topology().dfs()); //  5, 4, 2, 3, 1, 0


        // ==========================================================
        // Analysis
        // ==========================================================

        System.out.println("\nAnalysis");
        System.out.println(traversal.analysis().density());
        System.out.println(traversal.analysis().averageDegree());
        System.out.println(traversal.analysis().isTree());

        // ==========================================================
        // Euler
        // ==========================================================

        IGraph euler = Graphs.undirected().vertices(5).addEdge(0, 1).addEdge(1, 2).addEdge(2, 3).addEdge(3, 0).addEdge(0, 4).addEdge(4, 2).build();

        System.out.println("\nEuler");
        print(euler);
        System.out.println(euler.euler().path());

        try {
            System.out.println(euler.euler().circuit());
        } catch (GraphException e) {
            System.err.println("Graph is not Eulerian: " + e.getMessage());
        }
    }

    private static void builderDemo() {

        header("Builder Demo");

        IGraph graph = Graphs.undirected().vertices(6).weighted(true).immutable(false)
                .addEdge(0, 1, 5)
                .addEdge(0, 2, 2)
                .addEdge(1, 3, 7)
                .addEdge(2, 4, 4)
                .addEdge(4, 5, 1)
                .build();

        print(graph);

        System.out.println("\nRemoving edge (0,1)");
        graph.removeEdge(0, 1);

        print(graph);

        System.out.println("\nBuilder.from()");

        IGraph rebuilt = Graphs.undirected().from(graph).build();

        print(rebuilt);
    }

    // ---------------------------------------------------------
    // Builder
    // ---------------------------------------------------------

    // ---------------------------------------------------------
    // Copy
    // ---------------------------------------------------------
    private static void copyDemo() {

        header("Copy Demo");

        IGraph graph = Graphs.undirected().vertices(5).addEdge(0, 1).addEdge(1, 2).build();

        IGraph copy = graph.copy();

        copy.addEdge(2, 3);

        System.out.println("Original");

        print(graph);

        System.out.println();

        System.out.println("Copy");

        print(copy);
    }

    private static void transposeDemo() {

        header("Transpose Demo");

        IGraph graph = Graphs.directed().vertices(5).addEdge(0, 1).addEdge(0, 2).addEdge(1, 3).addEdge(4, 0).build();

        System.out.println("Original");

        GraphPrinter.compact(graph);

        System.out.println();

        System.out.println("Transpose");

        GraphPrinter.compact(graph.transpose());
    }

    // ---------------------------------------------------------
    // Transpose
    // ---------------------------------------------------------

    private static void directedDemo() {

        header("Directed Graph Demo");

        IGraph graph = Graphs.directed().vertices(6).weighted(true).addEdge(0, 1, 4).addEdge(0, 2, 3).addEdge(2, 5, 8).addEdge(5, 1, 9).build();

        print(graph);
    }

    // ---------------------------------------------------------
    // Directed
    // ---------------------------------------------------------

    private static void undirectedDemo() {

        header("Undirected Graph Demo");

        IGraph graph = Graphs.undirected().vertices(6).weighted(false).addEdge(0, 1).addEdge(0, 2).addEdge(1, 3).addEdge(3, 4).addEdge(4, 5).build();

        print(graph);
    }
    // ---------------------------------------------------------
    // Undirected
    // ---------------------------------------------------------

    private static void randomGeneratorDemo() {

        header("Random Generator Demo");

        ExamplePrinter.execute("Undirected Graph", () -> Graphs.random().undirected().vertices(8).edges(12).build());

        ExamplePrinter.execute("Directed Graph", () -> Graphs.random().directed().vertices(8).edges(12).build());

        ExamplePrinter.execute("Weighted Graph", () -> Graphs.random().undirected().vertices(8).edges(12).weightRange(1, 20).build());

        ExamplePrinter.execute("Connected Graph", () -> Graphs.random().undirected().vertices(8).edges(12).connected().build());

        ExamplePrinter.execute("Graph With Self Loops", () -> Graphs.random().undirected().vertices(8).edges(15).allowSelfLoops().build());

        ExamplePrinter.execute("Graph With Parallel Edges", () -> Graphs.random().undirected().vertices(8).edges(18).allowParallelEdges().build());

        ExamplePrinter.execute("Weighted Connected Graph", () -> Graphs.random().undirected().vertices(10).edges(18).weightRange(5, 50).connected().build());
    }
    // ---------------------------------------------------------
    // Random Generator
    // ---------------------------------------------------------

    private static void treeGeneratorDemo() {

        header("Tree Generator");

        IGraph graph = Graphs.patterns().tree(10);

        print(graph);

        System.out.println("Tree       : " + graph.analysis().isTree());
        System.out.println("Connected  : " + graph.analysis().isConnected());
        System.out.println("Cycle      : " + graph.analysis().isCyclic());
    }


// ---------------------------------------------------------
// Pattern Generators
// ---------------------------------------------------------

    private static void starGeneratorDemo() {

        header("Star Generator");

        IGraph graph = Graphs.patterns().star(8);

        print(graph);

        System.out.println("Center Degree : " + graph.degree(0));

        for (int i = 1; i < graph.vertexCount(); i++) {

            System.out.println("Degree(" + i + ") = " + graph.degree(i));
        }
    }

    private static void wheelGeneratorDemo() {

        header("Wheel Generator");

        IGraph graph = Graphs.patterns().wheel(8);

        print(graph);

        System.out.println("Connected : " + graph.analysis().isConnected());

        System.out.println("Cycle     : " + graph.analysis().isCyclic());
    }

    private static void gridGeneratorDemo() {

        header("Grid Generator");

        IGraph graph = Graphs.patterns().grid(3, 4);

        print(graph);

        System.out.println("Vertices : " + graph.vertexCount());

        System.out.println("Edges : " + graph.edgeCount());
    }

    private static void cycleGeneratorDemo() {

        header("Cycle Generator");

        IGraph graph = Graphs.patterns().cycle(8);

        print(graph);

        for (int i = 0; i < graph.vertexCount(); i++) {

            System.out.println("Degree(" + i + ") = " + graph.degree(i));
        }
    }

    private static void completeGeneratorDemo() {

        header("Complete Graph Generator");

        IGraph graph = Graphs.patterns().complete(6);

        print(graph);

        for (int i = 0; i < graph.vertexCount(); i++) {

            System.out.println("Degree(" + i + ") = " + graph.degree(i));
        }
    }

    private static void completeBipartiteGeneratorDemo() {

        header("Complete Bipartite Generator");

        IGraph graph = Graphs.patterns().completeBipartite(3, 4);

        print(graph);

        System.out.println("Bipartite : " + graph.analysis().isBipartite());

        System.out.println("Connected : " + graph.analysis().isConnected());
    }

    private static void bipartiteGeneratorDemo() {

        header("Random Bipartite Generator");

        IGraph graph = Graphs.patterns().bipartite(4, 5);

        print(graph);

        System.out.println("Bipartite : " + graph.analysis().isBipartite());

        System.out.println("Connected : " + graph.analysis().isConnected());
    }

    private static void generatorStressDemo() {

        header("Generator Stress");

        print(Graphs.patterns().tree(20));

        print(Graphs.patterns().star(20));

        print(Graphs.patterns().wheel(20));

        print(Graphs.patterns().cycle(20));

        print(Graphs.patterns().complete(20));

        print(Graphs.patterns().completeBipartite(10, 10));

        print(Graphs.patterns().grid(5, 5));
    }

    private static void graphFactoryDemo() {

        header("Graph Factory");

        ExamplePrinter.execute("Traversal Graph", () -> GraphPresetGenerator.traversalGraph(10));

        ExamplePrinter.execute("Shortest Path Graph", () -> GraphPresetGenerator.denseGraph(10));

        ExamplePrinter.execute("Minimum Spanning Tree Graph", () -> GraphPresetGenerator.mstGraph(10));

        ExamplePrinter.execute("Directed Dense Graph", () -> GraphPresetGenerator.directedDenseGraph(10));

        ExamplePrinter.execute("Dense Graph", () -> GraphPresetGenerator.denseGraph(10));

        ExamplePrinter.execute("Directed Acyclic Graph (DAG)", () -> GraphPresetGenerator.dag(10));

        ExamplePrinter.execute("Tree Graph", () -> GraphPresetGenerator.treeGraph(10));

        ExamplePrinter.execute("Bipartite Graph", () -> GraphPresetGenerator.bipartiteGraph(10));
    }

// ---------------------------------------------------------
// Graph Factory
// ---------------------------------------------------------

    private static void analysisDemo() {

        header("Analysis Demo");

        IGraph graph = Graphs.undirected().vertices(6).addEdge(0, 1).addEdge(1, 2).addEdge(2, 3).addEdge(3, 4).addEdge(4, 5).build();

        print(graph);

        var analysis = graph.analysis();

        System.out.println("Connected      : " + analysis.isConnected());
        System.out.println("Tree           : " + analysis.isTree());
        System.out.println("Forest         : " + analysis.isForest());
        System.out.println("Cycle          : " + analysis.isCyclic());
        System.out.println("Bipartite      : " + analysis.isBipartite());
        System.out.println("Eulerian       : " + analysis.isEulerian());

        System.out.println();

        System.out.println("Max Degree     : " + analysis.maxDegree());
        System.out.println("Min Degree     : " + analysis.minDegree());
        System.out.println("Average Degree : " + analysis.averageDegree());
        System.out.println("Density        : " + analysis.density());

        System.out.println();

        GraphAnalysisResult analyze = graph.analysis().analyze();
        System.out.println(analyze);
    }

// ---------------------------------------------------------
// Analysis
// ---------------------------------------------------------

    private static void printerDemo() {

        header("Printer Demo");

        IGraph graph = Graphs.undirected().vertices(6).weighted(true).addEdge(0, 1, 5).addEdge(0, 2, 1).addEdge(1, 4, 8).addEdge(4, 5, 2).build();

        System.out.println("Compact");

        GraphPrinter.compact(graph);

        System.out.println("Edge List");

        GraphPrinter.edgeList(graph);

        System.out.println("Tree");

        GraphPrinter.tree(graph);
    }

// ---------------------------------------------------------
// Printer
// ---------------------------------------------------------

    private static void header(String title) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

// ---------------------------------------------------------
// Helpers
// ---------------------------------------------------------

    private static void print(IGraph graph) {

        GraphPrinter.compact(graph);

        System.out.println();
        System.out.println("Vertices : " + graph.vertexCount());
        System.out.println("Edges    : " + graph.edgeCount());
        System.out.println("Directed : " + graph.isDirected());
        System.out.println("Weighted : " + graph.isWeighted());
        System.out.println("Immutable: " + graph.getClass());
    }

    void executeThemAll() {
        builderDemo();
        immutableDemo();
        copyDemo();
        transposeDemo();

        directedDemo();
        undirectedDemo();

        servicesDemo();

        randomGeneratorDemo();

        treeGeneratorDemo();
        starGeneratorDemo();
        wheelGeneratorDemo();
        gridGeneratorDemo();
        cycleGeneratorDemo();
        completeGeneratorDemo();
        completeBipartiteGeneratorDemo();
        bipartiteGeneratorDemo();

        graphTransformDemo();

        generatorStressDemo();

        graphFactoryDemo();

        analysisDemo();

        printerDemo();

    }
=======
import io.graphite.builder.Graphs;
import io.graphite.graph.IGraph;
import io.graphite.print.GraphPrinter;

public final class MainExamples {
    public static void main(String[] args) {
        IGraph graph = Graphs.directed()
                .addEdge(2,4)
                .build();

        for (int i = 0; i < graph.getVertices(); i++) {
            System.out.println(i + " : " + graph.isActiveVertex(i));
        }

        checkImmutability();
    }

    private static void checkImmutability() {
        IGraph graph = Graphs.undirected().immutable(true)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(0, 3)
                .build();

        GraphPrinter.println(graph);
        System.out.println("____________________");

        IGraph graph3 = Graphs.undirected()
                .addEdges(graph.edges())
                .build();

        graph3.removeEdge(0, 1);

        GraphPrinter.println(graph3);
    }

>>>>>>> fec1ea5 (fix: java docs)
}
