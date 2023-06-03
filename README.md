# Graphs

## Weighted Graph and Vertex Classes

The **```WeightedGraph<V>```** class represents a weighted graph, where V is the type of data associated with each vertex. It provides methods to add vertices, add edges with weights, get adjacent vertices, get the weight of an edge, and get a list of all vertices in the graph.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;

    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }
    
``` 
## Methods

* **```addVertex(Vertex<V> vertex):```** 

Adds a vertex to the graph by creating an entry in the adjacency map with an empty list of adjacent vertices.

```java
public void addVertex(Vertex<V> vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }
    // Adds a vertex to the graph
```

* **```addEdge(Vertex<V> source, Vertex<V> destination, double weight):```** 

Adds an edge with the specified weight between the source and destination vertices. It validates the existence of the vertices, adds the destination vertex to the list of adjacent vertices for the source vertex, and stores the weight in the adjacency map.

```java
 public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertexExistence(source, destination);

        source.addAdjacentVertex(destination, weight);
        adjacencyMap.get(source).add(destination);
    }
```

* **```getAdjacentVertices(Vertex<V> vertex):```** 

Returns a list of adjacent vertices for the specified vertex by retrieving the corresponding entry from the adjacency map.

```java
 public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        validateVertexExistence(vertex);

        return adjacencyMap.get(vertex);
    }
    // Returns a list of adjacent vertices for the specified vertex
```


* **```getWeight(Vertex<V> source, Vertex<V> destination):```** 

Returns the weight of the edge between the source and destination vertices by retrieving the weight from the adjacency map.

```java
 public double getWeight(Vertex<V> source, Vertex<V> destination) {
        validateVertexExistence(source, destination);

        return source.getAdjacentVertices().get(destination);
    }
    // Returns the weight of the edge between the source and destination vertices
```

* **```getVertices():```** 

Returns a list of all vertices in the graph by creating a new list from the keys of the adjacency map.

```java
public List<Vertex<V>> getVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }
    // Returns a list of all vertices in the graph
```

* **```alidateVertexExistence(Vertex<V>... vertices):```** 

Validates the existence of the specified vertices in the graph by checking if they are present in the adjacency map.

```java
private void validateVertexExistence(Vertex<V>... vertices) {
        for (Vertex<V> vertex : vertices) {
            if (!adjacencyMap.containsKey(vertex)) {
                throw new IllegalArgumentException("Vertex not found in the graph.");
            }
        }
    }
    // Validates the existence of the specified vertices in the graph
```

## Vertex
The **```Vertex<V>```** 

class represents a vertex in a weighted graph. It stores the associated data of type V and maintains a map of adjacent vertices and their corresponding edge weights.

```java
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

```

## Methods

* **```addAdjacentVertex(Vertex<V> destination, double weight):```** 

Adds an adjacent vertex with the specified weight to the map of adjacent vertices.

```java
public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }
    // Adds an adjacent vertex with the specified weight
```

* **```getAdjacentVertices():```** 

Returns a map of adjacent vertices and their corresponding edge weights.

```java
public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }
    // Returns a map of adjacent vertices and their corresponding edge weights
```

* **```getData():```** 

Returns the associated data of the vertex.

```java
public V getData() {
        return data;
    }
    // Returns the data associated with the vertex@Override
    public String toString() {
        return data.toString();
    }
    // Returns a string representation of the vertex

```

* **```toString():```** 

Returns a string representation of the vertex by calling the toString() method on its data.

```java
@Override
    public String toString() {
        return data.toString();
    }
    // Returns a string representation of the vertex

```

## Search class

The **```Search<V>```** class is an abstract class that serves as a base for search algorithms on a weighted graph. It takes a WeightedGraph<V> object as a constructor parameter and provides an abstract traverse method that needs to be implemented by subclasses.

```java
import java.util.List;

public abstract class Search<V> {
    protected WeightedGraph<V> graph; // The weighted graph to perform search on

    public Search(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public abstract List<Vertex<V>> traverse(Vertex<V> startVertex);
    // This method is abstract and needs to be implemented by subclasses.
    // It performs the search traversal starting from the specified start vertex
    // and returns the visited vertices.
  ```

## Methods

*  **```traverse(Vertex<V> startVertex):```** 

Abstract method that performs the search traversal starting from a specified start vertex and returns a list of visited vertices.
  
```java
public WeightedGraph<V> getGraph() {
        return graph;
    }
```
  
## BreadthFirstSearch Class
**``` The BreadthFirstSearch<V>```** class is a subclass of the Search<V> abstract class. It implements the breadth-first search algorithm.
 
  ```java
  
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph) {
        super(graph);
    }
}

