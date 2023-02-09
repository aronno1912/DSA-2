
import java.util.Scanner;

public class APSPTC {
    static class Graph
    {   final int INFINITY=Integer.MAX_VALUE;
        int V, E;
        int[][] mat,tc;
        String[][]toBePrinted;
        Graph(int v, int e)
        {
            V = v;
            E = e;
            mat=new int[V+1][V+1];
            tc=new int[V+1][V+1];
            toBePrinted=new String[V+1][V+1];
            for(int i=0;i<V+1;i++)
                for(int j=0;j<V+1;j++)
                {   if(i==j){
                    mat[i][j]=0;

                }
                else {
                    mat[i][j]=INFINITY;

                }

                }


        }
        void addEdge( int a, int b, int c) {
            mat[a][b]=c;
        }



        void FloydWarshall() //O(v^3)
        {
            for(int k=1;k<=V;k++)
            {
                for(int i=1;i<=V;i++)
                {
                    for(int j=1;j<=V;j++)
                    {   if (mat[i][k] == INFINITY || mat[k][j] == INFINITY) continue;
                        mat[i][j]=Integer.min(mat[i][j],(mat[i][k]+mat[k][j]));
                    }
                }
            }
            System.out.println("Using Floyd-Warshall method the result matrix is ");
            for(int i=1;i<=V;i++)
            {
                for(int j=1;j<=V;j++)
                {
                    if(mat[i][j]==INFINITY)
                    {
                        toBePrinted[i][j] = "INF";

                    }
                    else
                        toBePrinted[i][j]= String.valueOf(mat[i][j]);
                    System.out.print(" "+toBePrinted[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            for(int i=1;i<=V;i++)
            {
                for(int j=1;j<=V;j++)
                {
                    if(mat[i][j]==INFINITY)
                    {
                        tc[i][j] = 0;

                    }
                    else
                        tc[i][j]= 1;
                    System.out.print(" "+tc[i][j]+" ");
                }
                System.out.println();
            }
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v, e, w, p, q;
        v = scanner.nextInt();
        e = scanner.nextInt();
        Graph g1 = new Graph(v, e);

        for (int i = 0; i < e; i++)
        {
            p = scanner.nextInt();
            q = scanner.nextInt();
            w = scanner.nextInt();
            g1.addEdge( p, q, w);


        }
        g1.FloydWarshall();


    }
}