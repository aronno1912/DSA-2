import java.util.*;

public  class SeparateChaining {


    static class HashNode
    {
        String key;
        int value;
        HashNode nextPointer;

        public HashNode() {
            key = null;
            value = 0;
        }

        public HashNode(String key, int value) {
            this.key = key;
            this.value = value;
        }


    }
    static class HashTable
    {
        private ArrayList <HashNode> table;
        //table's capacity
        private int capacity;
        private int currentSize;

        public HashTable(int cap)
        {
            this.capacity = cap;
            table = new ArrayList <>();
            for (int i = 0; i < capacity; i++)
                table.add(null);
        }

        int hashFunc(String key, int size)
        {
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


        public void Search(String key)
        {

            int index;

            index = hashFunc(key, capacity);
            // head where
            HashNode curr = table.get(index);
            int count = 1;
            while (curr != null) {
                if (curr.key.equals(key))
                    break;
                curr = curr.nextPointer;

            }

        }

        public int insert(String key, int value)
        {

            //returns collision number
            int index;

            index = hashFunc(key, capacity);

            if (index < 0) index *= -1;
            HashNode head = table.get(index);
            int count = 0;
            while (head != null) {
                if (head.key.equals(key)) {
                    return count;
                }
                head = head.nextPointer;
                count++;
            }

            head = table.get(index);
            HashNode newNode = new HashNode(key, value);
            newNode.nextPointer = head;
            table.set(index, newNode);
            return count;
        }



        public HashNode delete(String key)
        {
            int index;

            //locate its position
            index = hashFunc(key, capacity);

          //locate head
            HashNode headChain = table.get(index);

            HashNode prev = null;
            while (headChain != null) {
                if (headChain.key.equals(key))
                    break;
                prev = headChain;
                headChain = headChain.nextPointer;
            }

            if (headChain == null)
                return null;//doesn't exist

            currentSize--;

            if (prev != null)
                prev.nextPointer = headChain.nextPointer;
            else
                table.set(index, headChain.nextPointer);

            return headChain;
        }
    }

    static void verify(Vector <String> v,int numOfStringsToBeGenerated)
    {
        int sizeOfSet=v.size();
        int x=sizeOfSet/numOfStringsToBeGenerated;
        System.out.println(x*100);
    }
    static Vector <String> generateALLStrings(int n, int len)
    {
        //n= how many strings to be generated
        //len= of what length
        Set <String> allStringSet = new HashSet <String>();

        String chars = "abcdefghijklmnopqrstuvwxyz";
        String sb;
        Random r = new Random();
        while (allStringSet.size() < n) {

            int i = 0;
            StringBuilder s = new StringBuilder();
            while (i < len) {
                //randomly generate a string
                s.append(chars.charAt(r.nextInt(chars.length())));

                i++;
            }
            sb = String.valueOf(s);
            allStringSet.add(sb);
        }

        Vector <String> v = new Vector <String>();

        for (String x : allStringSet) v.add(x);
        //here vector of String v contains n number of strings of length 7
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
//        //they are number of strings to be generated for load factor 0.4,0.5,0.6,0.7,0.8,0.9 respectively;
//        int load1,load2,load3,load4,load5,load6;
//
//         int x=load1;
        float load= (float) 0.4;
        //iterates for 6 load factors

        System.out.println("FOR SINGLE CHAINING ");

        for(int j=0;j<=5;j++) {
             int totalNUmberOfStrings= (int) (sizeOfTable*load);
             int y= (int) (0.1*totalNUmberOfStrings);//10% of the total items
            int afterDeleteTotalStrings=totalNUmberOfStrings-y;
             Vector <String> strings = generateALLStrings(totalNUmberOfStrings, 7);


             for (int i = 0; i < strings.size(); i++) {
                ht.insert(strings.get(i), i + 1);
             }
             //search y number of strings before deletion

             long start = System.nanoTime();

           //search 10% of the strings
             for (int i = 0; i < y; i++) {
                 int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                 ht.Search(strings.get(l));
             }
             long elapsedTime = System.nanoTime() - start;


            System.out.print("Load factor= ");
            System.out.printf("%.1f", load);
            System.out.println();

             System.out.println("before deletion the required time for search is "+elapsedTime);

           //NOW DELETE 10% OF STRINGS
            for (int i = 0; i < y; i++) {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                ht.delete(strings.get(l));
            }


           // System.out.println("start time is "+start2);

           int z= (int) (afterDeleteTotalStrings*0.1);
            long start2 = System.nanoTime();
            for (int i = 0; i < y; i++) {
                int l = (r.nextInt() % strings.size() + strings.size()) % strings.size();
                ht.Search(strings.get(l));
            }
            long elapsedTime2 = System.nanoTime() - start2;
            System.out.println("AFTER deletion the required time for search is "+elapsedTime2);




             load= (float) (load+0.1);
            System.out.println();

         }






    }
}
