# Graphite

<p align="center">
  <b>A modern, lightweight, immutable-first Java graph library.</b><br>
  Build graphs • Analyze graphs • Generate graphs • Benchmark algorithms
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21+-orange" alt="Java">
  <img src="https://img.shields.io/badge/Version-2.0-blue" alt="Version">
  <img src="https://img.shields.io/badge/Status-Stable-brightgreen" alt="Status">
  <img src="https://img.shields.io/badge/Algorithms-15%2B-success" alt="Algorithms">
  <img src="https://img.shields.io/badge/License-MIT-lightgrey" alt="License">
</p>

Graphite is a Java graph library built around a fluent API and a clean, service-oriented architecture. Instead of scattering algorithms across static utility classes, Graphite attaches them as discoverable services directly on the graph instance.

```java
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .addEdge(2, 3)
        .build();

TraversalResult bfs = graph.traversal().bfs(0);
MSTResult mst = graph.mst().prim(0);
ShortestPathResult paths = graph.shortestPath().dijkstra(0);
```

---

## Why Graphite?

Most graph libraries expose algorithms as disconnected static utilities:

```java
BreadthFirstSearch.traverse(graph, 0);
Dijkstra.shortestPath(graph, 0);
```

Graphite groups them by capability instead, so functionality is discoverable straight from the graph instance:

```java
graph.traversal().bfs(0);
graph.shortestPath().dijkstra(0);
```

Core design principles:

- **Simplicity** — construct graphs, run algorithms, and format output in just a few lines.
- **Consistency** — every service, builder, and result follows the same naming and design conventions; learn one part, know the rest.
- **Immutability-first** — algorithms run safely without accidental mutation; mutable graphs remain available when needed.
- **Extensibility** — new algorithms and services plug in without redesigning existing code.
- **Separation of concerns** — construction, algorithms, validation, generation, formatting, and benchmarking are all independent subsystems.

---

## Installation

