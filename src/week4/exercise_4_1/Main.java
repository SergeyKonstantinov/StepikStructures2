package week4.exercise_4_1;

import java.util.Scanner;

/**
 * Created by Sergey on 09.05.2017.
 */
public class Main {

    public static class Node{

        public long data;
        public Node left;
        public Node right;

        public void setNode(long data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node() {
        }
    }

    public static void printInOrder(Node root){
        if (root!=null){
            printInOrder(root.left);
            System.out.print(root.data+ " ");
            printInOrder(root.right);
        }
    }

    public static void printPreOrder(Node root){
        if (root!=null){
            System.out.print(root.data+ " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public static void printPostOrder(Node root){
        if (root!=null){
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(root.data+ " ");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Node[] nodes = new Node[n];

        for (int i = 0; i<n; i++){
            nodes[i] = new Node();
        }

        for (int i = 0; i<n; i++){

            long key = scanner.nextLong();
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            Node leftNode = null;
            Node rightNode = null;

            if (left>-1)
                leftNode = nodes[left];

            if (right>-1)
                rightNode = nodes[right];

            nodes[i].setNode(key,leftNode,rightNode);

        }


        printInOrder(nodes[0]);
        System.out.println();

        printPreOrder(nodes[0]);
        System.out.println();

        printPostOrder(nodes[0]);

    }
}
