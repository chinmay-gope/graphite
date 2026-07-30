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

Graphite is a modern graph library for Java designed around a fluent API, clean architecture, and immutable graph support. It provides a comprehensive collection of graph algorithms, graph generators, formatting utilities, benchmarking tools, and stress-testing utilities through a consistent, easy-to-use API.

Unlike traditional graph libraries that expose algorithms as disconnected utility classes, Graphite organizes functionality into dedicated services attached directly to a graph instance, allowing algorithms to be discovered naturally through the API.

```java
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .addEdge(2, 3)
        .build();

TraversalResult bfs =
        graph.traversal().bfs(0);

MSTResult mst =
        graph.mst().prim();

ShortestPathResult paths =
        graph.shortestPath().dijkstra(0);
```

---

## Highlights

* Fluent builder-based graph construction
* Directed and undirected graph implementations
* Mutable and immutable graph support
* Comprehensive graph algorithm suite
* Random, preset, pattern, and example graph generators
* Multiple graph formatting and visualization utilities
* Built-in graph benchmarking framework
* Built-in stress testing framework
* Consistent result objects across all algorithms
* Clean service-oriented API with cached algorithm services

---

## Installation

Graphite is available via **JitPack**.

### Requirements

- Java **21+**
- Maven **3.9+**

### Maven

Add the JitPack repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Configure your project to compile with **Java 21**:

```xml
<properties>
    <maven.compiler.release>21</maven.compiler.release>
</properties>
```

Finally, add the dependency:

```xml
<dependencies>
    <dependency>
        <groupId>com.github.chinmay-gope</groupId>
        <artifactId>graphite</artifactId>
        <version>v2.1.1-rc1</version>
    </dependency>
</dependencies>
```

### Verify Installation

```java
import io.graphite.print.GraphPrinter;
import io.graphite.builder.Graphs;
import io.graphite.graph.IGraph;

public class Main {
    public static void main(String[] args) {
         IGraph graph = Graphs.undirected()
                .addEdge(3, 4)
                .addEdge(3, 2)
                .addEdge(3, 1)
                .build();

         GraphPrinter.println(graph);
    }
}
```

> **Note**
>
> Graphite requires **Java 21 or later**. Ensure your project's
> `<maven.compiler.release>` is set to `21`.



## Why Graphite?

> **Graphite V2** focuses on simplicity, consistency, and extensibility—making graph algorithms easier to use without sacrificing performance or architectural clarity.

Graphite was created with a simple goal:

> **Make graph algorithms easy to use without compromising software design.**

Many graph libraries provide excellent algorithm implementations, but they often expose them through large collections of static utility methods or loosely connected classes. While functional, these APIs can become difficult to discover, extend, and maintain as projects grow.

Graphite takes a different approach.

Instead of treating algorithms as isolated utilities, Graphite organizes functionality into dedicated services attached directly to a graph instance. Traversal, shortest path, minimum spanning tree, connectivity analysis, graph formatting, and other capabilities are all accessed through a consistent, fluent API.

```javascript
graph.traversal().bfs(0);

graph.shortestPath().dijkstra(0);

graph.mst().prim();

graph.connectivity().bridges();

graph.topology().kahn();

graph.bipartite().bfs();
```

This design provides a natural developer experience while keeping algorithms modular and independent internally.

---

### Design Philosophy

Graphite V2 is built around several core principles.

#### Simplicity

The public API should be intuitive enough that developers can begin using the library without studying extensive documentation.

Constructing graphs, running algorithms, formatting output, and generating benchmark results should require only a few lines of code.

---

#### Consistency

Every feature in Graphite follows consistent naming conventions and design patterns.

Examples include:

* Fluent builders for graph construction
* Immutable result objects
* Service-based algorithm access
* Singleton algorithm implementations
* Shared validation and precondition checks
* Uniform exception hierarchy

Once a developer learns one part of the library, the rest behaves in a familiar way.

---

#### Immutability

Graphite embraces immutable data structures whenever practical.

Immutable graphs allow algorithms to execute safely without accidental modification while making applications easier to reason about and debug.

Developers can choose between mutable and immutable graph implementations depending on their requirements.

---

#### Extensibility

Adding new algorithms should not require redesigning the existing architecture.

Graphite's modular organization allows additional algorithms and services to integrate naturally alongside existing functionality.

The architecture has been designed with future expansion in mind.

---

#### Separation of Concerns

