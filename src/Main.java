import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);
        WeightedGraph<Integer> graph = new WeightedGraph<>();


        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addEdge(vertex1, vertex2, 1.5);
        graph.addEdge(vertex2, vertex3, 2.0);
        graph.addEdge(vertex3, vertex4, 2.5);
        graph.addEdge(vertex4, vertex5, 3.0);
        graph.addEdge(vertex1, vertex3, 1.0);
        graph.addEdge(vertex3, vertex5, 1.5);

        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(graph);
        List<Vertex<Integer>> bfsTraversal = bfs.traverse(vertex1);
        System.out.println("BFS traversal: " + bfsTraversal);

        DijkstraSearch<Integer> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.traverse(vertex1);
        List<Vertex<Integer>> shortestPath = dijkstra.getShortestPath(vertex5);
        System.out.println("Shortest path from 1 to 5: " + shortestPath);
    }
}
