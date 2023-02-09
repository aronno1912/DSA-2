import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class APSPusingSSSP
{    final static int INFINITY=Integer.MAX_VALUE;

    static class Node implements Comparable <Node> {
        int vertex, cost;


        Node(int v, int w) {
            vertex = v;
            cost = w;

        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    static class Edge {
        int src;
        int des;
        int cost;


        public Edge(int u, int v, int wt) {
            src = u;
            des = v;
            cost = wt;


        }


    }

    static class Graph {
        int V, E, src, des;
        int[] distance, parent, nextD;
        Edge[] edges;

        ArrayList <ArrayList <Node>> graph = new ArrayList <>(); //for dijkstra

        Graph(int v, int e) {
            V = v;
            E = e;
            //ArrayList<ArrayList<Node> > graph=new ArrayList <>();
            distance = new int[V+1];
            parent = new int[V+1];
            nextD = new int[V+1];
            edges = new Edge[E+1];
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList <>());
            }
        }


        void addEdge(int idx, int a, int b, int c) {
            graph.get(a).add(new Node(b, c));
            edges[idx] = new Edge(a, b, c);
        }

        void dijkstra(int s, int d) { //O(ElogV)
            src = s;
            des = d;
            for (int i = 0; i <= V; i++) {
                distance[i] = Integer.MAX_VALUE;
                parent[i] = -1;
            }
            distance[src] = 0;
            PriorityQueue <Node> q = new PriorityQueue <>();
            q.add(new Node(src, 0)); //assign source cost from source is zero
            while (!q.isEmpty()) {
                Node curr = q.poll();
                for (Node i : graph.get(curr.vertex)) {
                    if (distance[curr.vertex] + i.cost < distance[i.vertex]) {
                        distance[i.vertex] = distance[curr.vertex] + i.cost;
                        q.add(new Node(i.vertex, distance[i.vertex]));
                        parent[i.vertex] = curr.vertex;
                        nextD[curr.vertex] = i.vertex;
                    }
                }


            }
            System.out.println("Shortest path cost: " + distance[des]);
            StringBuilder shortestPath = new StringBuilder();
            int j = des;
            while (j != src) {
                shortestPath.append(String.valueOf(j));
                shortestPath.append(">-");

                j = parent[j];
            }
            shortestPath.append(String.valueOf(src));
            String rev = new StringBuilder(shortestPath).reverse().toString();
            System.out.println(rev);

        }

        Integer BellmanFord(int s, int d) {   //O(VE)
            src = s;
            des = d;
            int dist[] = new int[V+1];
            int parentB[] = new int[V+1];
            dist[src] = 0;
            for (int count = 1; count < V; count++) {
                for (int e = 0; e < E; e++) {
                    int source = edges[e].src;
                    int destination = edges[e].des;
                    int weight = edges[e].cost;
                    if (dist[source] != Integer.MAX_VALUE && (dist[source] + weight < dist[destination])) {
                        dist[destination] = dist[source] + weight;
                        parentB[destination] = source;
                    }

                }
            }


            return dist[des];


        }

        boolean hasNegCycle(int dist[], int parentB[], int src, int des) {

//            int dist[]=new int[V];
//            int parentB[]=new int[V];
//            for (int i = 0; i < V; i++) {
//                dist[i] = Integer.MAX_VALUE;
//                parentB[i] = -1;
//            }

            for (int e = 0; e < E; e++) {
                int source = edges[e].src;
                int destination = edges[e].des;
                int weight = edges[e].cost;
                if (dist[source] != Integer.MAX_VALUE && (dist[source] + weight < dist[destination])) {
                    //System.out.println("cycle present");
                    return true;
                }

            }
            return false;

        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q;
        int source, destination;

        v = scanner.nextInt();
        e = scanner.nextInt();
        int indicator = 0; //0 for dijkstra,1 for bellman-ford
        int[][]mat=new int[v+1][v+1];
        String[][]toBePrinted=new String[v+1][v+1];

        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();
            g1.addEdge(i, p, q, w);
//            if (w < 0)
//                indicator = 1;


        }

       for(int i=1;i<=v;i++)
       {
           for(int j=1;j<=v;j++)
           {
               mat[i][j]=g1.BellmanFord(i,j);
           }
       }

        for(int i=1;i<=v;i++)
        {
            for(int j=1;j<=v;j++)
            {
                if(mat[i][j]==INFINITY)
                {
                    toBePrinted[i][j] = "INF";

                }
                else
                    toBePrinted[i][j]= String.valueOf(mat[i][j]);
                System.out.print(" "+toBePrinted[i][j]+" ");
            }
            System.out.println();
        }
    }

}
