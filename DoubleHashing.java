import java.util.*;

/**
 *the average number of elements stored in
 * a chain  =number of elements/tableSize
 * **/

public class DoubleHashing {
    static class HashNode {
        String key;
        int value;

        HashNode(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class HashTable {

        private int capacity;

        private ArrayList<HashNode> hashTab;

        public HashTable(int cap)
        {
            this.capacity = cap;
            hashTab = new ArrayList<>();
            for (int i = 0; i < capacity; i++)
                hashTab.add(null);
        }

//        int hashFunc1(String key, int m) {
//            //here m is the table size
//            long p = 179; //a prime number
//            int h = 0;
//            for (int i = 0; i < key.length(); i++) {
//                char c = key.charAt(i);
//                h = (int) ((h * p + c) % m);
//            }
//            return h;
//        }


        int hashFunc1(String key, int size) {
            //            //here m is the table size
//            int h = 0;
//            for (int i = 0; i < key.length(); i++) {
//                char c = key.charAt(i);
//                h=h*79+c;
//            }
//            h=(int)(h)%m;
//            return h;


            int pr = 179; //a prime number
            int h = 0;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);

                h = (int) ((h * pr + c) % size);
            }
            return h;
        }


//
//        /**
//         *   int hashFunc2(String key, int m) {
//         *             //here m is the table size
//         *             long p = 593; //a prime number
//         *             int h = 0;
//         *             for (int i = 0; i < key.length(); i++) {
//         *                 char c = key.charAt(i);
//         *                 h = (int) ((h * p + c) % m);
//         *             }
//         *             return h;
//         *         }
//         *
//         *
//         * */


        /**
        *  let m be prime and to design h2 so
       that it always returns a positive integer less than m.
         */
        int hashFunc2(String key, int m) {
            //here m is the table size
            int p = 593; //a prime number
            int h = 0;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                h=(h*p+c)%m;
            }

            return h;
        }


        public int Search(String key) {
            int hash1,hash2;


            hash1 = hashFunc1(key, capacity);
            hash2 = hashFunc2(key, capacity);

            int count=0;
            /** we need to use the following hash function for double hashing
             *
             *         h(k, i) = ( h1(k) + i*h2(k) ) mod N
             * */
            for (int i = 0; i < capacity; i++)
            {
                int u = (int) ((hash1+i*hash2 ) % capacity);
                HashNode head = hashTab.get(u);
                if (head == null) continue;
                if (head.key == key)
                {
                    break;

                }
                count++;
            }

            return count;// for unsuccessful search
        }


        public void insert(String key, int value) {

            int hash1,hash2;


            hash1 = hashFunc1(key, capacity);
            hash2 = hashFunc2(key, capacity);

            int count=0;
            /** we need to use the following hash function for double hashing
             *
             *         h(k, i) = ( h1(k) + i*h2(k) ) mod N
             * */
            for (int i = 0; i < capacity; i++)
            {
                int u = (int) ((hash1+i*hash2 ) % capacity);
                HashNode head = hashTab.get(u);
                if (head == null) {
                    hashTab.set(u, new HashNode(key, value));
                    break;

                }
            }

        }


        public HashNode delete(String key) {
            int hash1,hash2;


            hash1 = hashFunc1(key, capacity);
            hash2 = hashFunc2(key, capacity);

            int count=0;
            /** we need to use the following hash function for double hashing
             *
             *         h(k, i) = ( h1(k) + i*h2(k) ) mod N
             * */
            for (int i = 0; i < capacity; i++)
            {
                int u = (int) ((hash1+i*hash2 ) % capacity);
                HashNode head = hashTab.get(u);
                if (head == null) continue;
                if (head.key == key) {
                    hashTab.set(u, null);
                    return head;

                }
            }
            return null;
        }
    }

    static void verify(Vector <String> v,int numOfStringsToBeGenerated)
    {
        int sizeOfSet=v.size();
        int x=sizeOfSet/numOfStringsToBeGenerated;
        System.out.println(x*100);
    }
    static Vector<String> generateAllStrings(int n, int len){
        //521 is a good prime number
        //n= how many strings to be generated
        //len= of what length
        Set<String> allStringSet = new HashSet<String>();
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
        //verify(v,n);
        return v;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("The size of table: ");
        int sizeOfTable = sc.nextInt();

        Random rand = new Random(System.currentTimeMillis());
        Random r = new Random();
        HashTable ht = new HashTable(sizeOfTable);
        float load= (float) 0.4;
        System.out.println("FOR Double Hashing");
        System.out.println();
        for(int j=0;j<=5;j++)
        {
            int totalNUmberOfStrings = (int) (sizeOfTable * load);
            int y = (int) (0.1 * totalNUmberOfStrings);//10% of the total items
            int afterDeleteTotalStrings=totalNUmberOfStrings-y;
            Vector <String> strings = generateAllStrings(totalNUmberOfStrings, 7);


            double avgProbes = 0;
            //firstly insert items
            for (int i = 0; i < strings.size(); i++)
            {
                ht.insert(strings.get(i), i + 1);
            }

            long start = System.nanoTime();
            //now search

            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbes += ht.Search(strings.get(l));
            }

            long elapsedTime = System.nanoTime()- start;



            System.out.print("Load factor= ");
            System.out.printf("%.1f", load);
            System.out.println();

            System.out.println("BEFORE deletion the required time for search is "+elapsedTime);


            avgProbes /= totalNUmberOfStrings;
            System.out.println("BEFORE deletion avg number of probes is "+avgProbes);

            //NOW DELETE 10% OF STRINGS
            for (int i = 0; i < y; i++) {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                ht.delete(strings.get(l));
            }
            avgProbes=0;
            //10% of the present strings after deletion
            int z= (int) (afterDeleteTotalStrings*0.1);
            long start2 = System.nanoTime();
            for (int i = 0; i < y; i++)
            {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                avgProbes += ht.Search(strings.get(l));
            }
            long elapsedTime2 = System.nanoTime()- start2;

            System.out.println("AFTER deletion the required time for search is "+elapsedTime2);


            avgProbes /= totalNUmberOfStrings;
            System.out.println("AFTER deletion avg number of probes is "+avgProbes);


            load= (float) (load+0.1);
            System.out.println();


        }

    }
}



