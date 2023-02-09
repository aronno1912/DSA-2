import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SSSP4 {


    static class Edge {
        int src;
        int des;
        double cost;


        public Edge(int u, int v, double wt) {
            src = u;
            des = v;
            cost = wt;


        }


    }

    static class Graph {
        int V, E, src, des;
        int[] distance, parent, nextD;
        Edge[] edges;



        Graph(int v, int e) {
            V = v;
            E = e;
            //ArrayList<ArrayList<Node> > graph=new ArrayList <>();
            distance = new int[V];
            parent = new int[V];
            edges = new Edge[E];

        }


        void addEdge(int idx, int a, int b, double c) {
            //graph.get(a).add(new Node(b, c));
            edges[idx] = new Edge(a, b, c);
        }


        void BellmanFord(int s, int d) {   //O(VE)
            src = s;
            des = d;
            double dist[] = new double[V];
            int parentB[] = new int[V];
            StringBuilder sp = new StringBuilder();
            for (int i = 0; i < V; i++) {
                dist[i] = 0;
                parentB[i] = -1;
            }
            if (hasAnomaly(dist, parentB, src, des) == true)
                System.out.println("There is an anomaly");
            else {
                System.out.println("There is no anomaly");
                System.out.println("There are no anomalies: " + dist[des]);
                int j = des;
                while (j != src) {
                    sp.append(j);
                    sp.append(">-");
                    j = parentB[j];
                }
                sp.append(src);
                String rev = new StringBuilder(sp).reverse().toString();
                System.out.println(rev);
            }
        }

        boolean hasAnomaly(double dist[], int parentB[], int src, int des) {

//            int dist[]=new int[V];
//            int parentB[]=new int[V];
//            for (int i = 0; i < V; i++) {
//                dist[i] = Integer.MAX_VALUE;
//                parentB[i] = -1;
//            }
            dist[src] = 1;
            for (int count = 1; count < V; count++) {
                for (int e = 0; e < E; e++) {
                    int source = edges[e].src;
                    int destination = edges[e].des;
                    double weight = edges[e].cost;
                    if ( (dist[source] * weight > dist[destination])) {
                        dist[destination] = dist[source] * weight;
                        parentB[destination] = source;
                    }

                }
            }

            for (int e = 0; e < E; e++) {
                int source = edges[e].src;
                int destination = edges[e].des;
                double weight = edges[e].cost;
                if ((dist[source] * weight > dist[destination])) {
                    //System.out.println("cycle present");
                    return true;
                }

            }
            return false;

        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, p, q;
        double w;
        int source, destination;
        v = scanner.nextInt();
        e = scanner.nextInt();


        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextDouble();
            g1.addEdge(i, p, q, w);



        }
        source = scanner.nextInt();
        destination = scanner.nextInt();

        g1.BellmanFord(source, destination);
    }

}