```
  
## Methods
  
*  **```traverse(Vertex<V> startVertex):```**
  
Performs breadth-first search traversal starting from a specified start vertex. It uses a **Queue** to keep track of the vertices to visit in a breadth-first manner. The algorithm visits each vertex's adjacent vertices and adds them to the queue if they have not been visited before.
  
  
```java
  
// Performs breadth-first search traversal starting from the specified start vertex
    public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        List<Vertex<V>> visited = new ArrayList<>(); // List to store visited vertices
        Queue<Vertex<V>> queue = new LinkedList<>(); // Queue to store vertices to visit

        visited.add(startVertex); // Mark the start vertex as visited
        queue.offer(startVertex); // Add the start vertex to the queue

        // Process vertices in the queue until the queue is empty
        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll(); // Get the next vertex from the queue
            processVertex(currentVertex, visited, queue); // Process the current vertex
        }

        return visited; // Return the list of visited vertices
    }
  
```

* **```processVertex(Vertex<V> vertex, List<Vertex<V>> visited, Queue<Vertex<V>> queue):```**
  
Helper method that processes a vertex by visiting its adjacent vertices. It adds the adjacent vertices to the queue if they have not been visited before.
  
```java
  
// Process a vertex by visiting its adjacent vertices
    private void processVertex(Vertex<V> vertex, List<Vertex<V>> visited, Queue<Vertex<V>> queue) {
        List<Vertex<V>> adjacentVertices = getGraph().getAdjacentVertices(vertex); // Get adjacent vertices

        // Visit each adjacent vertex
        for (Vertex<V> adjacentVertex : adjacentVertices) {
            if (!visited.contains(adjacentVertex)) { // If the adjacent vertex has not been visited
                visited.add(adjacentVertex); // Mark it as visited
                queue.offer(adjacentVertex); // Add it to the queue for further processing
            }
        }
    }
  
```
  
## DijkstraSearch Class

The **```DijkstraSearch<V>```** class is another subclass of the Search<V> abstract class. It implements Dijkstra's algorithm to find the shortest path in a weighted graph.

```java
import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> shortestDistances;
    private Map<Vertex<V>, Vertex<V>> previousVertices;

    public DijkstraSearch(WeightedGraph<V> graph) {
        super(graph);
    }
```

## Methods

* **```traverse(Vertex<V> startVertex):```** 

Initializes the necessary data structures and then calls the dijkstra method to perform Dijkstra's algorithm.

```java
public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        // Perform Dijkstra's algorithm
        dijkstra(startVertex);
        return null;
    }
```

* **```dijkstra(Vertex<V> startVertex):```** 

Implements Dijkstra's algorithm by iteratively updating the shortest distances and previous vertices until all vertices have been visited. It uses a PriorityQueue to process vertices based on their shortest distances.

```java
public void dijkstra(Vertex<V> startVertex) {
        // Priority queue to store vertices based on shortest distance
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(this::getShortestDistance));

        // Initialize distances and set start vertex distance to 0
        initializeDistances();
        setShortestDistance(startVertex, 0);
        priorityQueue.offer(startVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> currentVertex = priorityQueue.poll();
            double currentDistance = getShortestDistance(currentVertex);

            // Get adjacent vertices of the current vertex
            List<Vertex<V>> adjacentVertices = getGraph().getAdjacentVertices(currentVertex);

            // Iterate through adjacent vertices
            for (Vertex<V> neighbor : adjacentVertices) {
                double edgeWeight = getGraph().getWeight(currentVertex, neighbor);
                double newDistance = currentDistance + edgeWeight;

                // If new distance is shorter, update shortest distance, previous vertex, and add to priority queue
                if (newDistance < getShortestDistance(neighbor)) {
                    priorityQueue.remove(neighbor);
                    setShortestDistance(neighbor, newDistance);
                    previousVertices.put(neighbor, currentVertex);
                    priorityQueue.offer(neighbor);
                }
            }
        }
    }
```

* **```initializeDistances():```** 

Initializes the shortestDistances and previousVertices maps, setting all distances to infinity and all previous vertices to null.

```java
// Initializes the distances map and previous vertices map
    public void initializeDistances() {
        shortestDistances = new HashMap<>();
        previousVertices = new HashMap<>();

        // Set all distances to infinity and previous vertices to null
        for (Vertex<V> vertex : getGraph().getVertices()) {
            shortestDistances.put(vertex, Double.POSITIVE_INFINITY);
            previousVertices.put(vertex, null);
        }
    }
