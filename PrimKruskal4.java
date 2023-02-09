import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PrimKruskal4 {

    static class Edge  implements Comparable<Edge> {
        int src;
        int des;
        double cost;

        Edge(int s, int d, double c) {
            src = s;
            des = d;
            cost = c;
        }

        Edge(int d, double c) {
            des = d;

            cost = c;
        }

        @Override
        public int compareTo(Edge that) {
            return (int) (this.cost - that.cost);
        }
    }

//    static class Pair implements Comparable <Pair> {
//        int vertex;
//        double cost;
//
//        Pair(int v, double w) {
//            vertex = v;
//            cost = w;
//        }


//        @Override
//        public int compareTo(Pair that) {
//            return (int) (this.cost - that.cost);
//        }
//    }

    static class Graph {
        private int V;
        private int edges;
        //private List<List <nodeCost>>graph;
        //private LinkedList<LinkedList<Integer>>grp[];
        private LinkedList <Edge> graphPrim[];
        String resultSet = new String(" ");

        private Edge[] graphKruskal;

        Graph(int v,int e) {
            V = v;
            edges=e;


            graphPrim = new LinkedList[V];

            graphKruskal= new Edge[edges];
            for (int i = 0; i < V; i++) {
                graphPrim[i] = new LinkedList <>();
                //graphKruskal[i]=new Edge();

            }

        }

        void addEdge(int u, int v, double wt) {
            graphPrim[u].add(new Edge(u, v, wt));
            graphPrim[v].add(new Edge(v, u, wt));


        }
        void addEdgeK(int idx,int u,int v,double wt)
        {

            graphKruskal[idx]=new Edge(u,v,wt); //idx for array indexing
        }
//        void show()
//        {
//            System.out.println(graph[0]);
//        }

        void primMST() {
            boolean vis[] = new boolean[V];
            int[] parentPrim=new int[V];
            for (int i = 0; i < V; i++)
                vis[i] = false;
            PriorityQueue <Edge> q = new PriorityQueue <>();
            Edge random= graphPrim[0].get(0);

            q.add(new Edge( 1, 0)); //add any vertex
            double totalCost = 0.0;
            while (q.size() != 0) {
                Edge current = q.remove();

                int currentVertex = current.src;
                if (vis[currentVertex] != true) {
                    vis[currentVertex] = true;
                    totalCost += current.cost;
                    //resultSet.concat(String.valueOf(currentVertex));
                    //parentPrim[current.des] = currentVertex;



                    for (Edge j : graphPrim[currentVertex]) {
                        int vertex = j.des;
                        double weight = j.cost;
                        //parentPrim[vertex] = currentVertex;
                        if (vis[vertex] == false) {
                            q.add(new Edge(vertex, weight));
                            parentPrim[vertex] = currentVertex;

                        }
                    }
                    //parentPrim[current.des] = currentVertex;

                }

            }

            System.out.println("for Prim's MST ");
            System.out.println(totalCost);
            for (int i = 1; i < V; i++)
                System.out.println(parentPrim[i] + " "
                        + "-"
                        + " " + i);
        }


        int findParentK(int v,int[]parentK)
        {
            if(parentK[v]==v)
                return v;
            return findParentK(parentK[v],parentK);
        }
        void KruskalMST()
        {
            Arrays.sort(graphKruskal,Edge::compareTo);
            Edge[] output=new Edge[V-1];
            int count=0;
            int i=0;
            double totalC=0.0;
            int []parentK= new int[V];
            for(int j=0;j<V;j++)
                parentK[j]=j;

            while(count!=(V-1))
            {
                Edge currEdge=graphKruskal[i]; //i=0 is least weight

                int srcParent=findParentK(currEdge.src,parentK);
                int desParent=findParentK(currEdge.des,parentK);
                //check if we can add this edge or not
                if(srcParent!=desParent)
                {
                    output[count]=currEdge;
                    count++;
                    totalC+=currEdge.cost;
                    parentK[srcParent]=desParent;
                }
                i++;

            }
            System.out.println("for Kruskal MST ");
            System.out.println(totalC);
            for(int j=0;j<(V-1);j++)
            {
                System.out.println(output[j].src+" - "+output[j].des);
            }




        }

    }

    public static void main(String[] args) throws IOException {


//        String contents = null;
//        try {
//            contents = new String(Files.readAllBytes(Paths.get("src\\in.txt")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String[] values = contents.split("\\s+");
//        int vertices= Integer.parseInt(values[0]);
//        int edges= Integer.parseInt(values[1]);
//        int i=2;
//        int j=0; //for array indexing
//        Graph g1 = new Graph(vertices,edges);
//        while(i<3*edges)
//        {
//            g1.addEdge(Integer.parseInt(values[i]),Integer.parseInt(values[i+1]),Double.parseDouble(values[i+2]));
//            g1.addEdgeK(j,Integer.parseInt(values[i]),Integer.parseInt(values[i+1]),Double.parseDouble(values[i+2]));
//            i=i+3;
//            j++;
//        }


        Scanner scanner = new Scanner(System.in);
        int v,e,p,q;
        double w;
        v=scanner.nextInt();
        e=scanner.nextInt();

        Graph g1 = new Graph(v,e);

        for(int i=0;i<e;i++)
        {
            p=scanner.nextInt();
            q=scanner.nextInt();
            w= scanner.nextDouble();
            g1.addEdge(p,q,w);
            g1.addEdgeK(i,p,q,w);

        }
        g1.primMST();

        g1.KruskalMST();

    }
}
