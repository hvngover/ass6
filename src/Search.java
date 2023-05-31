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

    public WeightedGraph<V> getGraph() {
        return graph;
    }
    // Returns the weighted graph associated with this search.

}
