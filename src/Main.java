import java.util.List;

/**
 * Demonstrates the usage of BFS and Dijkstra's algorithm on a weighted graph.
 */
public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        // Create vertices
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");

        // Add edges with weights
        graph.addEdge(a, b, 1.0);
        graph.addEdge(b, c, 2.0);
        graph.addEdge(a, c, 4.0);
        graph.addEdge(c, d, 1.0);

        // BFS example
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, a);
        List<Vertex<String>> bfsPath = bfs.pathTo(d);
        printBFSResults(bfs, bfsPath);

        // Dijkstra example
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, a);
        List<Vertex<String>> dijkstraPath = dijkstra.pathTo(d);
        printDijkstraResults(dijkstra, dijkstraPath);
    }

    /**
     * Prints the results of the BFS search.
     * @param bfs The BFS instance.
     * @param path The path found by BFS.
     */
    private static void printBFSResults(BreadthFirstSearch<String> bfs, List<Vertex<String>> path) {
        System.out.println("=== BFS Results ===");
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path: " + path);
            System.out.println("Edge count: " + bfs.getPathLength(path.get(path.size() - 1)));
        }
    }

    /**
     * Prints the results of Dijkstra's algorithm.
     * @param dijkstra The Dijkstra instance.
     * @param path The path found by Dijkstra.
     */
    private static void printDijkstraResults(DijkstraSearch<String> dijkstra, List<Vertex<String>> path) {
        System.out.println("\n=== Dijkstra Results ===");
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path: " + path);
            System.out.println("Total weight: " + dijkstra.getTotalWeight(path.get(path.size() - 1)));
            printDetailedPath(path);
        }
    }

    /**
     * Prints the detailed path with edge weights.
     * @param path The path to display.
     */
    private static void printDetailedPath(List<Vertex<String>> path) {
        System.out.print("Detailed path: ");
        double totalWeight = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex<String> current = path.get(i);
            Vertex<String> next = path.get(i + 1);
            double weight = current.getAdjacentVertices().get(next);
            totalWeight += weight;
            System.out.printf("%s --(%.1f)--> ", current, weight);
        }
        System.out.println(path.get(path.size() - 1));
        System.out.println("Validation: Total weight = " + totalWeight);
    }
}