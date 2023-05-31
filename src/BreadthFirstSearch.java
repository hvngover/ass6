import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph) {
        super(graph);
    }

    public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        List<Vertex<V>> visited = new ArrayList<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            processVertex(currentVertex, visited, queue);
        }

        return visited;
    }

    private void processVertex(Vertex<V> vertex, List<Vertex<V>> visited, Queue<Vertex<V>> queue) {
        List<Vertex<V>> adjacentVertices = getGraph().getAdjacentVertices(vertex);

        for (Vertex<V> adjacentVertex : adjacentVertices) {
            if (!visited.contains(adjacentVertex)) {
                visited.add(adjacentVertex);
                queue.offer(adjacentVertex);
            }
        }
    }
}
