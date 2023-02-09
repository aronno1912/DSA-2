import java.io.*;
import java.util.*;

public class destroyRoads {

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

    static class node1 {  //for Prim mst


        int des;
        double cost;

        node1(int a, double b) {
            des = a;
            cost = b;
        }
    }

    static class node {
        int vertex;
        int key;
    }

    static class comparator implements Comparator <node> {

        @Override
        public int compare(node a, node b) {
            return a.key - b.key;
        }
    }

    static class result {
        int parent;
        double cost;
    }


    static class Graph {
        private int V;
        private int edges;
        private LinkedList <node1> graphPrim[];
        private Edge[] graphKruskal;

        Graph(int v, int e) {
            V = v;
            edges = e;


            graphPrim = new LinkedList[V+1];

            graphKruskal = new Edge[edges];
            for (int i = 0; i <= V; i++) {
                graphPrim[i] = new LinkedList <>();

            }

        }

        void addEdge(int u, int v,  double wt) {
//            graphPrim[u].add(new Edge(u, v, wt));
//            graphPrim[v].add(new Edge(v, u, wt));
            node1 a = new node1(v, wt);
            node1 b = new node1(u, wt);
            graphPrim[u].add(a);
            graphPrim[v].add(b);


        }

        void addEdgeK(int idx, int u, int v,  double wt) {

            graphKruskal[idx] = new Edge(u, v, wt); //idx for array indexing
        }
//        void show()
//        {
//            System.out.println(graph[0]);
//        }

        void primMST() {
            boolean vis[] = new boolean[V+1];
            int[] parentPrim = new int[V+1];
            node[] ed = new node[V+1]; //for key comparison
            for (int i = 0; i <= V; i++) {

                ed[i] = new node();
                //parentPrim[i]=-1;
            }

            //q.add(new Edge(0, 0)); //add any vertex

            for (int i = 1; i <= V; i++) {
                vis[i] = false;
                ed[i].key = Integer.MAX_VALUE; //initialize with infinity
                ed[i].vertex = i;
                parentPrim[i] = -1;
            }

            vis[1] = true; //any vertex
            ed[1].key = 0;
            PriorityQueue <node> q = new PriorityQueue <>(new comparator());
            for (int i = 0; i <= V; i++)
                q.add(ed[i]);
            double totalCost = 0.0;
            while (q.size() != 0) {
                node current = q.peek();
                q.remove();
                vis[current.vertex] = true;
                totalCost += current.key;
                //int currentVertex = current.src;
//                if (vis[currentVertex] != true) {
//                    vis[currentVertex] = true;
//                    totalCost += current.cost;
////                    resultSet.concat(String.valueOf(currentVertex));
////                    resultSet.concat("-");
////                    resultSet.concat(String.valueOf(current.des));
////                    resultSet.concat("\r\n");
//                    System.out.println();
//                    System.out.print(currentVertex);
//                    System.out.print("-");
//                    System.out.print(current.des);


                for (node1 j : graphPrim[current.vertex]) {

                    if (vis[j.des] == false) {
                        //If the key value of the adjacent vertex is
                        // more than the extracted key
                        // update the key value of adjacent vertex
                        // to update first remove and add the updated vertex
                        if (ed[j.des].key > j.cost) {
                            q.remove(ed[j.des]);
                            ed[j.des].key = (int) j.cost;
                            //totalCost+=j.cost;
                            q.add(ed[j.des]);
                            parentPrim[j.des] = current.vertex;

                        }

                    }
                }

            }
            System.out.println("for Prim ");
            System.out.println(totalCost);
            for (int i = 2; i <=V; i++) {
                System.out.println(parentPrim[i] + " "
                        + "-"
                        + " " + i);

            }


        }


        int findParentK(int v, int[] parentK) {
            if (parentK[v] == v)
                return v;
            return findParentK(parentK[v], parentK);
        }

        void KruskalMST(int a,int b,double c) {
            Edge inIt=new Edge(a,b,c);
            Arrays.sort(graphKruskal, Edge::compareTo);
            Edge[] output = new Edge[V ];
            int count = 0;
            int i = 0;
            double totalC = 0.0;
            int[] parentK = new int[V+1];
            for (int j = 1; j <= V; j++)
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
            System.out.println("for Kruskal MST ");
            System.out.println(totalC);
            for (int j = 0; j <V-1 ; j++) {
                System.out.println(output[j].src + " - " + output[j].des);
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
        int v, e, p, q;
        double w;
        v = scanner.nextInt();
        e = scanner.nextInt();

        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextDouble();
            //if(w==2 || w==3)
           // g1.addEdge(p, q, w); //prim for female
            //if(w==1 || w==3)
            g1.addEdgeK(i, p, q, w); //kruskal for male

        }
        int a,b;
        double c;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c=scanner.nextDouble();
        g1.primMST();

        g1.KruskalMST(a,b,c);

    }
}

