
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class rbNode {
    int value;
    rbNode left;
    rbNode right;
    rbNode parent;
    int color;

    rbNode getNode(int k){return this;}
}

public class redBlackTree3 {


    static final int BLACK = 0;
    static final int RED   = 1;
    List <Integer> inorderTraversal=new ArrayList <>();

    private rbNode root;
    private rbNode nullNode;



    public redBlackTree3() {
        nullNode = new rbNode();
        //initially black
        nullNode.color = BLACK;
        nullNode.left = null;
        nullNode.right = null;
        root = nullNode;
    }

    //in whose respect the other is moving
    public void leftRotate(rbNode pivot)
    {
        rbNode nodeToBeRotated = pivot.right;
        pivot.right = nodeToBeRotated.left;
        if (nodeToBeRotated.left != nullNode) {
            nodeToBeRotated.left.parent = pivot;
        }
        nodeToBeRotated.parent = pivot.parent;
        if (pivot.parent == null) {
            this.root = nodeToBeRotated;
        } else if (pivot == pivot.parent.left) {
            pivot.parent.left = nodeToBeRotated;
        } else {
            pivot.parent.right = nodeToBeRotated;
        }
        nodeToBeRotated.left = pivot;
        pivot.parent = nodeToBeRotated;
    }

    public void rightRotate(rbNode pivot)
    {
        rbNode leftChildOfPivot = pivot.left;
        pivot.left = leftChildOfPivot.right;
        if (leftChildOfPivot.right != nullNode) {
            leftChildOfPivot.right.parent = pivot;
        }
        leftChildOfPivot.parent = pivot.parent;
        if (pivot.parent == null) {
            this.root = leftChildOfPivot;
        } else if (pivot == pivot.parent.right) {
            pivot.parent.right = leftChildOfPivot;
        } else {
            pivot.parent.left = leftChildOfPivot;
        }
        leftChildOfPivot.right = pivot;
        pivot.parent = leftChildOfPivot;
    }

    public rbNode maximum(rbNode node)
    {
        while (node.right != nullNode)
        {
            node = node.right;
        }
        return node;
    }

    public rbNode minimum(rbNode node)
    {
        while (node.left != nullNode)
        {
            node = node.left;
        }
        return node;
    }

    // Search in the the tree
    private rbNode searchHelper(rbNode node, int key) {
        if (node == nullNode || key == node.value) {
            return node;
        }

        if (key < node.value) {
            return searchHelper(node.left, key);
        }
        return searchHelper(node.right, key);
    }

    private void inOrderHelper(rbNode node) {
        //ArrayList <Integer> inorderTraversal=new ArrayList <>();

        if (node != nullNode) {
            inOrderHelper(node.left);
            //System.out.print(node.value + " ");
            inorderTraversal.add(node.value);
            inOrderHelper(node.right);
        }
        //System.out.println("size in helper "+inorderTraversal.size());

    }

