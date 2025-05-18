import java.util.*;

/**
 * Implements Dijkstra's algorithm for finding the shortest path in terms of edge weights.
 * @param <V> The type of data stored in the graph vertices.
 */
public class DijkstraSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Double> distTo = new HashMap<>();
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final PriorityQueue<Vertex<V>> pq;

    /**
     * Initializes Dijkstra's algorithm with the graph and starting vertex.
     * @param graph The graph to search.
     * @param start The starting vertex.
     */
    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        initializeDistances();
        pq.add(start);
        computeShortestPaths();
    }

    /**
     * Initializes all distances to infinity except the start vertex.
     */
    private void initializeDistances() {
        for (Vertex<V> v : graph.getMap().keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
    }

    /**
     * Computes shortest paths using a priority queue.
     */
    private void computeShortestPaths() {
        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                relax(current, neighbor);
            }
        }
    }

    /**
     * Relaxes an edge, updating the shortest path if a shorter path is found.
     * @param from The source vertex.
     * @param to The destination vertex.
     */
    private void relax(Vertex<V> from, Vertex<V> to) {
        double weight = from.getAdjacentVertices().get(to);
        double newDist = distTo.get(from) + weight;
        if (newDist < distTo.get(to)) {
            distTo.put(to, newDist);
            edgeTo.put(to, from);
            updatePriorityQueue(to);
        }
    }

    /**
     * Updates the priority queue when a vertex's distance changes.
     * @param vertex The vertex to update.
     */
    private void updatePriorityQueue(Vertex<V> vertex) {
        pq.remove(vertex);
        pq.add(vertex);
    }

    /**
     * Returns the path from the start vertex to the destination.
     * @param destination The target vertex.
     * @return List of vertices in the path.
     */
    @Override
    public List<Vertex<V>> pathTo(Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        if (distTo.get(destination) == Double.POSITIVE_INFINITY) return path;
        for (Vertex<V> v = destination; v != start; v = edgeTo.get(v)) {
            path.add(v);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    /**
     * Returns the total weight of the shortest path.
     * @param destination The target vertex.
     * @return Total weight, or infinity if no path exists.
     */
    public double getTotalWeight(Vertex<V> destination) {
        return distTo.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}