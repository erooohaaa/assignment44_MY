import java.util.*;

/**
 * Represents an undirected weighted graph using adjacency lists.
 * @param <V> The type of data stored in the graph vertices.
 */
public class WeightedGraph<V> {
    protected final Map<Vertex<V>, List<Vertex<V>>> adjacencyMap;

    /**
     * Initializes an empty weighted graph.
     */
    public WeightedGraph() {
        this.adjacencyMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph if it does not already exist.
     * @param vertex The vertex to add.
     */
    public void addVertex(Vertex<V> vertex) {
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Adds an undirected edge between two vertices with a specified weight.
     * @param source The source vertex.
     * @param dest The destination vertex.
     * @param weight The weight of the edge.
     */
    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        addVertex(source);
        addVertex(dest);
        source.addAdjacentVertex(dest, weight);
        dest.addAdjacentVertex(source, weight);
        adjacencyMap.get(source).add(dest);
        adjacencyMap.get(dest).add(source);
    }

    /**
     * Returns the list of adjacent vertices for a given vertex.
     * @param vertex The vertex to query.
     * @return List of adjacent vertices.
     */
    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        return adjacencyMap.getOrDefault(vertex, Collections.emptyList());
    }

    /**
     * Returns the internal adjacency map (for advanced operations).
     * @return The adjacency map of the graph.
     */
    public Map<Vertex<V>, List<Vertex<V>>> getMap() {
        return adjacencyMap;
    }
}