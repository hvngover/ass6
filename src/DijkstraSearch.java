import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> shortestDistances;
    private Map<Vertex<V>, Vertex<V>> previousVertices;

    public DijkstraSearch(WeightedGraph<V> graph) {
        super(graph);
    }

    public void initializeDistances() {
        shortestDistances = new HashMap<>();
        previousVertices = new HashMap<>();

        for (Vertex<V> vertex : getGraph().getVertices()) {
            shortestDistances.put(vertex, Double.POSITIVE_INFINITY);
            previousVertices.put(vertex, null);
        }
    }

    public double getShortestDistance(Vertex<V> vertex) {
        return shortestDistances.get(vertex);
    }

    public void setShortestDistance(Vertex<V> vertex, double distance) {
        shortestDistances.put(vertex, distance);
    }

    public Vertex<V> getPreviousVertex(Vertex<V> vertex) {
        return previousVertices.get(vertex);
    }

    public List<Vertex<V>> getShortestPath(Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> currentVertex = destination;

        while (currentVertex != null) {
            path.add(0, currentVertex);
            currentVertex = getPreviousVertex(currentVertex);
        }

        return path;
    }

    @Override
    public List<Vertex<V>> traverse(Vertex<V> startVertex) {
        dijkstra(startVertex);
        return null;
    }

    public void dijkstra(Vertex<V> startVertex) {
        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(this::getShortestDistance));

        initializeDistances();
        setShortestDistance(startVertex, 0);
        priorityQueue.offer(startVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> currentVertex = priorityQueue.poll();
            double currentDistance = getShortestDistance(currentVertex);

            List<Vertex<V>> adjacentVertices = getGraph().getAdjacentVertices(currentVertex);

            for (Vertex<V> neighbor : adjacentVertices) {
                double edgeWeight = getGraph().getWeight(currentVertex, neighbor);
                double newDistance = currentDistance + edgeWeight;

                if (newDistance < getShortestDistance(neighbor)) {
                    priorityQueue.remove(neighbor);
                    setShortestDistance(neighbor, newDistance);
                    previousVertices.put(neighbor, currentVertex);
                    priorityQueue.offer(neighbor);
                }
            }
        }
    }
}
