//import java.util.Scanner;
//import java.math.*;
///* Class LinkedHashEntry */
//class HashEntry
//{
//    String key;
//    int value;
//
//    /* Constructor */
//    HashEntry(String key, int value)
//    {
//        this.key = key;
//        this.value = value;
//    }
//}
//
///* Class HashTable */
//class HashTable
//{
//    private int TABLE_SIZE;
//    private int size;
//    private HashEntry[] table;
//    private int primeSize;
//
//    /* Constructor */
//    public HashTable(int ts)
//    {
//        size = 0;
//        TABLE_SIZE = ts;
//        table = new HashEntry[TABLE_SIZE];
//        for (int i = 0; i < TABLE_SIZE; i++)
//            table[i] = null;
//        primeSize = getPrime();
//    }
//    /* Function to get prime number less than table size for myhash2 function */
//    public int getPrime()
//    {
//        for (int i = TABLE_SIZE - 1; i >= 1; i--)
//        {
//            int fact = 0;
//            for (int j = 2; j <= (int) Math.sqrt(i); j++)
//                if (i % j == 0)
//                    fact++;
//            if (fact == 0)
//                return i;
//        }
//        /* Return a prime number */
//        return 3;
//    }
//    /* Function to get number of key-value pairs */
//    public int getSize()
//    {
//        return size;
//    }
//    public boolean isEmpty()
//    {
//        return size == 0;
//    }
//    /* Function to clear hash table */
//    public void makeEmpty()
//    {
//        size = 0;
//        for (int i = 0; i < TABLE_SIZE; i++)
//            table[i] = null;
//    }
//    /* Function to get value of a key */
//    public int get(String key)
//    {
//        int hash1 = myhash1( key );
//        int hash2 = myhash2( key );
//        while (table[hash1] != null && !table[hash1].key.equals(key))
//        {
//            hash1 += hash2;
//            hash1 %= TABLE_SIZE;
//        }
//        return table[hash1].value;
//    }
//    /* Function to insert a key value pair */
//    public void insert(String key, int value)
//    {
//        if (size == TABLE_SIZE)
//        {
//            System.out.println("Table full");
//            return;
//        }
//        int hash1 = myhash1( key );
//        int hash2 = myhash2( key );
//        while (table[hash1] != null)
//        {
//            hash1 += hash2;
//            hash1 %= TABLE_SIZE;
//        }
//        table[hash1] = new HashEntry(key, value);
//        size++;
//    }
//    /* Function to remove a key */
//    public void remove(String key)
//    {
//        int hash1 = myhash1( key );
//        int hash2 = myhash2( key );
//        while (table[hash1] != null && !table[hash1].key.equals(key))
//        {
//            hash1 += hash2;
//            hash1 %= TABLE_SIZE;
//        }
//        table[hash1] = null;
//        size--;
//    }
//    /* Function myhash which gives a hash value for a given string */
//    private int myhash1(String x )
//    {
//        int hashVal = x.hashCode( );
//        hashVal %= TABLE_SIZE;
//        if (hashVal < 0)
//            hashVal += TABLE_SIZE;
//        return hashVal;
//    }
//    /* Function myhash function for double hashing */
//    private int myhash2(String x )
//    {
//        int hashVal = x.hashCode( );
//        hashVal %= TABLE_SIZE;
//        if (hashVal < 0)
//            hashVal += TABLE_SIZE;
//        return primeSize - hashVal % primeSize;
//    }
//    /* Function to print hash table */
//    public void printHashTable()
//    {
//        System.out.println("\nHash Table");
//        for (int i = 0; i < TABLE_SIZE; i++)
//            if (table[i] != null)
//                System.out.println(table[i].key +" "+table[i].value);
//    }
//}
//
///* Class DoubleHashingHashTableTest */
//public class DoubleHashingHashTableTest
//{
//    public static void main(String[] args)
//    {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Hash Table Test\n\n");
//        System.out.println("Enter size");
//        /* Make object of HashTable */
//        HashTable ht = new HashTable(scan.nextInt() );
//        char ch;
//        /*  Perform HashTable operations  */
//        do
//        {
//            System.out.println("\nHash Table Operations\n");
//            System.out.println("1. insert ");
//            System.out.println("2. remove");
//            System.out.println("3. get");
//            System.out.println("4. check empty");
//            System.out.println("5. clear");
//            System.out.println("6. size");
//            int choice = scan.nextInt();
//            switch (choice)
//            {
//                case 1 :
//                    System.out.println("Enter key and value");
//                    ht.insert(scan.next(), scan.nextInt() );
//                    break;
//                case 2 :
//                    System.out.println("Enter key");
//                    ht.remove( scan.next() );
//                    break;
//                case 3 :
//                    System.out.println("Enter key");
//                    System.out.println("Value = "+ ht.get( scan.next() ));
//                    break;
//                case 4 :
//                    System.out.println("Empty Status " +ht.isEmpty());
//                    break;
//                case 5 :
//                    ht.makeEmpty();
//                    System.out.println("Hash Table Cleared\n");
//                    break;
//                case 6 :
//                    System.out.println("Size = "+ ht.getSize() );
//                    break;
//                default :
//                    System.out.println("Wrong Entry\n ");
//                    break;
//            }
//            /* Display hash table */
//            ht.printHashTable();
//            System.out.println("\nDo you want to continue (Type y or n)\n");
//            ch = scan.next().charAt(0);
//        }
//        while (ch == 'Y'|| ch == 'y');
//    }
//}
//
//
//import java.util.ArrayList;
//class Map<K, V>
//{
//    class MapNode<K, V>
//    {
//        K key;
//        V value;
//        MapNode<K, V> next;
//        //creating a construtor of the MapNode class
//        public MapNode(K key, V value)
//        {
//            this.key = key;
//            this.value = value;
//            next = null;
//        }
//    }
//    //creating a bucket array that stores key-value pairs
//    ArrayList<MapNode<K, V> > buckets;
//    //variable denotes the number of pairs stored in bucket array - n
//    int size;
//    //variable that denotes the size of the bucketArray - b
//    int numBuckets;
//    //defining the default loadFactor
//    final double DEFAULT_LOAD_FACTOR = 0.75;
//    //creating a construtor of the Map class
//    public Map()
//    {
////defining the size of the bucket
//        numBuckets = 5;
//        buckets = new ArrayList<>(numBuckets);
////loop executes upto number of bucket size and sets nodes initially to null
//        for (int i = 0; i < numBuckets; i++)
//        {
////initially bucket is null
//            buckets.add(null);
//        }
////prints all the values that are initially set
//        System.out.println("HashMap created");
//        System.out.println("Number of pairs in the Map: " + size);
//        System.out.println("Size of Map: " + numBuckets);
//        System.out.println("Default Load Factor : " + DEFAULT_LOAD_FACTOR + "\n");
//    }
//    private int getBucketInd(K key)
//    {
////getting the hash code of the corresponding key by invoking the Object class hashCode() method
//        int hashCode = key.hashCode();
////calculating the array index and returning the same
//        return (hashCode % numBuckets);
//    }
//    //creating a function that inserts elements to the list
//    public void insert(K key, V value)
//    {
////Getting the index at which it needs to be inserted
//        int bucketInd = getBucketInd(key);
//// The first node at that index
//        MapNode<K, V> head = buckets.get(bucketInd);
//// First, loop through all the nodes present at that index
//// to check if the key already exists
////the loop iterate over all the nodes presented at that index and checks if the key already exists or not
//        while (head != null)
//        {
////if the key is already presented, the value is updated
//            if (head.key.equals(key))
//            {
//                head.value = value;
//                return;
//            }
//            head = head.next;
//        }
////creating a new node with key-value pair
//        MapNode<K, V> newElementNode = new MapNode<K, V>(key, value);
//// The head node at the index
//        head = buckets.get(bucketInd);
//// the new node is inserted
//// by making it the head
//        // and it's next is the previous head
//        newElementNode.next = head;
//        buckets.set(bucketInd, newElementNode);
//        System.out.println("Pair(" + key + ", " + value + ") inserted successfully.\n");
//// Incrementing size
//// as new K-V pair is added to the map
//        size++;
////calculating the load factor
//        double loadFactor = (1.0 * size) / numBuckets;
//        System.out.println("Current Load factor = " + loadFactor);
////if the calculated load factor is greater than the default load factor i.e.0.75, the rehashing is done
//        if (loadFactor > DEFAULT_LOAD_FACTOR)
//        {
//            System.out.println(loadFactor + " is greater than " + DEFAULT_LOAD_FACTOR);
//            System.out.println("Therefore Rehashing will be done.\n");
////calling the rehash() function
//            rehash();
//            System.out.println("New Size of Map: " + numBuckets + "\n");
//        }
//        System.out.println("Number of pairs in the Map: " + size);
//        System.out.println("Size of Map: " + numBuckets + "\n");
//    }
//    //function that performs rehashing
//    private void rehash()
//    {
//        System.out.println("\n***Rehashing Started***\n");
////the present bucket list is made temporarly
//        ArrayList<MapNode<K, V> > temp = buckets;
////creating a new bucket list of doubled size of the old bucket size i.e. (2 * numBuckets)
//        buckets = new ArrayList<MapNode<K, V> >(2 * numBuckets);
//        for (int i = 0; i < 2 * numBuckets; i++)
//        {
////initially all elements set to null
//            buckets.add(null);
//        }
//// Now size is made zero
//// and we loop through all the nodes in the original bucket list(temp)
//// and insert it into the new list
//        size = 0;
//        numBuckets = numBuckets * 2;
//        for (int i = 0; i < temp.size(); i++)
//        {
//// head of the chain at that index
//            MapNode<K, V> head = temp.get(i);
//            while (head != null)
//            {
//                K key = head.key;
//                V val = head.value;
//// calling the insert function for each node in temp
//// as the new list is now the bucketArray
//                insert(key, val);
//                head = head.next;
//            }
//        }
//        System.out.println("\n***Rehashing Done***\n");
//    }
//    //method to print the map key-value pairs
//    public void printMap()
//    {
////creating a temporary bucket list
//        ArrayList<MapNode<K, V> > temp = buckets;
//        System.out.println("Current HashMap:");
////loop iterate over the Map
//        for (int i = 0; i < temp.size(); i++)
//        {
////getting the head index of the temporary bucket list
//            MapNode<K, V> head = temp.get(i);
//            while (head != null)
//            {
//                System.out.println("key = " + head.key + ", val = " + head.value);
//                head = head.next;
//            }
//        }
//        System.out.println();
//    }
//}
////main class
//public class RehashingExample
//{
//    public static void main(String args[])
//    {
////creating the instance of the Map class
//        Map<Integer, String> map = new Map<Integer, String>();
////adding elements to the Map
//        map.insert(1, "Hyundai");
//        map.printMap();
//        map.insert(2, "KIA");
//        map.printMap();
//        map.insert(3, "Toyota");
//        map.printMap();
//        map.insert(4, "Mahindra");
//        map.printMap();
//        map.insert(5, "Jeep");
//        map.printMap();
//        map.insert(6, "Ford");
//        map.printMap();
//        map.insert(7, "BMW");
//        map.printMap();
//        map.insert(8, "Audi");
//        map.printMap();
//        map.insert(9, "Mercedes-Benz");
//        map.printMap();
//        map.insert(10, "Ferrari");
//        map.printMap();
//    }
//}
