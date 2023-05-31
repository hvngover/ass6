import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data; // The data associated with the vertex
    private Map<Vertex<V>, Double> adjacentVertices; // Map of adjacent vertices and their corresponding edge weights

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }
    // Returns the data associated with the vertex

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }
    // Adds an adjacent vertex with the specified weight

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }
    // Returns a map of adjacent vertices and their corresponding edge weights

    @Override
    public String toString() {
        return data.toString();
    }
    // Returns a string representation of the vertex

    // Additional methods or modifications can be added here
}
