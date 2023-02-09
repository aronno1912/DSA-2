import java.util.*;

public class PrimKruskal {

    static class nodeCost {
        int node;
        double cost;

        nodeCost(int n, double c) {
            node = n;
            cost = c;
        }
    }

    static class Pair implements Comparable <Pair> { //src vertex
        int vertex;
        double cost;

        Pair(int v, double w) {
            vertex = v;
            cost = w;
        }


        @Override
        public int compareTo(Pair that) {
            return (int) (this.cost - that.cost);
        }
    }

    static class Graph {
        private int V;
        //private List<List <nodeCost>>graph;
        //private LinkedList<LinkedList<Integer>>grp[];
        private LinkedList <nodeCost> graph[];
        private int[] parent;
        String resultSet = new String(" ");

        Graph(int v) {
            V = v;


            graph = new LinkedList[V];
            parent = new int[V];
            for (int i = 0; i < V; i++) {
                graph[i] = new LinkedList <>();

            }

        }

        void addEdge(int u, int v, double wt) {
            graph[u].add(new nodeCost(v, wt));
            graph[v].add(new nodeCost(u, wt));

        }
//        void show()
//        {
//            System.out.println(graph[0]);
//        }

        double primMST() {
            boolean vis[] = new boolean[V];
            for (int i = 0; i < V; i++)
                vis[i] = false;
            PriorityQueue <Pair> q = new PriorityQueue <>();
            q.add(new Pair(1, 0)); //add any vertex
            double totalCost = 0.0;
            while (q.size() != 0) {
                Pair current = q.remove();

                int currentVertex = current.vertex;
                if (vis[currentVertex] != true) {
                    vis[currentVertex] = true;
                    totalCost += current.cost;
                    //resultSet.concat(String.valueOf(currentVertex));


                    for (nodeCost j : graph[currentVertex]) {
                        int vertex = j.node;
                        double weight = j.cost;
                        if (vis[vertex] == false) {
                            q.add(new Pair(vertex, weight));
                            parent[vertex] = currentVertex;
                        }
                    }

                }

            }

            return totalCost;
        }

        void showPrimMST() {
            for (int i = 1; i < V; i++)
                System.out.println(parent[i] + " "
                        + "-"
                        + " " + i);
        }

    }

    public static void main(String[] args) {
        Graph g1 = new Graph(6);
        g1.addEdge(0, 1, 1);
        g1.addEdge(1, 3, 5);
        g1.addEdge(3, 0, 3);
        g1.addEdge(3, 4, 1);
        g1.addEdge(1, 4, 1);
        g1.addEdge(1, 2, 6);
        g1.addEdge(5, 2, 2);
        g1.addEdge(2, 4, 4);
        g1.addEdge(5, 4, 4);


        System.out.println(g1.primMST());
        g1.showPrimMST();

    }
}
