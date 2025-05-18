import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        bfs(start);
    }

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

    public int getPathLength(Vertex<V> destination) {
        List<Vertex<V>> path = pathTo(destination);
        return path.isEmpty() ? -1 : path.size() - 1;
    }
}