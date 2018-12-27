import java.io.*;
import java.util.*;

class Car implements Comparable<Car>{
    private int number;
    private String owner;

    public Car() {
        owner = null;
        number = -1;
    }

    public Car(int num, String own) {
        owner = own;
        number = num;
    }

    public int getNumber() {
        return number;
    }

    public String gerOwner() {
        return owner;
    }
    public int compareTo(Car a){
        return owner.compareTo(a.owner);
    }
    public String toString(){
        return owner+"\t"+number;
    }
}



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

    /**public void showTree() {
     Stack st = new Stack();
     st.push(root);
     boolean isREmpty = false;
     int tabs = 32;
     System.out.println("\n________________________________");
     while (!isREmpty) {
     Stack st1 = new Stack();
     isREmpty = true;
     for (int i = 0; i < tabs; i++)
     System.out.print(' ');
     while (!st.isEmpty()) {
     Node temp = (Node) st.pop();
     if (temp != null) {
     System.out.print(temp.key);
     st1.push(temp.leftC);
     st1.push(temp.rightC);
     if (temp.leftC != null || temp.rightC != null)
     isREmpty = false;
     }
     else {
     System.out.print("(==)");
     st1.push(null);
     //st1.push(null);
     }
     for (int i = 0; i < tabs * 2 - 2; i++)
     System.out.print(' ');
     System.out.println();
     tabs /= 2;
     while (!st1.isEmpty())
     st.push(st1.pop());
     }
     }
     System.out.println("\n________________________________");
     }**/
}

public class TreeRealisation {
    public static void main(String[] args) {

        Tree<Double> aTree = new Tree<>();
        aTree.insert(3.14);
        aTree.insert(43.5);
        aTree.insert(100.0);
        aTree.insert( -16.0);
        aTree.insert(-22.0);
        aTree.insert( 0.1);
        aTree.insert( 5.183);

        aTree.insert(0.0);

        //aTree.showTree();
        try {
            aTree.find(-16.1).showNode();
        } catch (NullPointerException e) {
            System.out.println("no such element");
        }

        Tree<Car> carTree = new Tree<>();
        Car citr = new Car(1985, "Jane");
        Car audi = new Car(5009, "Statham");
        Car tesla = new Car(2243, "Musk");
        Car ford = new Car(9120, "H.Ford");

        carTree.insert(citr);
        carTree.insert(audi);
        carTree.insert(tesla);
        carTree.insert(ford);

        System.out.println("\nleft, root, right:");
        aTree.leftRootRight(aTree.returnRoot());
        System.out.println("\nroot, left, right:");
        aTree.rootLeftRight(aTree.returnRoot());
        System.out.println("\nleft, right, root:");
        aTree.leftRightRoot(aTree.returnRoot());
        System.out.println();

        System.out.println("\nleft, root, right:");
        carTree.leftRootRight(carTree.returnRoot());
        System.out.println("\nroot, left, right:");
        carTree.rootLeftRight(carTree.returnRoot());
        System.out.println("\nleft, right, root:");
        carTree.leftRightRoot(carTree.returnRoot());
        System.out.println();


    }
}

