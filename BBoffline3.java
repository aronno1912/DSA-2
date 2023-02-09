import java.util.*;

public class BBoffline3 {
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


        int maxFlowEdmondsKarp(int source,int tank)  //O(EV^2)

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
                if (pathFlow>temp)
                {
                    ind=augmentedPath.get(1); //first vertex after s in the path that indicates the match between which two

                }
                temp=pathFlow;
                maxFlow += pathFlow;
            }



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

        String[] team;
        int[] teamWins, teamLoses, teamRemainingMatches;
        int[][] against;
        int totalTeams;

        int v,w,l,r;

        String s;

        Scanner scn=new Scanner(System.in);
        v=scn.nextInt();
        int capacity[][]=new int[v][v]; //edge weight of from a team vertex to t in the newly formed graph

        against=new int[v][v];
        team=new String[v];
        teamWins=new int[v];
        teamLoses=new int[v];
        teamRemainingMatches=new int[v];
        for(int i=0;i<v;i++)
        {
            s=scn.next();
            team[i]=s;
            w=scn.nextInt();
            teamWins[i]=w;
            l=scn.nextInt();
            teamLoses[i]=l;
            r=scn.nextInt();
            teamRemainingMatches[i]=r;
            for(int j=0;j<v;j++)
            {
                int x=scn.nextInt();
                against[i][j]=x;
            }


        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                capacity[i][j] = teamWins[i] + teamRemainingMatches[i] - teamWins[j];

            }
        }
        //starting of making the graph for each team,where in that graph,that team is not present as vertex..basically making the
        //graph of the qs

        int nodes = ((v*(v-1))/2)+2; //total nodes for a graph of each team.. generalized equation
        for(int i=0;i<v;i++)
        {
            Graph g=new Graph(nodes);
            int teamIndex[]=new int[v];
            int currNode=( (v - 1) * (v - 2) / 2) + 1; // first node of team vertex...Generalized

            //forming edge from each team vertex to t
            for(int j = 0; j < v; j++){
                if(i == j)continue;
                teamIndex[j] = currNode;
                g.addEdge(currNode, nodes - 1, capacity[i][j]); //nodes -1 means tank
                currNode++;
            }
            //now forming edge from source to games
            currNode = 1; //first index of game
            int total = 0; //counting the track that how much flow is entering in the graph
            for(int j = 0; j < v; j++){
                if(i == j)continue;
                for(int k = j + 1; k < v; k++){
                    if(i == k)continue;
                    total += against[j][k];
                    g.addEdge(0, currNode, against[j][k]); //from s to each game edges
                    g.addEdge(currNode, teamIndex[j], INFINITY); //either j is winner or k
                    g.addEdge(currNode, teamIndex[k], INFINITY); //from each game to winner team edges
                    currNode++;
                }
            }
            // if the graph is not saturated,that means we don't get the equal flow of entering from source, at the tank,the
            //team is eliminated
            if(g.maxFlowEdmondsKarp(0, nodes - 1) != total){
                System.out.println(team[i] + "  is eliminated.");
                int y=teamWins[i]+teamRemainingMatches[i];


                /**Bonus part
                 *
                 */

                System.out.println("they can at most win "+teamWins[i]+"+"+teamRemainingMatches[i]+"="+y);
                //System.out.println(ind);
                List<Integer> gamePlayer1=new ArrayList();
                List<Integer> gamePlayer2=new ArrayList <>();

                //possible games
                for(int j = 0; j < v; j++){
                    if(i == j)continue;
                    for(int k = j + 1; k < v; k++){
                        if(i == k)continue;
                        gamePlayer1.add(j);
                        gamePlayer2.add(k);

                    }
                }
                ind1=gamePlayer1.get(ind-1); //make it one indexing
                ind2=gamePlayer2.get(ind-1);
//                for(int t=0;t<=ind;t++)
//                {
//                    if (t==i)continue;
//                    ind1=t;
//                    ind2=t+1;
//                }
                if(teamWins[ind1]>teamWins[i]+teamRemainingMatches[i])
                {
                    System.out.println(team[ind1]+" has won a total of "+teamWins[ind1]+" games");
                    System.out.println("They play each other "+against[ind1][ind1]+" times.");
                    System.out.println("So on average, each of the teams in this group wins "+teamWins[ind1]+"/"+1+"= "+teamWins[ind1]+" games.");
                }
                else  if(teamWins[ind2]>teamWins[i]+teamRemainingMatches[i])
                {
                    System.out.println(team[ind2]+" has won a total of "+teamWins[ind2]+" games");
                    System.out.println("They play each other"+against[ind2][ind2]+" times.");
                    System.out.println("So on average, each of the teams in this group wins "+teamWins[ind2]+"/"+1+"= "+teamWins[ind2]+" games.");
                }
                else
                {    int z=teamWins[ind1]+teamWins[ind2];
                    double av=(z+against[ind1][ind2])/2.0;
                    System.out.println(team[ind1]+" and "+team[ind2]+" have won a total of "+z+" games");
                    System.out.println("They play each other "+against[ind1][ind2]+" times.");
                    System.out.println("So on average, each of the teams in this group wins "+z+"/"+2+"= "+av+" games.");
                }

                System.out.println();
                /**end of bonus part
             *
             */
            }

        }


    }
}

