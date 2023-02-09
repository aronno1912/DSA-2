
import java.util.Scanner;

public class contestAPSP {
    static class Graph
    {   final int INFINITY=Integer.MAX_VALUE;
        int V, E;
        int[][] mat,wt;
        String[][]toBePrinted;
        Graph(int v, int e)
        {
            V = v;
            E = e;
            mat=new int[V+1][V+1];
            //wt=new int[V+1][V+1];
            toBePrinted=new String[V+1][V+1];
            for(int i=0;i<V+1;i++)
                for(int j=0;j<V+1;j++)
                {   if(i==j){
                    mat[i][j]=0;
                    //wt[i][j]=0;
                }
                else {
                    mat[i][j]=INFINITY;

                   // wt[i][j]=INFINITY;

                }

                }


        }
        void addEdge( int a, int b, int c) {

       if(c<mat[a][b])
            { mat[a][b]=c;
                mat[b][a]=c;
            }


        }





        void FloydWarshall(int src) //O(v^3)
        {
            for(int k=0;k<V;k++)
            {
                for(int i=0;i<V;i++)
                {
                    for(int j=0;j<V;j++)
                    {   if (mat[i][k] == INFINITY || mat[k][j] == INFINITY) continue;
                       // mat[i][j]=Integer.min(mat[i][j],mat[j][i]);
                        int x=Integer.max(mat[i][k],mat[k][j]);
                        mat[i][j]=Integer.min(mat[i][j],x);
                    }
                }
            }
           // System.out.println("Using Floyd-Warshall method the result matrix is ");


                for(int j=0;j<V;j++)
                {
                    if(mat[src][j]==INFINITY)
                    {
                        toBePrinted[src][j] = "Impossible";

                    }
                    else
                        toBePrinted[src][j]= String.valueOf(mat[src][j]);
                    System.out.println(toBePrinted[src][j]);
                }


        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t=scanner.nextInt();
        int d=1;
        while(t-->0) {

            int v, e, w, p, q;
            v = scanner.nextInt();
            e = scanner.nextInt();
            Graph g1 = new Graph(v, e);

            for (int i = 0; i < e; i++) {
                p = scanner.nextInt();
                q = scanner.nextInt();
                w = scanner.nextInt();
                g1.addEdge(p, q, w);


            }

            int src = scanner.nextInt();
            System.out.println("Case "+d+":");
            g1.FloydWarshall(src);
            d++;
        }

    }
}