Graph construction, graph algorithms, formatting, validation, graph generation, benchmarking, and stress testing are all independent components.

Each subsystem has a clearly defined responsibility, making the codebase easier to understand, maintain, and extend.

---

### What Makes Graphite Different?

Graphite is not simply a collection of graph algorithms.

It is a complete toolkit for working with graphs throughout the entire development lifecycle.

It includes:

* Graph construction
* Random graph generation
* Preset graph generation
* Pattern graph generation
* Graph transformation
* Graph analysis
* Graph formatting
* Graph serialization
* Benchmarking
* Stress testing
* Comprehensive examples

These capabilities are presented through a unified API rather than as unrelated utilities.

---

### Intended Audience

Graphite is designed for developers who want a modern Java graph library that is:

* Easy to learn
* Architecturally clean
* Suitable for educational purposes
* Useful for algorithm experimentation
* Practical for production applications
* Straightforward to extend with additional algorithms

Whether you are learning graph theory, preparing for technical interviews, building research prototypes, or developing graph-based applications, Graphite provides a consistent foundation for graph development.

# Features

Graphite V2 provides a complete ecosystem for building, analyzing, generating, formatting, and benchmarking graphs through a clean and consistent API.

---

## Graph Models

Graphite supports the most common graph representations while maintaining a consistent programming model.

### Supported Graph Types

* Directed Graphs
* Undirected Graphs
* Weighted Graphs
* Unweighted Graphs
* Mutable Graphs
* Immutable Graphs

### Construction

* Fluent Builder API
* Edge-based graph construction
* Graph cloning
* Graph transformation utilities
* Immutable graph conversion

---

## Graph Algorithms

Graphite includes a comprehensive collection of classical graph algorithms organized into dedicated services.

### Traversal

* Breadth-First Search (BFS)
* Depth-First Search (DFS)

```
graph.traversal().bfs(source);
graph.traversal().dfs(source);
```

---

### Cycle Detection

* Directed Cycle Detection
* Undirected Cycle Detection

```
graph.cycle().hasCycle();
```

---

### Shortest Paths

* Dijkstra's Algorithm
* Bellman-Ford Algorithm
* Floyd-Warshall Algorithm

```
graph.shortestPath().dijkstra(source);
graph.shortestPath().bellmanFord(source);
graph.shortestPath().floydWarshall();
```

---

### Minimum Spanning Tree

* Prim's Algorithm
* Kruskal's Algorithm

```
graph.mst().prim(0);
graph.mst().kruskal();
```

---

### Topological Sorting

* DFS Topological Sort
* Kahn's Algorithm

```
graph.topology().dfs();
graph.topology().kahn();
```

---

### Connectivity

* Strongly Connected Components
* Bridges
* Articulation Points
* Biconnected Components

```
graph.connectivity().bridges();
graph.connectivity().articulationPoints();
graph.connectivity().biConnectedComponents();
graph.connectivity().stronglyConnectedComponents();
```

---

### Bipartite Graphs

* BFS Bipartite Check
* DFS Bipartite Check

```
graph.bipartite().bfs();
graph.bipartite().dfs();
```

---

### Euler Graphs

* Euler Path
* Euler Circuit

```
graph.euler().path();
graph.euler().circuit();
```

---

## Graph Builders

Graphite provides fluent builders that make graph construction expressive and readable.

### Supported Builders

* Directed Graph Builder
* Undirected Graph Builder
* Random Graph Builder

```
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

---

## Graph Generators

Graph generation utilities simplify algorithm testing and experimentation.

### Random Graph Generator

Generate configurable random graphs.

* Directed / Undirected
* Weighted / Unweighted
* Connected graphs
* Custom edge counts
* Weight ranges
* Immutable generation

---

### Preset Graph Generator

Ready-to-use graphs optimized for algorithms.

Examples include:

* Traversal Graphs
* Sparse Graphs
* Dense Graphs
* Weighted Graphs
* Directed Graphs
* Tree Graphs
* DAGs
---

### Pattern Graph Generator

Generate classical graph structures.

Examples include:

* Complete Graph
* Complete Bipartite Graph
* Wheel Graph
* Star Graph
* Grid Graph
* Tree
* DAG
---

### Example Graph Generator

Purpose-built graphs for demonstrating algorithms.

Examples include:

* Euler Path Graph
* Euler Circuit Graph
* Invalid Euler Graph
* Disconnected Graphs

---

## Graph Formatting

Convert graphs into multiple human-readable and machine-readable formats.

Supported formatters include:

* Compact
* Edge List
* Adjacency Matrix
* Statistics
* DOT (Graphviz)
* Mermaid
* JSON

```
GraphPrinter.compact(graph);
GraphPrinter.matrix(graph);
GraphPrinter.json(graph);
```

---

## Graph I/O

Read and write graphs using standard representations.

Current support:

* Edge List Reader
* Edge List Writer

Designed for future expansion to additional formats.

---

## Benchmarking

Measure algorithm performance using the built-in benchmarking framework.

Features include:

* Warm-up iterations
* Configurable benchmark iterations
* Average execution time
* Minimum execution time
* Maximum execution time
* Standard deviation
* Total execution time
* Operations per second

```
Benchmark.builder()
         .task(...)
         .build()
         .run();
