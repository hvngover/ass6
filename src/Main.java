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
