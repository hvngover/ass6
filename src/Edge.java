public class Edge<V> {
    private V source; // The source vertex of the edge
    private V destination; // The destination vertex of the edge
    private Double weight; // The weight of the edge

    public Edge(V source, V destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public V getSource() {
        return source;
    }

    public void setSource(V source) {
        this.source = source;
    }

    public V getDestination() {
        return destination;
    }

    public void setDestination(V destination) {
        this.destination = destination;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + destination + ", weight: " + weight + ")";
    }
}
