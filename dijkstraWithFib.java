//import java.util.ArrayList;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//
//public class dijkstraWithFib {
//
//    static class Node implements Comparable <Node> {
//        int vertex, cost;
//
//
//        Node(int v, int w) {
//            vertex = v;
//            cost = w;
//
//        }
//
//        @Override
//        public int compareTo(Node o) {
//            return cost - o.cost;
//        }
//    }
//
//    static class Edge {
//        int src;
//        int des;
//        int cost;
//
//
//        public Edge(int u, int v, int wt) {
//            src = u;
//            des = v;
//            cost = wt;
//
//
//        }
//
//
//    }
//
//    static class Graph {
//        int V, E, src, des;
//        int[] distance, parent, nextD,best;
//        Edge[] edges;
//        boolean inSP[];
//        Fib.heapElements<Integer> track[];
//
//        ArrayList <ArrayList <Node>> graph = new ArrayList <>(); //for dijkstra
//
//        Graph(int v, int e) {
//            V = v;
//            E = e;
//            //ArrayList<ArrayList<Node> > graph=new ArrayList <>();
//            distance = new int[V];
//            parent = new int[V];
//            nextD = new int[V];
//            best=new int[V];
//            inSP=new boolean[V];
//            track=new Fib.heapElements[V];
//            edges = new Edge[E];
//            for (int i = 0; i < V; i++) {
//                graph.add(new ArrayList <>());
//            }
//        }
//
//
//        void addEdge(int idx, int a, int b, int c) {
//
//            graph.get(a).add(new Node(b, c));
//            edges[idx] = new Edge(a, b, c);
//        }
//
//        void dijkstra(int s, int d) { //O(ElogV)
//            src = s;
//            des = d;
//            for (int i = 0; i < V; i++) {
//                distance[i] = Integer.MAX_VALUE;
//                parent[i] = -1;
//                best[i]=0;
//                inSP[i]=false;
//                track[i]=null;
//            }
//            //Fib.heapElements<Integer> track[]=new Fib.heapElements[V];
////            for(int i=0;i<=V;i++)
////            {
////                track[i]=new Fib.heapElements <Integer>(null,null);
////            }
//            distance[src] = 0;
//            best[src]=0;
//            //PriorityQueue <Node> q = new PriorityQueue <>();
//            Fib<Integer>fq=new Fib <>();
//            //fq.add(new Node(src, 0),0);
//
//            for(int i=0; i<V; i++)
//            {
//                if(i!=src)
//                {
//                    Fib.heapElements<Integer> x=fq.add(Integer.MAX_VALUE,i);
//                    track[i]=x;
//                }
//                else
//                {
//                    Fib.heapElements<Integer>x=fq.add(0,src);
//                    track[i]=x;
//                }
//
//            }
//
//           // q.add(new Node(src, 0)); //assign source cost from source is zero
//            while (!fq.isEmpty()) {
//                int curr = fq.extractMin();
//                System.out.println(curr);
//                if(inSP[curr])
//                    continue;
//                inSP[curr]=true;
//
//                for (Node i : graph.get(curr)) {
//                    if (distance[curr] + i.cost < distance[i.vertex]) {
//                        distance[i.vertex] = distance[curr] + i.cost;
//                        fq.decreaseKey((track[i.vertex]), distance[i.vertex]);
//                        parent[i.vertex] = curr;
//                        //nextD[curr.vertex] = i.vertex;
//                    }
//                }
//
//
//            }
//            System.out.println("Shortest path cost: " + distance[des]);
//            StringBuilder shortestPath = new StringBuilder();
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
//
//        }
//
//
//
//
//
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int v, e, w, p, q;
//        int source, destination;
//        v = scanner.nextInt();
//        e = scanner.nextInt();
//        int indicator = 0; //0 for dijkstra,1 for bellman-ford
//
//        Graph g1 = new Graph(v, e);
//
//        for (int i = 0; i < e; i++) {
//            p = scanner.nextInt();
//            q = scanner.nextInt();
//            w = scanner.nextInt();
//            g1.addEdge(i, p, q, w);
//
//
//        }
//        source = scanner.nextInt();
//        destination = scanner.nextInt();
//
//            g1.dijkstra(source, destination);
//
//    }
//
//}
//
