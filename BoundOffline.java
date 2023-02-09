//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class BoundOffline {
//
//
//    void display(ArrayList <ArrayList <Character>> matrix, int n) {
//
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//
//            for (int j = 0; j < n; j++) {
//
//                System.out.print(matrix.get(i).get(j));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//    }
//
//        static class Node implements  Comparable <Node>
//    {
//        Node parent;
//        ArrayList <ArrayList <Character>> Matrix = new ArrayList <>();
//        ArrayList<Node>children=new ArrayList <>();
//        boolean isFixed;
//        int fixedColumn, fixedRow;
//        int n;
//
//
//        void addChild(Node x) {
//            children.add(x);
//        }
//
//        @Override
//        public int compareTo(Node o) {
//            return 0;
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n;
//        ArrayList <ArrayList <Character>>inputMatrix;
//       n=scanner.nextInt();
//
//
//        int FixedColumnNumber = 0 ;
//        int  FixedRowNumber = 0;
//        //take the input
//
//        for (int i = 0; i < n; i++)
//        {
//            ArrayList <Character> tmp;
//            for (int j = 0; j < n; j++) {
//
//                char a=scanner.next();
//
//                tmp.push_back(a);
//            }
//
//            inputMatrix.push_back(tmp);
//        }
//    }
//
//
//}