```

---

## Stress Testing

Stress-test algorithms across graphs of varying sizes.

Features include:

* Configurable graph sizes
* Multiple graph presets
* Repeated execution
* Automatic graph generation
* Performance validation

Ideal for performance evaluation and regression testing.

---

## Examples

Graphite ships with a comprehensive collection of examples covering every major feature of the library.

Examples include:

* Graph Construction
* Graph Builders
* Graph Generation
* Traversal
* Shortest Paths
* Minimum Spanning Trees
* Connectivity
* Topological Sorting
* Bipartite Graphs
* Euler Graphs
* Graph Formatting
* Graph I/O
* Benchmarking
* Stress Testing

---

## Developer Experience

Graphite has been designed to provide a clean and consistent development experience.

Highlights include:

* Fluent API
* Immutable Result Objects
* Singleton Algorithm Implementations
* Cached Service Delegation
* Consistent Naming Conventions
* Comprehensive Validation
* Custom Exception Hierarchy
* Modular Architecture
* Extensive Example Suite
* Easy Extensibility for New Algorithms

# Quick Start

Graphite is designed around a fluent API that allows you to construct graphs, execute algorithms, generate graphs, and visualize results with minimal code.

This section introduces the most common workflows.

---

## Creating a Graph

Graphs can be created using the fluent builder API.

```java
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(0, 2)
        .addEdge(1, 3)
        .addEdge(2, 4)
        .build();
```

Weighted graphs are equally straightforward.

```java
IGraph graph = Graphs.undirected()
        .weighted()
        .addEdge(0, 1, 4)
        .addEdge(0, 2, 2)
        .addEdge(2, 3, 5)
        .build();
```

Graphs may also be created as immutable.

```java
IGraph graph = Graphs.undirected()
        .immutable(true)
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

---

## Running Algorithms

Algorithms are accessed through dedicated services attached directly to the graph.

### Traversal

```java
TraversalResult bfs =
        graph.traversal().bfs(0);

TraversalResult dfs =
        graph.traversal().dfs(0);
```

---

### Shortest Path

```java
ShortestPathResult result =
        graph.shortestPath().dijkstra(0);
```

---

### Minimum Spanning Tree

```java
MSTResult mst =
        graph.mst().prim();
```

---

### Connectivity

```
graph.connectivity().bridges();

graph.connectivity().articulationPoints();

graph.connectivity().stronglyConnectedComponents();
```

---

### Topological Sorting

```
graph.topology().kahnTopologicalSort();
```

---

### Cycle Detection

```java
boolean hasCycle =
        graph.cycle().hasCycle();
```

---

### Euler Graphs

```java
EulerResult path =
        graph.euler().path();

EulerResult circuit =
        graph.euler().circuit();
```

---

## Generating Graphs

Graphite includes multiple graph generators for testing and experimentation.

### Random Graph

```java
IGraph graph = Graphs.random()
        .undirected()
        .vertices(100)
        .edges(250)
        .connected()
        .immutable()
        .build();
```

---

### Preset Graph

```java
IGraph graph =
        Graphs.presets().mstGraph(1000);
```

---

### Pattern Graph

```java
IGraph graph =
        Graphs.patterns().grid(20, 20);
```

---

## Printing Graphs

Graphite includes several built-in graph formatters.

```
GraphPrinter.compact(graph);

GraphPrinter.edgeList(graph);

GraphPrinter.matrix(graph);

GraphPrinter.statistics(graph);
```

Graphs can also be exported for visualization.

