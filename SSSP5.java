import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SSSP5 {

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


    static class Graph {
        int V, E, src, des;
        int[] distance, parent, nextD;


        ArrayList <ArrayList <Node>> graph = new ArrayList <>(); //for dijkstra

        Graph(int v, int e) {
            V = v;
            E = e;
            //ArrayList<ArrayList<Node> > graph=new ArrayList <>();
            distance = new int[V];
            parent = new int[V];
            nextD = new int[V];

            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList <>());
            }
        }


        void addEdge(int idx, int a, int b, int c) {
            graph.get(a).add(new Node(b, c));

        }

        void dijkstra(int[] selfCost, int s, int d) { //O(ElogV)
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
                    if (distance[curr.vertex] + i.cost +selfCost[i.vertex]< distance[i.vertex]) {
                        distance[i.vertex] = distance[curr.vertex] + i.cost+selfCost[i.vertex];
                        q.add(new Node(i.vertex, distance[i.vertex]));
                        parent[i.vertex] = curr.vertex;
                        nextD[curr.vertex] = i.vertex;
                    }
                }


            }
            distance[des]+=selfCost[src];
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




    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q,selfC,x;
        int selfCost[];
        int source, destination;
        v = scanner.nextInt();
        e = scanner.nextInt();
        selfCost=new int[v];


        Graph g1 = new Graph(v, e);
        for (int i = 0; i < v; i++)
        {   x=scanner.nextInt();
            selfC=scanner.nextInt();
            selfCost[x]=selfC;
        }


        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();
            g1.addEdge(i, p, q, w);



        }
        source = scanner.nextInt();
        destination = scanner.nextInt();
        for (int i = 0; i < v; i++)
        {
            System.out.println( selfCost[i]);
        }



            g1.dijkstra(selfCost,source, destination);


    }

}
