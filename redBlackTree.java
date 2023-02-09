// Implementing Red-Black Tree in Java

class Node {
    int value;
    rbNode parent;
    rbNode left;
    rbNode right;
    int color;
}

public class redBlackTree {
    private rbNode root;
    private rbNode TNULL;

    // Preorder
    private void preOrderHelper(rbNode node) {
        if (node != TNULL) {
            System.out.print(node.value + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Inorder
    private void inOrderHelper(rbNode node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.value + " ");
            inOrderHelper(node.right);
        }
    }

    // Post order
    private void postOrderHelper(rbNode node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.value + " ");
        }
    }

    // Search the tree
    private rbNode searchTreeHelper(rbNode node, int key) {
        if (node == TNULL || key == node.value) {
            return node;
        }

        if (key < node.value) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    // Balance the tree after deletion of a node
    private void fixDelete(rbNode x) {
        rbNode s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void rbTransplant(rbNode u, rbNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(rbNode node, int key) {
        rbNode z = TNULL;
        rbNode x, y;
        while (node != TNULL) {
            if (node.value == key) {
                z = node;
            }

            if (node.value <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
    }

    // Balance the node after insertion
    private void fixInsert(rbNode k) {
        rbNode u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void printHelper(rbNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.value + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public redBlackTree() {
        TNULL = new rbNode();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    public void preorder() {
        preOrderHelper(this.root);
    }

    public void inorder() {
        inOrderHelper(this.root);
    }

    public void postorder() {
        postOrderHelper(this.root);
    }

    public rbNode searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public rbNode minimum(rbNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public rbNode maximum(rbNode node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public rbNode successor(rbNode x) {
        if (x.right != TNULL) {
            return minimum(x.right);
        }

        rbNode y = x.parent;
        while (y != TNULL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public rbNode predecessor(rbNode x) {
        if (x.left != TNULL) {
            return maximum(x.left);
        }

        rbNode y = x.parent;
        while (y != TNULL && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    public void leftRotate(rbNode x) {
        rbNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }


    /*
    * int CountGreater(struct Node* root, int x)
{
    int res = 0;

    // Search for x. While searching, keep
    // updating res if x is greater than
    // current node.
    while (root != NULL) {

        int desc = (root->right != NULL) ?
                root->right->desc : -1;

        if (root->key > x) {
            res = res + desc + 1 + 1;
            root = root->left;
        } else if (root->key < x)
            root = root->right;
        else {
            res = res + desc + 1;
            break;
        }
    }
    return res;
}*/

    public void rightRotate(rbNode x) {
        rbNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        rbNode node = new rbNode();
        node.parent = null;
        node.value = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1;

        rbNode y = null;
        rbNode x = this.root;

        while (x != TNULL) {
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
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
        System.out.println("successful insertion of "+key);
    }

    public rbNode getRoot() {
        return this.root;
    }

    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public static void main(String[] args) {
        redBlackTree bst = new redBlackTree();
        bst.insert(55);
        bst.insert(40);
        bst.insert(65);
        bst.insert(60);
        bst.insert(75);
        bst.insert(57);
        bst.printTree();

        System.out.println("\nAfter deleting:");
        bst.deleteNode(40);
        bst.printTree();
    }
}
