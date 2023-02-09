import java.util.Scanner;

public class APSP2 {
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
            wt=new int[V+1][V+1];
            toBePrinted=new String[V+1][V+1];
            for(int i=0;i<V+1;i++)
                for(int j=0;j<V+1;j++)
                {   if(i==j){
                    mat[i][j]=0;
                    wt[i][j]=0;
                }
                else {
                    mat[i][j]=INFINITY;
                    wt[i][j]=INFINITY;

                }

                }
//            for(int i=0;i<V;i++)
//                for(int j=0;j<V;j++)
//                {
//                    if(i==j)wt[i][j]=0;
//                    else {
//                        wt[i][j]=INFINITY;}
//                }

        }
        void addEdge( int a, int b, int c) {
            mat[a][b]=c;
            wt[a][b]=c;//for mm
        }

        int[][]extendedSP(int[][] l, int[][] w) //O(v^3)
        {
            int[][]LL=new int[V+1][V+1];

            for(int i=1;i<=V;i++)
                for(int j=1;j<=V;j++)
                {
                    LL[i][j]=INFINITY;
                    for(int k=1;k<=V;k++)
                    {
                        if (l[i][k] == INFINITY || wt[k][j] == INFINITY) continue;
                        LL[i][j]=Math.min(LL[i][j],l[i][k]+wt[k][j]);
                    }
                }


            return LL;
        }

        //        void SlowMatrixMultiplication() //O(v^4)
//        {
//
//          int[][]L=new int[V+1][V+1];
//            for(int i=1;i<=V;i++)
//                for(int j=1;j<=V;j++)
//                    L[i][j]=wt[i][j];
//            for(int m=2;m<=V-1;m++)
//            {
//              L=extendedSP(L,wt);
//            }
//            System.out.println("Using Matrix multiplication method the result matrix is ");
//            for(int i=1;i<=V;i++)
//            {
//                for(int j=1;j<=V;j++)
//                {
//                    if(L[i][j]==INFINITY)
//                    {
//                        toBePrinted[i][j] = "INF";
//
//                    }
//                    else
//                        toBePrinted[i][j]= String.valueOf(L[i][j]);
//                    System.out.print(" "+toBePrinted[i][j]+" ");
//                }
//                System.out.println();
//            }
//
//
//        }
        void fastMatrixMultiplication() //O(v^3logV)

        {
            int[][]L=new int[V+1][V+1];
            for(int i=1;i<=V;i++)
                for(int j=1;j<=V;j++)
                    L[i][j]=wt[i][j];
            int m=1;
            //int[][]Ll=new int[V+1][V+1];
            while(m<V-1)
            {
                L=extendedSP(L,L);
                m=2*m;
            }

            System.out.println("Using Matrix multiplication method the result matrix is ");
            for(int i=1;i<=V;i++)
            {
                for(int j=1;j<=V;j++)
                {
                    if(L[i][j]==INFINITY)
                    {
                        toBePrinted[i][j] = "INF";

                    }
                    else
                        toBePrinted[i][j]= String.valueOf(L[i][j]);
                    System.out.print(" "+toBePrinted[i][j]+" ");
                }
                System.out.println();
            }

        }
        void FloydWarshall() //O(v^3)
        {
            int idx = 0;
            for (int k = 1; k <= V; k++) {
                for (int i = 1; i <= V; i++) {
                    for (int j = 1; j <= V; j++) {
                        if (mat[i][k] == INFINITY || mat[k][j] == INFINITY) continue;
                        mat[i][j] = Integer.min(mat[i][j], (mat[i][k] + mat[k][j]));
                    }
                }
            }
//            int[][] dis = new int[V + 1][V + 1];
//            for (int i = 1; i <= V; i++) {
//                for (int j = 1; j <= V; j++) {
//                    dis[i][j] = mat[i][j];
//                }
//            }
//
//
            for (int i = 1; i <= V; i++) {
                if (mat[i][i] < 0)

                    idx = 1;
                break;


            }
            if (idx != 1)
            {
                System.out.println("Using Floyd-Warshall method the result matrix is ");
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (mat[i][j] == INFINITY) {
                        toBePrinted[i][j] = "INF";

                    } else
                        toBePrinted[i][j] = String.valueOf(mat[i][j]);
                    System.out.print(" " + toBePrinted[i][j] + " ");
                }
                System.out.println();
            }
        }

            if(idx==1) System.out.println("negCycle");
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
        //g1.SlowMatrixMultiplication();
        //g1.fastMatrixMultiplication();

    }
}

