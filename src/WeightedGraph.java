import java.util.*;

public class WeightedGraph<V> {
    protected Map<Vertex<V>, List<Vertex<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        addVertex(source);
        addVertex(dest);
        source.addAdjacentVertex(dest, weight);
        dest.addAdjacentVertex(source, weight);
        map.get(source).add(dest);
        map.get(dest).add(source);
    }

    public List<Vertex<V>> getAdjacentVertices(Vertex<V> vertex) {
        return map.getOrDefault(vertex, new ArrayList<>());
    }

    public Map<Vertex<V>, List<Vertex<V>>> getMap() {
        return map;
    }
}