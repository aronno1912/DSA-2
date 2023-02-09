//import java.util.Scanner;
//import java.util.Queue;
//import java.util.ArrayDeque;
//
//import static sun.java2d.cmm.ColorTransform.In;
//
//public class Baseball
//{
//    private String[] tNames;
//    private int[] tWins;
//    private int[] tLosses;
//    private int[] tRemain;
//    private int[][] tRemainOthers;
//    private int cTeams;
//    private int toTCapacity = 0;
//
//    private Queue<String> queue;
//
//    public Baseball(String filename)
//    {
//        In in = new In(filename);
//        cTeams = Integer.parseInt(in.readLine());
//        tNames = new String[cTeams];
//        tWins = new int[cTeams];
//        tLosses = new int[cTeams];
//        tRemain = new int[cTeams];
//        tRemainOthers = new int[cTeams][cTeams];
//        queue = new ArrayDeque<String>();
//        for (int iTeam = 0; iTeam < cTeams; iTeam++) {
//            String line = in.readLine();
//            Scanner lineScanner = new Scanner(line);
//            tNames[iTeam] = lineScanner.next();
//            tWins[iTeam] = lineScanner.nextInt();
//            tLosses[iTeam] = lineScanner.nextInt();
//            tRemain[iTeam] = lineScanner.nextInt();
//            for (int iAgainst = 0; iAgainst < cTeams; iAgainst++) {
//                tRemainOthers[iTeam][iAgainst] = lineScanner.nextInt();
//            }
//        }
//
//    }
//    public int numberOfTeams() {
//        return cTeams;
//    }
//    public Iterable<String> teams() {
//        Stack<String> teams = new Stack<String>();
//        for (String s : tNames) {
//            teams.push(s);
//        }
//        return teams;
//    }
//    public int numberWins(String team) {
//        for (int i = 0; i < cTeams; i++) {
//            if (team.equals(tNames[i])) {
//                return  tWins[i];
//            }
//        }
//        throw new java.lang.IllegalArgumentException();
//    }
//
//    public int numberLosses(String team) {
//        for (int i = 0; i < cTeams; i++) {
//            if (team.equals(tNames[i])) {
//                return  tLosses[i];
//            }
//        }
//        throw new java.lang.IllegalArgumentException();
//    }
//    public int determineRemainingTeam(String team) {
//        for (int i = 0; i < cTeams; i++) {
//            if (team.equals(tNames[i])) {
//                return  tRemain[i];
//            }
//        }
//        throw new java.lang.IllegalArgumentException();
//    }
//    public int determineMatching(String team1, String team2) {
//        int numLeft = 0;
//        int one = -1;
//        int two = -1;
//        for (int i = 0; i < cTeams; i++) {
//            if (team1.equals(tNames[i])) {
//                one = i;
//            }
//            if (team2.equals(tNames[i])) {
//                two = i;
//            }
//        }
//        if (one == -1 || two == -1) {
//            throw new java.lang.IllegalArgumentException();
//        }
//        numLeft = tRemainOthers[one][two];
//        return numLeft;
//    }
//
//    private boolean trivialSolution(int team) {
//        boolean elim = false;
//        for (int i = 0; i < cTeams; i++) {
//            if (team != i && tWins[team] + tRemain[team] < tWins[i]) {
//                elim = true;
//                queue.add(tNames[i]);
//                break;
//            }
//        }
//        return elim;
//    }
//
//    private FordFulkerson nontrivialSolution(int team) {
//        int games = ( (cTeams - 1) * cTeams ) / 2;
//        FlowNetwork graph = new FlowNetwork( games + cTeams + 2 );
//        int s = games + cTeams;
//        int t = games + cTeams + 1;
//        int vertex = 0;
//        toTCapacity = 0;
//        for (int i = 0; i < cTeams; i++) {
//            graph.addEdge(new FlowEdge(games + i, t, tRemain[team] + tWins[team] - tWins[i]));
//            for (int j = i + 1; j < cTeams; j++) {
//                graph.addEdge(new FlowEdge(vertex, games + i, Double.POSITIVE_INFINITY));
//                graph.addEdge(new FlowEdge(vertex, games + j,  Double.POSITIVE_INFINITY));
//                graph.addEdge(new FlowEdge(s, vertex, tRemainOthers[i][j]));
//                vertex ++;
//                toTCapacity += tRemainOthers[i][j];
//            }
//        }
//        int index = 0;
//        FordFulkerson graph2 = new FordFulkerson(graph, s, t);
//        for (int i = games; i < games + cTeams; i++) {
//            if (graph2.inCut(i)){
//                queue.add(tNames[index]);
//            }
//            index++;
//        }
//        return new FordFulkerson(graph, s, t);
//    }
//    public boolean eliminatedTeam(String team) {
//        queue = new ArrayDeque<String>();
//        int index = -1;
//        for (int i = 0; i < cTeams; i++) {
//            if(team.equals(tNames[i])){
//                index = i;
//            }
//        }
//        if (index != -1){
//            if (trivialSolution(index)) {
//                return true;
//            } else {
//                FordFulkerson graph2 = nontrivialSolution(index);
//                if (toTCapacity != graph2.value()) {
//                    return true;
//                }
//                return false;
//            }
//        } else {
//            throw new java.lang.IllegalArgumentException();
//        }
//    }
//
//    public Iterable<String> certificateElim(String team) {
//        queue = new ArrayDeque<String>();
//        int index = -1;
//        for (int i = 0; i < cTeams; i++) {
//            if(team.equals(tNames[i])){
//                index = i;
//            }
//        }
//        int notEliminated = -1;
//        if (index != -1) {
//            if (trivialSolution(index)) {
//                System.out.println("queue : " + queue.toString());
//                return queue;
//            } else {
//                FordFulkerson graph2 = nontrivialSolution(index);
//                if (queue.size() == 0) {
//                    return null;
//                }
//                return queue;
//            }
//        } else {
//            throw new java.lang.IllegalArgumentException();
//        }
//    }
//
//    public static void main(String[] args) {
//        Baseball division = new Baseball("testInput/teams4.txt");
//        for (String team : division.teams()) {
//            if (division.eliminatedTeam(team)) {
//                StdOut.print(team + " is eliminated by the subset R = { ");
//                for (String t : division.certificateElim(team)) {
//                    StdOut.print(t + " ");
//                }
//                StdOut.println("}");
//            } else {
//                StdOut.println(team + " is not eliminated");
//            }
//        }
//    }
//}