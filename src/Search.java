import java.util.List;

public abstract class Search<V> {
    protected WeightedGraph<V> graph;

    public Search(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        // Implementation specific to each search algorithm
        return null;
    }

    public WeightedGraph<V> getGraph() {
        return graph;
    }
}