```
GraphPrinter.dot(graph);

GraphPrinter.mermaid(graph);

GraphPrinter.json(graph);
```

---

## Reading and Writing Graphs

Graphs can be serialized using the built-in I/O utilities.

```
Graphs.write(graph)
        .edgeList("graph.txt");

IGraph loaded =
        Graphs.read()
              .edgeList("graph.txt");
```

---

## Benchmarking Algorithms

Measure algorithm performance with the built-in benchmarking framework.

```
BenchmarkResult result = Benchmark.builder()
        .name("Breadth First Search")
        .task(() -> graph.traversal().bfs(0))
        .warmup(10)
        .iterations(100)
        .build()
        .run();

System.out.println(result);
```

---

## Stress Testing

Stress-test algorithms against increasingly large graphs.

```
StressRunner.run(
        "BFS Stress Test",
        StressConfig.DEFAULT_CONFIG,
        GraphPresetGenerator::traversalGraph,
        graph -> graph.traversal().bfs(0)
);
```

---

Within just a few lines of code, Graphite enables graph construction, algorithm execution, graph generation, visualization, benchmarking, and stress testing through a consistent, fluent API.

# Architecture

Graphite V2 was designed with a single objective:

> **Provide a clean, modular, and intuitive architecture for graph development.**

Rather than exposing dozens of unrelated utility classes, Graphite organizes functionality into dedicated services attached directly to a graph instance. This creates a natural, discoverable API while keeping the internal implementation modular and easy to extend.

The architecture emphasizes separation of concerns, immutability, modularity, and consistency across the entire library.

---

# Architectural Overview

```
                          Graphs
                             │
        ┌────────────────────┴────────────────────┐
        │                                         │
   Graph Builders                         Graph Generators
        │                                         │
        └────────────────────┬────────────────────┘
                             │
                             ▼
                         IGraph
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
        ▼                    ▼                    ▼
  Mutable Graph        Immutable Graph      Graph Factory
                             │
                             ▼
                    Cached Service Layer
                             │
 ┌──────────────┬──────────────┬──────────────┬──────────────┐
 ▼              ▼              ▼              ▼              ▼
Traversal   ShortestPath      MST      Connectivity    Topology
Service       Service        Service      Service       Service
        │
        ├─────────────┬──────────────┬──────────────┐
        ▼             ▼              ▼              ▼
     Cycle      Bipartite        Euler        Formatting
```

Every feature is accessed from a single graph instance.

Developers never need to locate utility classes or instantiate algorithms manually.

---

# Service Delegation

One of the core ideas behind Graphite is **service delegation**.

Instead of this:

```
BreadthFirstSearch.traverse(graph, 0);

Dijkstra.shortestPath(graph, 0);

Prim.minimumSpanningTree(graph);
```

Graphite exposes algorithms through dedicated services.

```
graph.traversal().bfs(0);

graph.shortestPath().dijkstra(0);

graph.mst().prim();
```

This design provides several advantages.

* Better API discoverability
* Improved readability
* Logical grouping of algorithms
* Easier IDE auto-completion
* Clear separation between algorithm categories

Developers interact with capabilities rather than implementation classes.

---

# Cached Services

Each graph lazily creates its service objects and reuses them for subsequent calls.

```
graph.traversal();

graph.shortestPath();

graph.connectivity();
```

Internally, these services are cached by the graph instance.

This avoids unnecessary object allocation while keeping the public API simple and expressive.

Service objects themselves contain no graph state beyond a reference to the owning graph, allowing them to remain lightweight.

---

# Builder Architecture

Graph construction is completely separated from graph implementation.

```
Graphs
   │
   ▼
Graph Builder
   │
   ▼
Graph Configuration
   │
   ▼
Graph Factory
   │
   ▼
IGraph
```

Builders collect configuration and edges.

Factories create the correct graph implementation.

Graphs remain responsible only for graph behavior.

This separation keeps each component focused on a single responsibility.

---

# Graph Factory

Graphite uses the Factory Pattern to centralize graph creation.

The factory determines which implementation should be created based on the supplied configuration.

Examples include:

* Directed Graph
* Undirected Graph
* Immutable Graph
* Weighted Graph

The rest of the library never needs to know the concrete graph implementation.

Everything works against the `IGraph` abstraction.

---

# Immutable Graphs

Graphite treats immutability as a first-class concept.

Graphs can be created as immutable directly from the builder.

