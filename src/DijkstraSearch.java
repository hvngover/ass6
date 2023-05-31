import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> shortestDistances;
    private Map<Vertex<V>, Vertex<V>> previousVertices;

    public DijkstraSearch(WeightedGraph<V> graph) {
        super(graph);
    }

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

    // Returns the shortest distance for a given vertex
    public double getShortestDistance(Vertex<V> vertex) {
        return shortestDistances.get(vertex);
    }

    // Sets the shortest distance for a given vertex
    public void setShortestDistance(Vertex<V> vertex, double distance) {
        shortestDistances.put(vertex, distance);
    }

    // Returns the previous vertex in the shortest path for a given vertex
    public Vertex<V> getPreviousVertex(Vertex<V> vertex) {
        return previousVertices.get(vertex);
    }

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

    @Override
    public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        // Perform Dijkstra's algorithm
        dijkstra(startVertex);
        return null;
    }

    // Dijkstra's algorithm implementation
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
}
