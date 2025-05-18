import java.util.HashMap;
import java.util.Map;

/**
 * Represents a vertex in a weighted graph, storing data and adjacent vertices with edge weights.
 * @param <V> The type of data stored in the vertex.
 */
public class Vertex<V> {
    private final V data;
    private final Map<Vertex<V>, Double> adjacentVertices;

    /**
     * Constructs a vertex with the specified data.
     * @param data The data to be stored in the vertex.
     */
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    /**
     * Adds an adjacent vertex with a weighted edge.
     * @param destination The adjacent vertex.
     * @param weight The weight of the edge connecting this vertex to the destination.
     */
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    /**
     * Returns the data stored in the vertex.
     * @return The vertex data.
     */
    public V getData() {
        return data;
    }

    /**
     * Returns a map of adjacent vertices and their corresponding edge weights.
     * @return Map of adjacent vertices with weights.
     */
    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    /**
     * Returns a string representation of the vertex (its data).
     * @return The data as a string.
     */
    @Override
    public String toString() {
        return data.toString();
    }
}