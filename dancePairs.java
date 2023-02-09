import java.util.*;

public class dancePairs{
    final static int INFINITY=Integer.MAX_VALUE;
    static int ind,ind1,ind2;
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

        //        String[] team;
//        int[] teamWins, teamLoses, teamRemainingMatches;
//        int[][] against;
//        int totalTeams;
        int V,E;
        LinkedList <Edge>[] adj;

        Graph(int vertices) {
            V= vertices;
            adj = new LinkedList[vertices];

            for (int i = 0; i <V ; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            adj[source].addFirst(e);
        }

//        public boolean easyElimination(int x) {
//            boolean ans = false;
//            for (int i = 0; i < totalTeams; i++) {
//                if (x != i && (teamWins[x] + teamRemainingMatches[x] < teamWins[i]))
//                    ans = true; //that means team x is eliminated
//            }
//            return ans;
//
//        }
            private void printAugmentedPaths(List<List<Integer>> augmentedPaths) {
                System.out.println("Augmented paths");
                augmentedPaths.forEach(path -> {
                    path.forEach(i -> System.out.print(i + " "));
                    System.out.println();
                });
            }

        int maxFlowEdmondsKarp(int source,int tank)

        {    int temp=-1;
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
                augmentedPath.add(source);
                Collections.reverse(augmentedPath);
                augmentedPaths.add(augmentedPath);
                System.out.println(augmentedPath);
                System.out.println("for this path the flow is "+pathFlow);
                if (pathFlow>temp)
                {
                    ind=augmentedPath.get(1); //first vertex after s in the path that indicates the match between which two

                }
                temp=pathFlow;
                maxFlow += pathFlow;
            }


           // printAugmentedPaths(augmentedPaths);

            return maxFlow;
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
    }

    public static void main(String[] args) {


        int[][] graph;
        int totalTeams;

        int m,n,girls,boys,pairs;

        String s;

        Scanner scn=new Scanner(System.in);
        m=scn.nextInt();
        n=scn.nextInt();
        girls=scn.nextInt();
        boys=scn.nextInt();
        int total_nodes=girls+boys+2;
        pairs=scn.nextInt();

        graph=new int[girls][boys]; //edge weight of from a team vertex to t in the newly formed graph

        for(int i=0;i<girls;i++)
        {
            for(int j=0;j<boys;j++)
            {
                graph[i][j]=0;
            }
        }

        Graph g=new Graph(total_nodes);

        for(int i=0;i<pairs;i++)
        {
           int x=scn.nextInt();
           int y=scn.nextInt();
           graph[x][y]=m;
           g.addEdge(0,x+1,n);
           g.addEdge(girls+y+1,total_nodes-1,n);
           g.addEdge(x+1,girls+y+1,m);

        }




        System.out.println(g.maxFlowEdmondsKarp(0,total_nodes-1));


    }
}


