import java.util.List;

/**
 * Abstract base class for graph traversal algorithms.
 * @param <V> The type of data stored in the graph vertices.
 */
public abstract class Search<V> {
    protected final WeightedGraph<V> graph;
    protected final Vertex<V> start;

    /**
     * Initializes the search algorithm with a graph and a starting vertex.
     * @param graph The graph to traverse.
     * @param start The starting vertex for the search.
     */
    public Search(WeightedGraph<V> graph, Vertex<V> start) {
        this.graph = graph;
        this.start = start;
    }

    /**
     * Finds a path from the start vertex to the specified destination.
     * @param destination The target vertex.
     * @return A list of vertices representing the path, or an empty list if no path exists.
     */
    public abstract List<Vertex<V>> pathTo(Vertex<V> destination);
}