```java
IGraph graph = Graphs.undirected()
        .immutable()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

Immutable graphs prevent accidental structural modification after construction, making algorithms safer to execute and applications easier to reason about.

Both mutable and immutable implementations share the same public API.

---

# Algorithm Architecture

Each algorithm is implemented as an independent, stateless singleton.

```
BFS.INSTANCE

DFS.INSTANCE

Dijkstra.INSTANCE

Prim.INSTANCE

Kosaraju.INSTANCE
```

Algorithms never store graph state internally.

All required data is created during execution, allowing algorithm implementations to remain thread-safe, reusable, and easy to test.

---

# Result Objects

Instead of returning raw collections or primitive arrays, Graphite returns immutable result objects.

Examples include:

* TraversalResult
* ShortestPathResult
* MSTResult
* EulerResult
* BenchmarkResult

These records encapsulate algorithm output while providing a consistent interface across the library.

---

# Validation Layer

Graphite centralizes validation through reusable precondition utilities.

Examples include:

* Graph type validation
* Vertex validation
* Connectivity checks
* Weight validation
* Cycle validation

Algorithms focus exclusively on their logic while shared validation remains centralized.

---

# Exception Hierarchy

Graphite defines domain-specific exceptions rather than relying on generic runtime exceptions.

Examples include:

* InvalidVertexException
* NegativeWeightException
* NegativeCycleException
* GraphDisconnectedException
* UnsupportedGraphTypeException
* InvalidGraphConfigurationException

Meaningful exceptions improve debugging and clearly communicate incorrect API usage.

---

# Design Principles

Graphite V2 is guided by several architectural principles.

* Separation of Concerns
* Single Responsibility Principle
* Factory Pattern
* Builder Pattern
* Immutable-First Design
* Composition over Inheritance
* Stateless Algorithm Implementations
* Service Delegation
* Consistent Fluent API
* Modular Package Organization

These principles allow Graphite to grow without increasing architectural complexity.

---

# Why Graphite V2?

Graphite V2 represents a complete architectural redesign.

Compared to the original implementation, Version 2 introduces:

* Service-based API delegation
* Cached graph services
* Immutable graph support
* Unified graph builders
* Factory-driven graph creation
* Modular algorithm organization
* Consistent result records
* Graph generation framework
* Benchmarking framework
* Stress testing framework
* Comprehensive formatting utilities
* Improved validation and exception handling

The result is a cleaner, more maintainable, and significantly more extensible architecture that provides a consistent experience across the entire library.

# Graph Construction

Graph construction is the foundation of every graph application.

Graphite provides a fluent, type-safe builder API that makes graph creation expressive, readable, and easy to configure. Rather than manually instantiating graph implementations, developers construct graphs through builders exposed by the `Graphs` entry point.

```java
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

The builder collects graph configuration and edges before producing a fully initialized graph instance.

---

# Design Goals

The builder API was designed around a few simple principles.

* Fluent and readable syntax
* Minimal boilerplate
* Immutable-first construction
* Centralized validation
* Separation of configuration and implementation
* Consistent behavior across all graph types

This allows graph construction to remain simple regardless of graph size or complexity.

---

# The Builder Pattern

Graphite uses the Builder Pattern to separate graph construction from graph implementation.

```text
Graphs
   │
   ▼
Graph Builder
   │
   ▼
Graph Configuration
   │
   ▼
Graph Factory
   │
   ▼
IGraph
```

The builder is responsible only for collecting information.

The actual graph implementation is created later by the Graph Factory.

This keeps graph classes focused on graph behavior rather than construction logic.

---

# Entry Point

Every graph begins with the `Graphs` utility.

```
Graphs.directed();

Graphs.undirected();

Graphs.random();

Graphs.presets();

Graphs.patterns();

Graphs.examples();

Graphs.transform();

Graphs.read();
```

The `Graphs` class acts as the central entry point for the entire library.

---

# Creating an Undirected Graph

Undirected graphs are created using the dedicated builder.

```java
IGraph graph = Graphs.undirected()
        .addEdge(0, 1)
        .addEdge(0, 2)
        .addEdge(1, 3)
        .addEdge(2, 4)
        .build();
```

Every edge is automatically inserted in both directions.

---

# Creating a Directed Graph

Directed graphs preserve edge direction.

```java
IGraph graph = Graphs.directed()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .addEdge(2, 3)
        .build();
```

