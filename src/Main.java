import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a weighted graph
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Add vertices to the graph
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        Vertex<String> v6 = new Vertex<>("F");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);

        // Add edges to the graph
        graph.addEdge(v1, v2, 2);
        graph.addEdge(v1, v3, 4);
        graph.addEdge(v2, v4, 3);
        graph.addEdge(v2, v5, 1);
        graph.addEdge(v3, v4, 2);
        graph.addEdge(v4, v5, 2);
        graph.addEdge(v4, v6, 4);
        graph.addEdge(v5, v6, 1);

        // Perform breadth-first search
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        List<Vertex<String>> bfsTraversal = bfs.traverse(v1);

        System.out.println("Breadth-First Search:");
        for (Vertex<String> vertex : bfsTraversal) {
            System.out.println(vertex);
        }

        // Perform Dijkstra's algorithm
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.traverse(v1);

        // Get the shortest path from A to F
        List<Vertex<String>> shortestPath = dijkstra.getShortestPath(v6);

        System.out.println("Shortest Path from A to F:");
        for (Vertex<String> vertex : shortestPath) {
            System.out.println(vertex);
        }
    }
}