Requires **Java 21+** and **Maven 3.9+**. Available via [JitPack](https://jitpack.io).

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<properties>
    <maven.compiler.release>21</maven.compiler.release>
</properties>

<dependencies>
    <dependency>
        <groupId>com.github.chinmay-gope</groupId>
        <artifactId>graphite</artifactId>
        <version>v2.1.1-rc1</version>
    </dependency>
</dependencies>
```

**Verify:**

```java
IGraph graph = Graphs.undirected()
        .addEdge(3, 4)
        .addEdge(3, 2)
        .addEdge(3, 1)
        .build();

GraphPrinter.println(graph);
```

---

## Quick Start

**Construct a graph:**

```java
IGraph graph = Graphs.undirected()
        .weighted()
        .addEdge(0, 1, 4)
        .addEdge(0, 2, 2)
        .addEdge(2, 3, 5)
        .build();

IGraph immutable = Graphs.undirected()
        .immutable(true)
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

**Run algorithms:**

```java
graph.traversal().bfs(0);
graph.traversal().dfs(0);

graph.shortestPath().dijkstra(0);
graph.shortestPath().bellmanFord(0);
graph.shortestPath().floydWarshall();

graph.mst().prim(0);
graph.mst().kruskal();

graph.cycle().directed();
graph.cycle().undirected();

graph.connectivity().bridges();
graph.connectivity().articulationPoints();
graph.connectivity().biconnectedComponents();
graph.connectivity().stronglyConnectedComponents();

graph.topology().dfs();
graph.topology().kahn();

graph.bipartite().bfs();
graph.bipartite().dfs();

graph.euler().path();
graph.euler().circuit();

graph.analysis().analyze(); // density, degree stats, connectivity, etc.
```

**Generate graphs:**

```java
IGraph random  = Graphs.random().undirected().vertices(1000).edges(2500)
                        .connected().weighted().weightRange(1, 50).immutable().build();

IGraph preset  = Graphs.presets().mstGraph(1000);      // traversal, sparse, dense, dag, bipartite, ...
IGraph pattern = Graphs.patterns().grid(20, 20);        // complete, star, wheel, tree, dag, ...
IGraph example = GraphExampleGenerator.eulerCircuitGraph(8);
```

**Transform graphs:**

```java
Graphs.transform().union(graphA, graphB);
Graphs.transform().intersection(graphA, graphB);
Graphs.transform().difference(graphA, graphB);
Graphs.transform().compose(graphA, graphB);
Graphs.transform().matrixProduct(graphA, graphB); // Strassen-based
graph.transposed();
graph.copy();
```

**Print & export:**

```java
GraphPrinter.compact(graph);
GraphPrinter.matrix(graph);
GraphPrinter.statistics(graph);
GraphPrinter.dot(graph);      // Graphviz
GraphPrinter.mermaid(graph);  // Mermaid diagrams
GraphPrinter.json(graph);
```

**Read & write:**

```java
graph.write().edgeList(Path.of("graph.txt"));
IGraph loaded = Graphs.read().edgeList(Path.of("graph.txt"));
```

**Benchmark:**

```java
BenchmarkResult result = Benchmarks.builder()
        .name("BFS")
        .task(() -> graph.traversal().bfs(0))
        .warmup(10).iterations(100)
        .build()
        .run();

BenchmarkComparison comparison = Benchmarks.compare()
        .add("DFS", () -> graph.traversal().dfs(0))
        .add("BFS", () -> graph.traversal().bfs(0))
        .run();
```

**Stress test:**

```java
StressRunner.run(
        "BFS Stress Test",
        StressConfig.DEFAULT_CONFIG,
        GraphPresetGenerator::traversalGraph,
        g -> g.traversal().bfs(0)
);
```

---

## Algorithms & Complexity

| Category | Algorithms | Time | Space |
|---|---|---|---|
| Traversal | BFS, DFS | O(V + E) | O(V) |
| Cycle Detection | Directed, Undirected | O(V + E) | O(V) |
| Shortest Path | Dijkstra | O((V + E) log V) | O(V) |
| | Bellman-Ford (negative weights) | O(V x E) | O(V) |
| | Floyd-Warshall (all-pairs) | O(V^3) | O(V^2) |
| MST | Prim, Kruskal | O(E log V) / O(E log E) | O(V) |
| Topological Sort | DFS-based, Kahn's | O(V + E) | O(V) |
| Connectivity | Bridges, Articulation Points, Biconnected Components (Tarjan) | O(V + E) | O(V + E) |
| | Strongly Connected Components (Kosaraju) | O(V + E) | O(V) |
| Bipartite Check | BFS-based, DFS-based | O(V + E) | O(V) |
| Euler Path/Circuit | Hierholzer's | O(V + E) | O(V + E) |

All algorithms are implemented as **stateless singletons** (e.g. `Dijkstra.INSTANCE`) — thread-safe and reusable, with no graph state stored internally.

---

## Graph Generation

| Generator | Purpose |
|---|---|
| `Graphs.random()` | Configurable graphs: directed/undirected, weighted, connected, self-loops, parallel edges, custom weight ranges |
| `Graphs.presets()` | Ready-made graphs tuned per algorithm family: `traversalGraph`, `sparseGraph`, `denseGraph`, `weightedGraph`, `mstGraph`, `treeGraph`, `dag`, `bipartiteGraph`, and directed variants |
| `Graphs.patterns()` | Classical structures: complete, complete bipartite, star, wheel, tree, grid, cycle, DAG |
| `GraphExampleGenerator` | Hand-crafted graphs for demos/tests: Euler path/circuit, invalid Euler, disconnected graphs |

---

## Formatting & I/O

- **Formatters:** compact, tree, edge list, adjacency matrix, statistics, DOT (Graphviz), Mermaid, JSON
- **I/O:** edge list reader/writer (round-trips graph structure + metadata)

---

## Architecture

```
Graphs -> Builder/Generator -> GraphConfiguration -> GraphFactory -> IGraph -> Cached Services
```

- **Builders/Generators** collect configuration; the **Factory** creates the correct concrete implementation (directed/undirected, mutable/immutable).
- **Services** (`traversal()`, `mst()`, `shortestPath()`, `connectivity()`, `topology()`, `cycle()`, `bipartite()`, `euler()`, `analysis()`) are created lazily and cached per graph instance, then delegate to stateless singleton algorithms.
- **Result objects** (`TraversalResult`, `MSTResult`, `ShortestPathResult`, `EulerResult`, `BenchmarkResult`, ...) are immutable records — never raw arrays or collections.
- A centralized **validation layer** (`GraphPreconditions`, `GraphValidator`, `BuilderValidator`) enforces algorithm requirements before execution.
- A domain-specific **exception hierarchy** rooted in `GraphException` — `InvalidVertexException`, `NegativeCycleException`, `NegativeWeightException`, `GraphDisconnectedException`, `UnsupportedGraphTypeException`, `InvalidGraphConfigurationException`, `ImmutableGraphException` — gives precise, actionable errors.

---

## Architecture Overview


![graphite_v2.png](src/main/java/io/graphite/diagrams/graphite_v2.png)

The diagram illustrates the high-level architecture of Graphite, including the graph domain, algorithm APIs,
service layer, validation, I/O, benchmarking, and the interactions between these components.

## License

[MIT](https://opensource.org/licenses/MIT)
