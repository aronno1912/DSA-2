import java.util.*;

public class sampleLab {

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
        int V, E, src;
        int[] distance,requiredEdges ,parent, nextD;
        Edge[] edges;

        ArrayList <ArrayList <Node>> graph = new ArrayList <>(); //for dijkstra

        Graph(int v, int e) {
            V = v;
            E = e;
            //ArrayList<ArrayList<Node> > graph=new ArrayList <>();
            distance = new int[V];
            parent = new int[V];
            requiredEdges=new int[V];
            nextD = new int[V];
            edges = new Edge[E];
            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList <>());
                //requiredEdges[i]=Integer.MAX_VALUE;
                requiredEdges[i]=0;
            }
        }


        void addEdge(int idx, int a, int b, int c) {
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a,c));
            edges[idx] = new Edge(a, b, c);
        }

        void dijkstra(int s) { //O(E+VlogV)
            src = s;

            for (int i = 0; i < V; i++) {
                distance[i] = Integer.MAX_VALUE;
                parent[i] = -1;
            }
            distance[src] = 0;
            requiredEdges[src]=0;
            PriorityQueue <Node> q = new PriorityQueue <>();
            q.add(new Node(src, 0)); //assign source cost from source is zero
            while (!q.isEmpty()) {
                Node curr = q.poll();
                for (Node i : graph.get(curr.vertex)) {
                    if (distance[curr.vertex] + i.cost < distance[i.vertex]) {
                        distance[i.vertex] = distance[curr.vertex] + i.cost;
                        q.add(new Node(i.vertex, distance[i.vertex]));
                        parent[i.vertex] = curr.vertex;
//                        if(requiredEdges[curr.vertex]+1<requiredEdges[i.vertex])
//                            requiredEdges[i.vertex]=requiredEdges[curr.vertex]+1;
                        requiredEdges[i.vertex]=requiredEdges[curr.vertex]+1;

                        //dfs(i.vertex);

                    }
                    if((distance[curr.vertex] + i.cost==distance[i.vertex]&&requiredEdges[curr.vertex]+1<requiredEdges[i.vertex]))
                    {

                        { parent[i.vertex] = curr.vertex;
                        requiredEdges[i.vertex]=requiredEdges[curr.vertex]+1;}

                    }
                }




            }

            //requiredEdges[des]=requiredEdges[des]+1;

            for(int i=0;i<V;i++)
                System.out.println(requiredEdges[i]);
            //StringBuilder shortestPath = new StringBuilder();
//            int j = des;
//            while (j != src) {
//                shortestPath.append(String.valueOf(j));
//                shortestPath.append(">-");
//
//                j = parent[j];
//            }
//            shortestPath.append(src);
//            String rev = new StringBuilder(shortestPath).reverse().toString();
//            System.out.println(rev);

        }

//        void BellmanFord(int s, int d) {   //O(VE)
//            src = s;
//            //des = d;
//            int dist[] = new int[V];
//            int parentB[] = new int[V];
//            StringBuilder sp = new StringBuilder();
//            for (int i = 0; i < V; i++) {
//                dist[i] = Integer.MAX_VALUE;
//                parentB[i] = -1;
//            }
//            if (hasNegCycle(dist, parentB, src, des) == true)
//                System.out.println("The graph contains a negative cycle");
//            else {
//                System.out.println("The graph does not contain a negative cycle ");
//                System.out.println("Shortest path cost: " + dist[des]);
//                int j = des;
//                while (j != src) {
//                    sp.append(j);
//                    sp.append(">-");
//                    j = parentB[j];
//                }
//                sp.append(src);
//                String rev = new StringBuilder(sp).reverse().toString();
//                System.out.println(rev);
//            }
//        }

        boolean hasNegCycle(int dist[], int parentB[], int src, int des) {

//            int dist[]=new int[V];
//            int parentB[]=new int[V];
//            for (int i = 0; i < V; i++) {
//                dist[i] = Integer.MAX_VALUE;
//                parentB[i] = -1;
//            }
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


        void dfsHelp(int v, boolean []vis)
        {
            vis[v]=true;
            System.out.println(" "+v+" ");
            for(Node i: graph.get(v))
            {
                if(vis[i.vertex]!=true)
                {

                    dfsHelp(i.vertex,vis);
                }
            }
        }
        void dfs(int w)
        {
            boolean vis[]=new boolean[V];
            dfsHelp(w,vis);

        }
        void bfs(int w)
        {
            boolean vis[]=new boolean[V];
            Queue <Integer> q=new LinkedList <>();
            vis[w]=true;
            q.add(w);
            while(!q.isEmpty())
            {
                int p=q.poll();
                System.out.print(p+" ");
                for(Node i: graph.get(p))
                {
                    if(!vis[i.vertex])
                    {
                        vis[i.vertex]=true;
                        q.add(i.vertex);
                    }
                }
            }
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q;
        int source;
        v = scanner.nextInt();
        e = scanner.nextInt();
        int indicator = 0; //0 for dijkstra,1 for bellman-ford

        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();
            g1.addEdge(i, p, q, w);
            if (w < 0)
                indicator = 1;


        }
        source = scanner.nextInt();


            g1.dijkstra(source);

        //g1.bfs(source);
    }

}
