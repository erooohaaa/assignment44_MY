import java.util.*;
public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();


        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");


        graph.addEdge(a, b, 1.0);
        graph.addEdge(b, c, 2.0);
        graph.addEdge(a, c, 4.0);
        graph.addEdge(c, d, 1.0);

        // BFS
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, a);
        List<Vertex<String>> bfsPath = bfs.pathTo(d);

        System.out.println("=== BFS Results ===");
        if (bfsPath.isEmpty()) {
            System.out.println("No path found from A to D");
        } else {
            System.out.println("Path: " + bfsPath);
            System.out.println("Path length: " + bfs.getPathLength(d) + " edges");
        }

        // Dijkstra
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, a);
        List<Vertex<String>> dijkstraPath = dijkstra.pathTo(d);

        System.out.println("\n=== Dijkstra Results ===");
        if (dijkstraPath.isEmpty()) {
            System.out.println("No path found from A to D");
        } else {
            System.out.println("Path: " + dijkstraPath);
            System.out.println("Total weight: " + dijkstra.getTotalWeight(d));
            printDetailedPath(dijkstraPath);
        }
    }


    private static <V> void printDetailedPath(List<Vertex<V>> path) {
        if (path.size() < 2) return;

        System.out.print("Detailed path: " + path.get(0));
        double totalWeight = 0;

        for (int i = 1; i < path.size(); i++) {
            Vertex<V> prev = path.get(i-1);
            Vertex<V> current = path.get(i);
            double weight = prev.getAdjacentVertices().get(current);
            totalWeight += weight;
            System.out.print(" --(" + weight + ")--> " + current);
        }
        System.out.println("\nValidation: Total weight = " + totalWeight);
    }
}