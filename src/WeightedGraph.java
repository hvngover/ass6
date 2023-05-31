import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;

    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertexExistence(source, destination);

        source.addAdjacentVertex(destination, weight);
        adjacencyMap.get(source).add(destination);
    }

    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        validateVertexExistence(vertex);

        return adjacencyMap.get(vertex);
    }

    public double getWeight(Vertex<V> source, Vertex<V> destination) {
        validateVertexExistence(source, destination);

        return source.getAdjacentVertices().get(destination);
    }

    public List<Vertex<V>> getVertices() {
        return new ArrayList<>(adjacencyMap.keySet());
    }

    private void validateVertexExistence(Vertex<V>... vertices) {
        for (Vertex<V> vertex : vertices) {
            if (!adjacencyMap.containsKey(vertex)) {
                throw new IllegalArgumentException("Vertex not found in the graph.");
            }
        }
    }
}
