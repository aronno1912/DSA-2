import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class onlineMST {

    static class Edge implements Comparable <Edge> {
        int src;
        int des;
        double cost;


        Edge(int d, double c) {
            des = d;

            cost = c;
        }

        public Edge(int u, int v, double wt) {
            src = u;
            des = v;
            cost = wt;


        }

        @Override
        public int compareTo(Edge that) {
            return (int) (this.cost - that.cost);
        }
    }





    static class Graph {
        private int V;
        private int edges;

        private Edge[] graphKruskal;
        private LinkedList <Integer> adj[];
        String resultSet=null;

        Graph(int v, int e) {
            V = v;
            edges = e;



            adj=new LinkedList[edges];
            graphKruskal = new Edge[edges];
            for (int i = 0; i < edges; i++) {

                adj[i]=new LinkedList <>();

            }

        }

        void addEdge(int u, int v, double wt) {
//            graphPrim[u].add(new Edge(u, v, wt));
//            graphPrim[v].add(new Edge(v, u, wt));
//            //node1 a = new node1(v, wt);
//            node1 b = new node1(u, wt);
//            graphPrim[u].addLast(a);
//            graphPrim[v].addLast(b);
            adj[u].add(v);
            adj[v].add(u);
        }





        void addEdgeK(int idx, int u, int v, double wt) {

            graphKruskal[idx] = new Edge(u, v, wt); //idx for array indexing
        }
        void show()
        {
            System.out.println(resultSet);
        }
void dfsHelp(int v,boolean []vis)
{
    vis[v]=true;
    //System.out.println(" "+v+" ");
    for(int w:adj[v])
    {
        if(vis[w]!=true)
        {

            dfsHelp(w,vis);
        }
    }
}
        void dfs(int w)
        {
            boolean vis[]=new boolean[V];
            dfsHelp(w,vis);

        }

        int findParentK(int v, int[] parentK) {
            if (parentK[v] == v)
                return v;
            return findParentK(parentK[v], parentK);
        }
        boolean isConnected()
        {   boolean visitedD[]=new boolean[V];
            for(int i=0;i<V;i++)
                visitedD[i]=false;
            dfsHelp(0, visitedD);

            // If set of reachable vertices includes all,
            // return true.
            for (int i=0; i<V; i++)
                if (visitedD[i] == false)
                    return false;

            return true;
        }

        void reverseDlt() {
            Arrays.sort(graphKruskal, Edge::compareTo);
            int mstCost = 0;
            for (int i = edges - 1; i >= 0; i--) {
                int u = graphKruskal[i].src;
                int v = graphKruskal[i].des;

                // Remove edge from undirected graph

                adj[u].remove("v");

                adj[v].remove("u");
                if (isConnected() == false) {
                    adj[u].add(v);
                    adj[v].add(u);

                }
                //System.out.println("("+u+","+v+")");
//                resultSet.concat("(");
//                resultSet.concat(String.valueOf(u));
//                resultSet.concat(",");
//                resultSet.concat(String.valueOf(v));

                mstCost += graphKruskal[i].cost;
            }

        }

        void KruskalMST() {
            Arrays.sort(graphKruskal, Edge::compareTo);
            Edge[] output = new Edge[V - 1];
            int count = 0;
            int i = 0;
            double totalC = 0.0;
            int[] parentK = new int[V];
            for (int j = 0; j < V; j++)
                parentK[j] = j;

            while (count != (V - 1)) {
                Edge currEdge = graphKruskal[i]; //i=0 is least weight

                int srcParent = findParentK(currEdge.src, parentK);
                int desParent = findParentK(currEdge.des, parentK);
                //check if we can add this edge or not
                if (srcParent != desParent) {
                    output[count] = currEdge;
                    count++;
                    totalC += currEdge.cost;
                    parentK[srcParent] = desParent;
                }
                i++;

            }
            //System.out.println("for Kruskal MST ");
            System.out.println(totalC);
            for (int j = 0; j < (V - 1); j++) {
                System.out.println("("+output[j].src + " , " + output[j].des+")");
            }


        }

    }

    public static void main(String[] args) throws IOException {




        Scanner scanner = new Scanner(System.in);
        int v, e, p, q;
        double w;
        v = scanner.nextInt();
        e = scanner.nextInt();

        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextDouble();
            g1.addEdge(p, q, w);
            g1.addEdgeK(i, p, q, w);

        }
        //g1.primMST();
        g1.reverseDlt();
        g1.KruskalMST();

    }
}