```

* **```getShortestDistance(Vertex<V> vertex):```** 

Returns the shortest distance for a given vertex.

```java
// Returns the shortest distance for a given vertex
    public double getShortestDistance(Vertex<V> vertex) {
        return shortestDistances.get(vertex);
    }
```

* **```setShortestDistance(Vertex<V> vertex, double distance):```** 

Sets the shortest distance for a given vertex.

```java
// Sets the shortest distance for a given vertex
    public void setShortestDistance(Vertex<V> vertex, double distance) {
        shortestDistances.put(vertex, distance);
    }
```

* **```getPreviousVertex(Vertex<V> vertex):```** 
  
Returns the previous vertex in the shortest path for a given vertex.

```java
// Returns the previous vertex in the shortest path for a given vertex
    public Vertex<V> getPreviousVertex(Vertex<V> vertex) {
        return previousVertices.get(vertex);
    }
```

* **```getShortestPath(Vertex<V> destination):```** 

Returns the shortest path from the start vertex to the destination vertex by traversing backwards from the destination vertex to the start vertex.
  
```java
// Returns the shortest path from start vertex to destination vertex
    public List<Vertex<V>> getShortestPath(Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> currentVertex = destination;

        // Traverse backwards from destination to start
        while (currentVertex != null) {
            path.add(0, currentVertex);
            currentVertex = getPreviousVertex(currentVertex);
        }

        return path;
    }
```


## Edge Class

The **```Edge<V>```** class represents an edge in a weighted graph. It has three attributes: source, which represents the source vertex, destination, which represents the destination vertex, and weight, which represents the weight of the edge.

```java
public class Edge<V> {
    private V source; // The source vertex of the edge
    private V destination; // The destination vertex of the edge
    private Double weight; // The weight of the edge

    public Edge(V source, V destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public V getSource() {
        return source;
    }

    public void setSource(V source) {
        this.source = source;
    }

    public V getDestination() {
        return destination;
    }

    public void setDestination(V destination) {
        this.destination = destination;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + destination + ", weight: " + weight + ")";
    }
}
```

## Methods

* **``Edge(Vertex<V> source, Vertex<V> destination, double weight):```** This is the constructor of the Edge class. It takes the source vertex, the destination vertex, and the weight of the edge as parameters, and initializes the corresponding instance variables.

```java
public Edge(V source, V destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
```

* **``Vertex<V> getSource():```** This method returns the source vertex of the edge.

```java
public V getSource() {
        return source;
    }

```

* **``void setSource(Vertex<V> source):```** This method sets the source vertex of the edge to the specified source vertex.

```java
public void setSource(V source) {
        this.source = source;
    }

```

* **``Vertex<V> getDestination():```** This method returns the destination vertex of the edge.

```java
public V getDestination() {
        return destination;
    }

```

* **``void setDestination(Vertex<V> destination):```** This method sets the destination vertex of the edge to the specified destination vertex.

```java
 public void setDestination(V destination) {
        this.destination = destination;
    }
```

* **``double getWeight():```** This method returns the weight of the edge.

```java
public Double getWeight() {
        return weight;
    }
```

* **``void setWeight(double weight):```** This method sets the weight of the edge to the specified weight.

```java
public void setWeight(Double weight) {
        this.weight = weight;
    }
```

## Usage

```java
public class Main {
    public static void main(String[] args) {
        // Create a weighted graph
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Add vertices to the graph
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);

        // Add edges to the graph
        graph.addEdge(a, b, 5.0);
        graph.addEdge(a, c, 2.0);
        graph.addEdge(b, d, 3.0);
        graph.addEdge(c, d, 1.0);
        graph.addEdge(c, e, 4.0);
        graph.addEdge(d, e, 6.0);

        // Create an instance of BreadthFirstSearch and perform breadth-first search traversal
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        List<Vertex<String>> bfsTraversal = bfs.traverse(a);
        System.out.println("BFS traversal: " + bfsTraversal);
        // Output: BFS traversal: [A, B, C, D, E]

        // Create an instance of DijkstraSearch and perform Dijkstra's algorithm
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.traverse(a);

        // Get the shortest distance from vertex A to vertex E
        double shortestDistance = dijkstra.getShortestDistance(e);
        System.out.println("Shortest distance from A to E: " + shortestDistance);
        // Output: Shortest distance from A to E: 8.0

        // Get the shortest path from vertex A to vertex E
        List<Vertex<String>> shortestPath = dijkstra.getShortestPath(e);
        System.out.println("Shortest path from A to E: " + shortestPath);
        // Output: Shortest path from A to E: [A, C, E]
    }
}

```
## Output

```java
BFS traversal: [A, B, C, D, E]
Shortest distance from A to E: 8.0
Shortest path from A to E: [A, C, E]
```


  





