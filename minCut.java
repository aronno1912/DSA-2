import java.util.*;


public class minCut {
    final static int INFINITY=Integer.MAX_VALUE;

    static class Edge
    {
        int source;
        int destination;
        int weight;

        public Edge(int s, int d, int w) {
            source = s;
            destination = d;
            weight = w;
        }
    }

    static class Graph
    {

        int V,E;
        LinkedList <Edge>[] adj;
        int gp[][];

        Graph(int vertices) {
            V= vertices;
            adj = new LinkedList[vertices];
            gp=new int[vertices][vertices];

            for (int i = 0; i <V ; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            adj[source].addFirst(e);
            gp[source][destination]=weight;
        }


        void maxFlowEdmondsKarp(int source,int tank)

        {
            int maxFlow=0;
            List<List <Integer>> augmentedPaths = new ArrayList <>();
            int ResidualGraph[][] = new int[V][V];  //capacity -flow that means residual capacity
            int parent[]=new int[V];
            for (int u = 0; u < V; u++){
                for(Edge e : adj[u])
                {
                    ResidualGraph[u][e.destination] = e.weight;
                }
            }

            //finding how many ways we can reach from s to t
            while (bfs(ResidualGraph, source, tank, parent)) {
                List<Integer> augmentedPath = new ArrayList<>();
                int pathFlow = INFINITY;

                //find minimum residual capacity in augmented path
                for (int v = tank; v != source; v = parent[v]) {
                    augmentedPath.add(v);
                    int u = parent[v];
                    pathFlow = Math.min(pathFlow, ResidualGraph[u][v]);

                }


                for (int v = tank; v != source; v = parent[v]) {
                    int u = parent[v];
                    ResidualGraph[u][v] -= pathFlow; //for main graph
                    ResidualGraph[v][u] += pathFlow; //for reverse graph
                }


                maxFlow += pathFlow;
            }

            boolean[]visited=new boolean[V];
            dfsHelp(source,ResidualGraph,visited);
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (gp[i][j] > 0 && visited[i] && !visited[j]) {
                        System.out.println(i + " - " + j);
                    }
                }
            }

        }

        private boolean bfs(int[][] residualGraph, int source, int tank, int[] parent) {

            boolean vis[]=new boolean[V];
            for (int i = 0; i < V; i++)
                vis[i] = false;
            Queue <Integer> q = new LinkedList<>();
            q.add(source);
            vis[source]=true;
            parent[source] = -1;

            while(!q.isEmpty())
            {
                int u =q.poll();
                for (Edge x : adj[u]) {
                    int v = x.destination;
                    if (vis[v] == false && residualGraph[u][v] > 0) {
                        if (v == tank) {
                            parent[v] = u;
                            return true;
                        }
                        q.add(v);
                        parent[v] = u;
                        vis[v] = true;
                    }
                }
            }

            return false;
        }


        void dfsHelp(int v,int[][]ResGraph,boolean []vis)
        {

            vis[v]=true;

            for (int i = 0; i < ResGraph.length; i++) {
                if (ResGraph[v][i] > 0 && !vis[i]) {
                    dfsHelp( i,ResGraph, vis);
                }
            }
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q;
        int source, tank;
        v = scanner.nextInt();
        e = scanner.nextInt();
        source=0;
        tank=v-1;

        Graph g1 = new Graph(v);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();
            g1.addEdge( p, q, w);



        }
        int ans;
        g1.maxFlowEdmondsKarp(source,tank);



    }

}