Algorithms that require directed graphs automatically validate the graph type before execution.

---

# Weighted Graphs

Weighted graphs are enabled through the builder configuration.

```java
IGraph graph = Graphs.undirected()
        .weighted()
        .addEdge(0, 1, 4)
        .addEdge(0, 2, 8)
        .addEdge(1, 3, 6)
        .build();
```

Once enabled, every edge may carry an integer weight.

Weighted graphs integrate seamlessly with the shortest path and minimum spanning tree algorithms.

---

# Immutable Graphs

Graphite supports immutable graph instances directly from the builder.

```java
IGraph graph = Graphs.undirected()
        .immutable()
        .addEdge(0, 1)
        .addEdge(1, 2)
        .build();
```

Immutable graphs cannot be structurally modified after construction.

This provides several advantages.

* Safer algorithm execution
* Easier debugging
* Predictable behavior
* Thread-friendly usage
* Reduced accidental mutation

---

# Fluent Configuration

Builders support fluent configuration before construction.

Example:

```java
IGraph graph = Graphs.random()
        .undirected()
        .vertices(1000)
        .edges(2500)
        .connected()
        .weighted()
        .weightRange(1, 50)
        .immutable()
        .build();
```

Each configuration step returns the builder itself, allowing expressive method chaining.

---

# Edge Construction

Edges may be added individually.

```
.addEdge(0, 1)

.addEdge(1, 2)

.addEdge(2, 3)
```

Weighted edges simply include the third argument.

```
.addEdge(0, 1, 15)
```

The API intentionally mirrors mathematical graph notation, keeping graph definitions concise and readable.

---

# Builder Validation

Before graph creation, Graphite validates the supplied configuration.

Examples include:

* Invalid vertex counts
* Invalid edge counts
* Invalid weight ranges
* Unsupported graph configurations
* Illegal random graph parameters

Configuration errors are detected before graph construction begins.

---

# Graph Configuration

Internally, builders store configuration separately from graph implementations.

Configuration includes properties such as:

* Number of vertices
* Number of edges
* Directed / Undirected
* Weighted / Unweighted
* Connected graphs
* Immutable graphs
* Self-loop support
* Parallel edge support
* Weight ranges

Separating configuration from implementation keeps graph classes lightweight and focused.

---

# Building the Graph

Construction finishes with a single call.

```java
IGraph graph = builder.build();
```

During this step Graphite:

1. Validates the configuration.
2. Creates the correct graph implementation.
3. Inserts all edges.
4. Applies immutable wrapping when requested.
5. Returns an `IGraph` ready for use.

No additional initialization is required.

---

# Why a Builder?

Without builders, graph construction quickly becomes verbose.

Instead of:

```
UndirectedGraph graph = new UndirectedGraph(5);

graph.addEdge(0,1);
graph.addEdge(1,2);
graph.addEdge(2,3);
graph.addEdge(3,4);
```

Graphite encourages:

```java
IGraph graph = Graphs.undirected()
        .addEdge(0,1)
        .addEdge(1,2)
        .addEdge(2,3)
        .addEdge(3,4)
        .build();
```

The fluent style is shorter, easier to read, and naturally supports additional configuration without introducing multiple constructors or complex initialization logic.

---

# Summary

Graphite's graph construction API combines the Builder Pattern, Factory Pattern, and immutable-first design into a single, consistent workflow.

Whether creating a small demonstration graph or generating a graph containing thousands of vertices, the construction process remains identical:

1. Choose a builder.
2. Configure the graph.
3. Add edges.
4. Build.
5. Execute algorithms.

This consistency is one of the core design goals of Graphite V2.

# Graph Generation Framework

Constructing graphs manually is ideal for small examples, but real-world applications often require graphs containing hundreds or thousands of vertices.

Graphite's **Graph Generation Framework** provides a collection of specialized generators for different scenarios, including algorithm testing, benchmarking, demonstrations, and mathematical graph construction.

Instead of relying on a single random generator, Graphite offers multiple generation strategies through a unified API.

```
Graphs.random();

Graphs.presets();

Graphs.patterns();

Graphs.examples();
```

Each generator is designed for a specific purpose while sharing the same fluent, consistent design philosophy.

---

# Framework Overview

```text
                    Graphs
                       │
     ┌─────────────────┼─────────────────┐
     │                 │                 │
     ▼                 ▼                 ▼
 Random Generator  Preset Generator  Pattern Generator
                                           │
                                           ▼
                                  Example Generator
```

