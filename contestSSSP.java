import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class contestSSSP {

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
            distance = new int[V];
            parent = new int[V];
            nextD = new int[V];
            edges = new Edge[E];
            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList <>());
            }
        }


        void addEdge(int idx, int a, int b, int c) {
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));

            //edges[idx] = new Edge(a, b, c);
        }

        void dijkstra(int s, int d) { //O(ElogV)
            src = s;
            des = d;
            for (int i = 0; i < V; i++) {
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
                        //nextD[curr.vertex] = i.vertex;
                    }
                }


            }
            for(int i=0;i<V;i++)
            System.out.println( distance[i]);


        }




    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q;
        int source, destination;
        v = scanner.nextInt();
        e = scanner.nextInt();
        int indicator = 0; //0 for dijkstra,1 for bellman-ford

        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();

            g1.addEdge(i, p, q, w);



        }
        source = scanner.nextInt();
        destination = scanner.nextInt();

            g1.dijkstra(source, destination);


    }

}