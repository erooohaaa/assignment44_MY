import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private PriorityQueue<Vertex<V>> pq;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        super(graph, start);
        for (Vertex<V> v : graph.getMap().keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
        pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        pq.add(start);
        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                relax(current, neighbor);
            }
        }
    }

    private void relax(Vertex<V> from, Vertex<V> to) {
        double weight = from.getAdjacentVertices().get(to);
        if (distTo.get(to) > distTo.get(from) + weight) {
            distTo.put(to, distTo.get(from) + weight);
            edgeTo.put(to, from);
            if (pq.contains(to)) pq.remove(to);
            pq.add(to);
        }
    }

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

    public double getTotalWeight(Vertex<V> destination) {
        return distTo.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}