Each generator produces an `IGraph`, allowing every graph to work seamlessly with the rest of the Graphite ecosystem.

---

# Random Graph Generator

The Random Graph Generator creates configurable graphs for testing, experimentation, and large-scale benchmarking.

Developers control every important aspect of graph generation through a fluent builder API.

```java
IGraph graph = Graphs.random()
        .undirected()
        .vertices(1000)
        .edges(2500)
        .connected()
        .immutable()
        .build();
```

---

## Features

The random generator supports:

* Directed graphs
* Undirected graphs
* Weighted graphs
* Unweighted graphs
* Connected graph generation
* Immutable graph generation
* Custom edge counts
* Configurable weight ranges
* Optional self-loops
* Optional parallel edges

Example:

```java
IGraph graph = Graphs.random()
        .directed()
        .vertices(500)
        .edges(2000)
        .weighted()
        .weightRange(1, 100)
        .immutable()
        .build();
```

The fluent configuration keeps graph generation expressive while hiding implementation complexity.

---

# Preset Graph Generator

Some algorithms perform best when evaluated against specific graph structures.

The Preset Graph Generator provides ready-made graphs designed for algorithm development, testing, benchmarking, and demonstrations.

```java
IGraph graph =
        Graphs.presets().traversalGraph(1000);
```

Unlike purely random graphs, preset graphs follow predefined characteristics that make them suitable for particular algorithm categories.

---

## Available Presets

### Traversal Graph

Optimized for BFS and DFS.

```
Graphs.presets().traversalGraph(1000);
```

---

### Sparse Graph

Contains relatively few edges.

Ideal for algorithms whose complexity depends on edge count.

```
Graphs.presets().sparseGraph(1000);
```

---

### Dense Graph

Generates graphs with a high edge density.

Useful for evaluating worst-case algorithm behavior.

```
Graphs.presets().denseGraph(1000);
```

---

### Weighted Graph

Creates connected weighted graphs suitable for shortest path and minimum spanning tree algorithms.

```
Graphs.presets().weightedGraph(1000);
```

---

### MST Graph

Specialized weighted graph for minimum spanning tree algorithms.

```
Graphs.presets().mstGraph(1000);
```

---

### Directed Graph

Generates a general directed graph.

```
Graphs.presets().directedGraph(1000);
```

---

### Directed Sparse Graph

Sparse directed graph optimized for connectivity algorithms.

```
Graphs.presets().directedSparseGraph(1000);
```

---

### Directed Dense Graph

Dense directed graph for large-scale testing.

```
Graphs.presets().directedDenseGraph(1000);
```

---

### Tree

Generates a connected acyclic graph.

```
Graphs.presets().treeGraph(1000);
```

---

### Directed Acyclic Graph (DAG)

Ideal for topological sorting and dependency analysis.

```
Graphs.presets().dag(1000);
```

---

### Bipartite Graph

Creates balanced bipartite graphs.

```
Graphs.presets().bipartiteGraph(1000);
```

---

# Pattern Graph Generator

The Pattern Graph Generator constructs well-known mathematical graph structures.

These graphs are useful for education, demonstrations, visualization, and algorithm validation.

Example:

```java
IGraph graph =
        Graphs.patterns().wheel(10);
```

---

## Available Patterns

Graphite includes generators for classical graph families.

Examples include:

* Complete Graph
* Complete Bipartite Graph
* Star Graph
* Wheel Graph
* Tree
* Grid
* Directed Acyclic Graph (DAG)

Each pattern guarantees the mathematical properties associated with that structure.

---

# Example Graph Generator

Documentation and tutorials often require carefully designed graphs rather than randomly generated ones.

The Example Graph Generator provides these predefined graphs.

```java
IGraph graph =
        Graphs.examples().eulerCircuitGraph(8);
```

These graphs are intentionally constructed to demonstrate specific algorithm behavior.

---

## Available Examples

Examples currently include:

* Euler Path Graph
* Euler Circuit Graph
* Invalid Euler Graph
* Disconnected Graph

These graphs are used throughout Graphite's examples, benchmarks, and documentation.

---

# Unified API

Despite serving different purposes, every generator follows the same entry-point philosophy.

```
Graphs.random()

Graphs.presets()

Graphs.patterns()

Graphs.examples()
```

Every generator returns an `IGraph`, allowing algorithms, formatters, validators, benchmarks, and stress tests to operate without any special handling.