    private void deleteHelper(rbNode node, int toBeDeletedKey)
    {
        rbNode z = nullNode;
        rbNode x, y;
        //find out the exact position from the root
        while (node != nullNode)
        {
            if (node.value == toBeDeletedKey) {
                z = node;
            }

            if (node.value <= toBeDeletedKey) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == nullNode) {
            //no such node with this key in the tree
            return;
        }
        //z is the node to be deleted
        y = z;
        int actualColorOfToBeDeletedNode = y.color;
        if (z.left == nullNode)
        {
            //has only one child,so simply promote it
            x = z.right;
            rbReplace(z, z.right);
        } else if (z.right == nullNode)
        {
            ////has only one child,so simply promote it
            x = z.left;
            rbReplace(z, z.left);
        } else
        {
            //has both child..so internal node
            //get the successor and replace the value and delete
            y = minimum(z.right);
            actualColorOfToBeDeletedNode = y.color;
            x = y.right;
            if (y.parent == z)
            {
                x.parent = y;
            } else
            {
                rbReplace(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbReplace(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (actualColorOfToBeDeletedNode == BLACK) {
            afterDeleteFixUp(x);
        }
    }

    // Balance the tree after deletion of a node
    private void afterDeleteFixUp(rbNode needToBeFixed)
    {
        rbNode sibling;
        while (needToBeFixed != root && needToBeFixed.color == BLACK)
        {
            //if that node is the left child

            if (needToBeFixed == needToBeFixed.parent.left)
            {
                sibling = needToBeFixed.parent.right;


                /**
                 * Case I: tobeFixed's sibling  is red
                 */
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    needToBeFixed.parent.color = RED;
                    leftRotate(needToBeFixed.parent);
                    sibling = needToBeFixed.parent.right;
                }

                /**
                 * Case II: tobeFixed's sibling w is black and both of w’s children are black
                 */

                if (sibling.left.color == BLACK && sibling.right.color == BLACK)
                {
                    sibling.color = RED;
                    needToBeFixed = needToBeFixed.parent;
                }

                else //not both black children
                {
                    /**
                     * Case III: tobeFixed's sibling w is black and left child of w is red and right child of w is black
                     */
                    if (sibling.right.color == BLACK) {
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rightRotate(sibling);
                        sibling = needToBeFixed.parent.right;
                    }

                    /**
                     * Case IV: x’s sibling w is black and right child of w is red
                     */
                    sibling.color = needToBeFixed.parent.color;
                    needToBeFixed.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    leftRotate(needToBeFixed.parent);
                    needToBeFixed = root;
                }
            }


            //all same as its if condition,now just the node is the right child
            else
            {
                sibling = needToBeFixed.parent.left;

                /**
                 * Case I: tobeFixed's sibling  is red
                 */
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    needToBeFixed.parent.color = RED;
                    rightRotate(needToBeFixed.parent);
                    sibling = needToBeFixed.parent.left;
                }



                /**
                 * Case II: tobeFixed's sibling w is black and both of w’s children are black
                 */
                if (sibling.right.color == BLACK && sibling.right.color == BLACK)
                {
                    sibling.color = RED;
                    needToBeFixed = needToBeFixed.parent;
                }

                else
                {

                    /**
                     * Case III: tobeFixed's sibling w is black and left child of w is red and right child of w is black
                     */
                    if (sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        leftRotate(sibling);
                        sibling = needToBeFixed.parent.left;
                    }

                    /**
                     * Case IV: x’s sibling w is black and right child of w is red
                     */
                    sibling.color = needToBeFixed.parent.color;
                    needToBeFixed.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rightRotate(needToBeFixed.parent);
                    needToBeFixed = root;
                }
            }
        }
        needToBeFixed.color = BLACK;
    }

    //replaces node one with node two
    private void rbReplace(rbNode one, rbNode two) {



        if (one.parent == null) {

            root = two;


        }
        //if one is the left child
        else if (one == one.parent.left)
        {
            one.parent.left = two;

        }
        else
        {
            one.parent.right = two;
        }
        two.parent = one.parent;
    }



    // Balance the node after insertion
    private void insertFixUp(rbNode newlyInsertedNode) {
        rbNode uncle;
        //the current color of rbNode k is red..Because after insertion the color of new node is red
        while (newlyInsertedNode.parent.color == RED)
        {
            if (newlyInsertedNode.parent == newlyInsertedNode.parent.parent.right)
            {
                //parent is right child,so left child is the uncle of newlyInsertedNode
                uncle = newlyInsertedNode.parent.parent.left;

                //Case I: z’s uncle y is red
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    newlyInsertedNode.parent.color = BLACK;
                    newlyInsertedNode.parent.parent.color = RED;
                    //until we get a root,we will perform such recoloring
                    newlyInsertedNode = newlyInsertedNode.parent.parent;
                } else {

                    //if the uncle is black or null
                    if (newlyInsertedNode == newlyInsertedNode.parent.left) {
                        newlyInsertedNode = newlyInsertedNode.parent;
                        rightRotate(newlyInsertedNode);
                    }
                    newlyInsertedNode.parent.color = BLACK;
                    newlyInsertedNode.parent.parent.color = RED;
                    leftRotate(newlyInsertedNode.parent.parent);
                }
            } else {
                //do the same thing as above
                uncle = newlyInsertedNode.parent.parent.right;

                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    newlyInsertedNode.parent.color = BLACK;
                    newlyInsertedNode.parent.parent.color = RED;
                    newlyInsertedNode = newlyInsertedNode.parent.parent;
                } else {
                    if (newlyInsertedNode == newlyInsertedNode.parent.right) {
                        newlyInsertedNode = newlyInsertedNode.parent;
                        leftRotate(newlyInsertedNode);
                    }
                    newlyInsertedNode.parent.color = BLACK;
                    newlyInsertedNode.parent.parent.color = RED;
                    rightRotate(newlyInsertedNode.parent.parent);
                }
            }
            if (newlyInsertedNode == root) {
                break;
            }
        }
        root.color = BLACK;
    }



