import java.util.*;

/**
 * Implements Breadth-First Search (BFS) for finding the shortest path in terms of edge count.
 * @param <V> The type of data stored in the graph vertices.
 */
public class BreadthFirstSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();

    /**
     * Initializes BFS with the graph and starting vertex.
     * @param graph The graph to search.
     * @param start The starting vertex.
     */
    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        bfs(start);
    }

    /**
     * Performs BFS traversal starting from the specified vertex.
     * @param start The starting vertex.
     */
    private void bfs(Vertex<V> start) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Returns the path from the start vertex to the destination.
     * @param destination The target vertex.
     * @return List of vertices in the path.
     */
    @Override
    public List<Vertex<V>> pathTo(Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        if (!edgeTo.containsKey(destination)) return path;
        for (Vertex<V> v = destination; v != start; v = edgeTo.get(v)) {
            path.add(v);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    /**
     * Calculates the number of edges in the path.
     * @param destination The target vertex.
     * @return Path length in edges, or -1 if no path exists.
     */
    public int getPathLength(Vertex<V> destination) {
        List<Vertex<V>> path = pathTo(destination);
        return path.isEmpty() ? -1 : path.size() - 1;
    }
}