import java.util.*;

public class SwiggyRouteOptimizer {
    static class Edge {
        int target;
        int weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        int distance;
        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // 1. Dijkstra's Algorithm implementation
    public static void dijkstra(List<List<Edge>> graph, int source, int vertices) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }
        printRouteMetrics("Dijkstra", source, distances, parent);
    }

    // 2. Bellman-Ford Algorithm implementation
    public static void bellmanFord(List<List<Edge>> graph, int source, int vertices) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);

        // Relax edges V-1 times
        for (int i = 1; i < vertices; i++) {
            for (int u = 0; u < vertices; u++) {
                for (Edge edge : graph.get(u)) {
                    int v = edge.target;
                    int weight = edge.weight;
                    if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                        parent[v] = u;
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (int u = 0; u < vertices; u++) {
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    System.out.println("⚠️ CRITICAL ERROR: Live traffic data corruption! Negative-weight loop detected.");
                    return;
                }
            }
        }
        printRouteMetrics("Bellman-Ford", source, distances, parent);
    }

    private static void printRouteMetrics(String algo, int source, int[] distances, int[] parent) {
        System.out.println("\n🚀 [" + algo + " Engine Search Complete]");
        for (int i = 0; i < distances.length; i++) {
            if (i == source) continue;
            System.out.print("DarkStore -> Hub_" + i + " : " + distances[i] + " mins | Optimal Path: ");
            printPath(i, parent);
            System.out.println();
        }
    }

    private static void printPath(int current, int[] parent) {
        if (current == -1) return;
        printPath(parent[current], parent);
        System.out.print((parent[current] == -1 ? "" : " ➔ ") + "Junction_" + current);
    }

    public static void main(String[] args) {
        int vertices = 4; // Mock layout intersections
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) graph.add(new ArrayList<>());

        // Adding simulated live street delivery paths (Source, Destination, Weight in minutes)
        graph.get(0).add(new Edge(1, 4));  // DarkStore -> Junction 1
        graph.get(0).add(new Edge(2, 7));  // DarkStore -> Junction 2
        graph.get(1).add(new Edge(2, 2));  // Junction 1 -> Junction 2
        graph.get(1).add(new Edge(3, 10)); // Junction 1 -> Junction 3
        graph.get(2).add(new Edge(3, 3));  // Junction 2 -> Junction 3

        System.out.println("Initializing Swiggy Instamart Delivery Route Optimizer...");
        dijkstra(graph, 0, vertices);
        bellmanFord(graph, 0, vertices);
    }
}