    public int search(int key)
    {   int z;
        rbNode x= searchHelper(this.root, key);
        if(x==nullNode||x==null)z=0;
        else z=1;
        //if(z==-1) System.out.println("Priority "+key+" not found");
        //if(z==1) System.out.println("Priority "+key+" found");
        return z;
    }





    public int getLessPriority(int key)

    {
        int x=0;
        inorderTraversal.clear();
        inOrderHelper(this.root);

        int i=0;
        int count=inorderTraversal.size();
        //System.out.println("size "+count+" is");
        for(int j=0;j<count;j++)
        {
            if(inorderTraversal.get(i)<key)
            {
                i++;
                x++;
            }
        }

        return x;

    }




    public void insert(int key) {
        int found=search(key);
        if(found==1)
        {
            // System.out.println("Same priority ("+key+") exists");
            System.out.print(0);
            return;
        }
        rbNode node = new rbNode();
        node.parent = null;
        node.value = key;

        //inserted node is initially red
        node.color = RED;
        node.left = nullNode;
        node.right = nullNode;

        rbNode y = null;
        rbNode x = this.root;

        //find the exact position of insert

        while (x != nullNode)
        {

            y = x;
            if (node.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.value < y.value) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            //root must be black
            node.color = BLACK;
            //System.out.println("Successful initiation "+key);
            System.out.print(1);
            return;
        }

        if (node.parent.parent == null) {
            //System.out.println("Successful initiation "+key);
            System.out.print(1);
            return;
        }

        insertFixUp(node);
        //System.out.println("Successful initiation "+key);
        System.out.print(1);
    }

    public rbNode getRoot() {
        return this.root;
    }

    public void deleteNode(int key) {

        int found=search(key);
        if(found==0)
        {
            //System.out.println("No priority  ("+key+") exists");
            System.out.print(0);
            return;
        }

        deleteHelper(this.root, key);
        //System.out.println("Successful termination "+key);
        System.out.print(1);
    }


    public int getLessPriority2(int key,rbNode currentNode)
    {



        if (currentNode== null||currentNode==nullNode) {
            return 0;
        }

        //current node has value less than the key so,we definitely get less values by going its left subtree and may also get
        //some values less than the key by going to its right subtree
        else if ( currentNode.value < key)
        {
            return 1 + getLessPriority2( key, currentNode.left) + getLessPriority2( key, currentNode.right);
        }

        ////current node has value greater than the key
        else
        {
            return getLessPriority2( key, currentNode.left);

        }
//        else {
//            return getLessPriority2( key, root.right);
//        }




    }


    /*
     *    0--->delete
     *    1--->insert
     *     2--->search
     *  */
    public static void main(String[] args) {
        redBlackTree3 tree = new redBlackTree3();

        int lines;
        Scanner scn=new Scanner(System.in);
        lines= scn.nextInt();
        System.out.println(lines);

        for(int i=0;i<lines;i++)
        {

            int command=scn.nextInt();
            int val=scn.nextInt();
            if(command==0)
            {
                System.out.print(command+" "+val+" ");
                tree.deleteNode(val);
                System.out.println();

            }

            else if(command==1)
            {
                System.out.print(command+" "+val+" ");
                tree.insert(val);
                System.out.println();

            }

            else if(command==2)
            {
                System.out.print(command+" "+val+" ");
                int z=tree.search(val);
                System.out.print(z);
                System.out.println();

            }

            else if(command==3)
            {
                System.out.print(command+" "+val+" ");
                int z=tree.getLessPriority2(val,tree.root);
                System.out.print(z);
                System.out.println();

            }

        }

    }
}


