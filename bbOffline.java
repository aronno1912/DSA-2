import java.util.Scanner;

public class bbOffline {
    static class BBgame {

        String[] team;
         int[] teamWins, teamLoses, teamRemainingMatches;
       int[][] against;
        int totalTeams;

        BBgame( int tT,int[] teamW, int[] teamL,int[] teamR,int[][] ag,String[] t)
        {
            int v=tT;
            totalTeams=tT;


            against=new int[v][v];
            team=new String[v];
            teamWins=new int[v];
            teamLoses=new int[v];
            teamRemainingMatches=new int[v];
            for(int i=0;i<v;i++)
            {
               team[i]=t[i];
               teamWins[i]=teamW[i];
               teamLoses[i]=teamL[i];
               teamRemainingMatches[i]=teamR[i];

                for(int j=0;j<v;j++)
                {

                    against[i][j]=ag[i][j];
                }

            }

        }


        public boolean easyElimination(int x) {
            boolean ans = false;
            for (int i = 0; i < totalTeams; i++) {
                if (x != i && (teamWins[x] + teamRemainingMatches[x] < teamWins[i]))
                    ans = true; //that means team x is eliminated
            }
            return ans;

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

//        for(int i=0;i<v;i++)
//        {
//            for(int j=0;j<v;j++)
//            {
//
//                System.out.print(" "+against[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        for(int i=0;i<v;i++)
//        {
//            System.out.println(team[i]);
//        }

    BBgame b=new BBgame(v,teamWins,teamLoses,teamRemainingMatches,against,team);

        
    }
}
