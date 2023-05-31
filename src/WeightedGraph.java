import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;

    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }
    // Constructs a new weighted graph

    public void addVertex(Vertex<V> vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }
    // Adds a vertex to the graph

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertexExistence(source, destination);

        source.addAdjacentVertex(destination, weight);
        adjacencyMap.get(source).add(destination);
    }
    // Adds an edge with the specified weight between the source and destination vertices

    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        validateVertexExistence(vertex);

        return adjacencyMap.get(vertex);
    }
    // Returns a list of adjacent vertices for the specified vertex

    public double getWeight(Vertex<V> source, Vertex<V> destination) {
        validateVertexExistence(source, destination);

        return source.getAdjacentVertices().get(destination);
    }
    // Returns the weight of the edge between the source and destination vertices

    public List<Vertex<V>> getVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }
    // Returns a list of all vertices in the graph

    private void validateVertexExistence(Vertex<V>... vertices) {
        for (Vertex<V> vertex : vertices) {
            if (!adjacencyMap.containsKey(vertex)) {
                throw new IllegalArgumentException("Vertex not found in the graph.");
            }
        }
    }
    // Validates the existence of the specified vertices in the graph
}
