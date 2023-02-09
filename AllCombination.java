import java.util.*;

public class AllCombination {


    static Vector<String> generateAllStrings(int n, int len){
        //521 is a good prime number
        //n= how many strings to be generated
        //len= of what length
        Set <String> allStringSet = new HashSet <String>();
        String sb;
        String chars = "abcdefghijklmnopqrstuvwxyz";

        Random r = new Random();
        while(allStringSet.size() < n){

            int i = 0;
            StringBuilder s = new StringBuilder();
            while(i< len)
            {   //randomly generate a string
                s.append(chars.charAt(r.nextInt(chars.length())));

                i++;
            }
            sb = String.valueOf(s);
            allStringSet.add(sb);
        }

        Vector<String> v = new Vector<String>();
        for(String x : allStringSet)v.add(x);
        return v;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("The size of table: ");
        int sizeOfTable = sc.nextInt();
        long start, elapsedTime,start2,elapsedTime2;

        Random rand = new Random(System.currentTimeMillis());
        Random r = new Random();
        SeparateChaining.HashTable htSC=new SeparateChaining.HashTable(sizeOfTable);
        LinearProbing.HashTable htLP = new LinearProbing.HashTable(sizeOfTable);
        QuadraticProbing.HashTable htQP=new QuadraticProbing.HashTable(sizeOfTable);
        DoubleHashing.HashTable htDH = new DoubleHashing.HashTable(sizeOfTable);
        float load= (float) 0.4;

        System.out.println();
        for(int j=0;j<=5;j++)
        {
            int totalNUmberOfStrings = (int) (sizeOfTable * load);
            int y = (int) (0.1 * totalNUmberOfStrings);//10% of the total items
            int afterDeleteTotalStrings=totalNUmberOfStrings-y;
            Vector <String> strings = generateAllStrings(totalNUmberOfStrings, 7);


            double avgProbesLP = 0;
            double avgProbesQP = 0;
            double avgProbesDH = 0;

            //firstly insert items
            for (int i = 0; i < strings.size(); i++)
            {
                htDH.insert(strings.get(i), i + 1);
                htLP.insert(strings.get(i), i + 1);
                htQP.insert(strings.get(i), i + 1);
                htSC.insert(strings.get(i), i + 1);
            }

            System.out.print("Load factor= ");
            System.out.printf("%.1f", load);
            System.out.println();
            System.out.println();


            System.out.println("SEPARATE CHAINING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                htSC.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("BEFORE deletion the required time for search is "+elapsedTime);



            System.out.println("BEFORE deletion avg number of probes is N/A");


            System.out.println("LINEAR PROBING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesLP += htLP.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("BEFORE deletion the required time for search is "+elapsedTime);


            avgProbesLP /= totalNUmberOfStrings;
            System.out.println("BEFORE deletion avg number of probes is "+avgProbesLP);


            System.out.println("QUADRATIC PROBING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesQP += htQP.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("BEFORE deletion the required time for search is "+elapsedTime);


            avgProbesQP /= totalNUmberOfStrings;
            System.out.println("BEFORE deletion avg number of probes is "+avgProbesQP);




            System.out.println("DOUBLE HASHING");
             start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesDH += htDH.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("BEFORE deletion the required time for search is "+elapsedTime);


            avgProbesDH /= totalNUmberOfStrings;
            System.out.println("BEFORE deletion avg number of probes is "+avgProbesDH);

            //NOW DELETE 10% OF STRINGS
            for (int i = 0; i < y; i++) {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                htSC.delete(strings.get(l));
                htLP.delete(strings.get(l));
                htQP.delete(strings.get(l));
                htDH.delete(strings.get(l));
            }

             avgProbesLP = 0;
            avgProbesQP = 0;
             avgProbesDH = 0;
            //10% of the present strings after deletion
            int z= (int) (afterDeleteTotalStrings*0.1);

            start = System.nanoTime();
            //now search
            System.out.println("SEPARATE CHAINING");
            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                htSC.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("AFTER deletion the required time for search is "+elapsedTime);



            System.out.println("AFTER deletion avg number of probes is N/A");


            System.out.println("LINEAR PROBING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesLP += htLP.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("AFTER deletion the required time for search is "+elapsedTime);


            avgProbesLP /= totalNUmberOfStrings;
            System.out.println("AFTER deletion avg number of probes is "+avgProbesLP);


            System.out.println("QUADRATIC PROBING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesQP += htQP.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("AFTER deletion the required time for search is "+elapsedTime);


            avgProbesQP /= totalNUmberOfStrings;
            System.out.println("AFTER deletion avg number of probes is "+avgProbesQP);




            System.out.println("DOUBLE HASHING");
            start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbesDH += htDH.Search(strings.get(l));
            }

            elapsedTime = System.nanoTime()- start;




            System.out.println("AFTER deletion the required time for search is "+elapsedTime);


            avgProbesDH /= totalNUmberOfStrings;
            System.out.println("AFTER deletion avg number of probes is "+avgProbesDH);



            load= (float) (load+0.1);
            System.out.println();




        }

    }
}