---

# Design Philosophy

The Graph Generation Framework was designed around three principles.

### Purpose-Built

Each generator solves a specific problem instead of trying to be a universal solution.

### Consistency

Every generator integrates with the same fluent API and returns the same graph abstraction.

### Extensibility

New generators and graph families can be added without affecting existing APIs or implementations.

This modular approach allows the framework to grow while maintaining a clean and predictable developer experience.

---

# Summary

The Graph Generation Framework eliminates the need to manually construct graphs for every use case.

Whether generating a random graph for benchmarking, a preset graph for algorithm evaluation, a mathematical graph pattern, or a carefully crafted demonstration graph, Graphite provides a consistent and intuitive solution through a unified API.

# Algorithm Services

Graphite organizes algorithms into dedicated services attached directly to a graph instance.

Instead of exposing dozens of independent utility classes, algorithms are grouped by functionality and accessed through a fluent, discoverable API.

```id="service-api"
graph.traversal();

graph.shortestPath();

graph.mst();

graph.connectivity();

graph.topology();

graph.cycle();

graph.bipartite();

graph.euler();
```

This service-oriented design keeps the public API intuitive while allowing the internal implementation to remain modular and extensible.

---

# Service Architecture

```text id="service-architecture"
                    IGraph
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Traversal      Shortest Path          MST
      │                │                │
      ▼                ▼                ▼
 Connectivity     Topology          Cycle
      │
      ├─────────────┬─────────────┐
      ▼             ▼             ▼
 Bipartite      Euler       Formatting
```

Each service owns a related family of algorithms.

This organization improves discoverability, keeps responsibilities focused, and makes future expansion straightforward.

---

# Why Services?

Many graph libraries expose algorithms as static utility methods.

```id="traditional-api"
BreadthFirstSearch.traverse(graph, 0);

Dijkstra.shortestPath(graph, 0);

Prim.minimumSpanningTree(graph);
```

Graphite instead groups algorithms by capability.

```id="graphite-api"
graph.traversal().bfs(0);

graph.shortestPath().dijkstra(0);

graph.mst().prim();
```

This approach offers several advantages:

* Better API discoverability
* Cleaner code
* Consistent naming
* IDE-friendly auto-completion
* Natural grouping of related algorithms
* Easy extension with additional algorithms

---

# Available Services

Graphite currently provides the following algorithm services.

| Service       | Purpose                            |
| ------------- | ---------------------------------- |
| Traversal     | Graph exploration                  |
| Cycle         | Cycle detection                    |
| Shortest Path | Path finding                       |
| MST           | Minimum spanning trees             |
| Connectivity  | Graph connectivity analysis        |
| Topology      | Topological ordering               |
| Bipartite     | Bipartite graph validation         |
| Euler         | Euler path and circuit computation |

Each service exposes a focused collection of algorithms through a consistent interface.

---

# Stateless Algorithms

Internally, every algorithm is implemented as a singleton.

```id="singleton"
BFS.INSTANCE

DFS.INSTANCE

Dijkstra.INSTANCE

Prim.INSTANCE

Kosaraju.INSTANCE
```

Algorithms do not retain graph state between executions.

Instead, all execution state is created locally during each invocation, making implementations lightweight, reusable, and thread-friendly.

---

# Cached Services

Service objects are created lazily and cached by the graph instance.

```id="cached"
graph.traversal();

graph.traversal();

graph.traversal();
```

Regardless of how many times a service is requested, the same lightweight instance is reused internally.

This minimizes object allocation while keeping the public API clean.

---

# Result Objects

Most algorithms return immutable result records instead of primitive arrays or raw collections.

Examples include:

* TraversalResult
* ShortestPathResult
* MSTResult
* EulerResult

This provides a consistent API across the entire library while making algorithm output easier to understand and extend.

---

# Validation

Before execution, Graphite automatically validates algorithm preconditions.

Examples include:

* Directed graph requirements
* Undirected graph requirements
* Invalid vertices
* Negative edge weights
* Graph connectivity
* Cycle constraints

Validation is centralized so that algorithms remain focused solely on their core logic.

---

# Complexity

Each service documents the time and space complexity of its algorithms, allowing developers to choose the most appropriate implementation for their use case.

---

# Service Overview

The following sections describe every algorithm service in detail, including supported algorithms, API examples, complexity, requirements, and typical applications.
