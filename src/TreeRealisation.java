import java.io.*;
import java.util.*;

class Tree <T extends Comparable<T>>{
    public class Node {
        public T key;
        public Node leftC;
        public Node rightC;


        public void showNode() {
            System.out.print('(');
            System.out.print(key.toString());
            System.out.print(") ");
        }
    }
    private Node root;

    public Tree() {
        root = null;
    }

    public Node returnRoot() {
        return root;
    }

    public Node find(T key) {
        Node current = root;
        while (current.key.compareTo(key)!=0 ) {
            if (key.compareTo(current.key)<0)
                current = current.leftC;
            else
                current = current.rightC;
            if (current == null)
                return null;
        }
        return current;
    }

    public void insert(T key) {
        Node insNode = new Node();
        insNode.key = key;
        if (root == null)
            root = insNode;
        else {
            Node current = root, parent;
            while (true) {
                parent = current;
                if (key.compareTo(current.key)<0) {
                    current = current.leftC;
                    if (current == null) {
                        parent.leftC = insNode;
                        return;
                    }
                } else {
                    current = current.rightC;
                    if (current == null) {
                        parent.rightC = insNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(T key) {
        Node current = root, parent = root;
        boolean isLeftC = true;
        while (current.key.compareTo(key)!=0 ) {
            parent = current;
            if (key.compareTo(current.key)<0) {
                isLeftC = true;
                current = current.leftC;
            } else {
                isLeftC = false;
                current = current.rightC;
            }
            if (current == null)
                return false;
        }
        if (current.leftC == null && current.rightC == null) {
            if (current == root)
                root = null;
            else if (isLeftC)
                parent.leftC = null;
            else
                parent.rightC = null;
        } else if (current.rightC == null) {
            if (current == root)
                root = null;
            else if (isLeftC)
                parent.leftC = current.leftC;
            else
                parent.rightC = current.leftC;
        } else if (current.leftC == null) {
            if (current == root)
                root = null;
            else if (isLeftC)
                parent.leftC = current.rightC;
            else
                parent.rightC = current.rightC;
        } else {
            Node nextCurr = getNextVal(current);
            if (current == root)
                root = nextCurr;
            else if (isLeftC)
                parent.leftC = nextCurr;
            else
                parent.rightC = nextCurr;

        }
        return true;
    }

    private Node getNextVal(Node del) {
        Node nextParent = del, next = del, current = del.rightC;
        while (current != null) {
            nextParent = next;
            next = current;
            current = current.leftC;
        }
        if (next != del.rightC) {
            nextParent.leftC = next.rightC;
            next.rightC = del.rightC;
        }
        return next;
    }

    public void leftRootRight(Node n) {
        if (n != null) {
            leftRootRight(n.leftC);
            System.out.print(n.key + " ");
            //n.showNode();
            leftRootRight(n.rightC);
        }
    }

    public void leftRightRoot(Node n) {
        if (n != null) {
            leftRightRoot(n.leftC);
            leftRightRoot(n.rightC);
            System.out.print(n.key + " ");
            //n.showNode();
        }
    }

    public void rootLeftRight(Node n) {
        if (n != null) {
            System.out.print(n.key + " ");
            //n.showNode();
            rootLeftRight(n.leftC);
            rootLeftRight(n.rightC);
        }
    }
}
