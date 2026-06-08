import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}

public class CaseStudyThree {

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    public static void main(String[] args) {

        int V = 6;

        Edge[] edges = {
            new Edge(0,1,4),
            new Edge(0,2,3),
            new Edge(1,2,2),
            new Edge(1,3,5),
            new Edge(2,3,7),
            new Edge(2,4,8),
            new Edge(3,4,6),
            new Edge(3,5,9),
            new Edge(4,5,1)
        };

        Arrays.sort(edges);

        parent = new int[V];
        for(int i=0;i<V;i++)
            parent[i]=i;

        int totalCost = 0;

        System.out.println("Selected Roads:");

        for(Edge e : edges){
            if(find(e.src)!=find(e.dest)){
                union(e.src,e.dest);
                totalCost += e.weight;

                System.out.println(
                    (char)('A'+e.src) + " - " +
                    (char)('A'+e.dest) +
                    " : " + e.weight);
            }
        }

        System.out.println("\nTotal Construction Cost = " + totalCost);
    }
}