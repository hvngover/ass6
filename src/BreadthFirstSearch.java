import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph) {
        super(graph);
    }

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
}
