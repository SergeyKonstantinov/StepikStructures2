package week4.exercise_4_2;

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

    public static boolean checkTree(Node root,Long min, Long max){

        if (root==null)
            return true;

        if (( min != null && root.data <= min) || (max != null && root.data > max )) {
            return false;
        }

        if ( !checkTree(root.left , min, root.data ) || !checkTree (root.right, root.data, max)) {
            return false;
        }

        return true;

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
        if (n==0)
            System.out.println("CORRECT");
        else if (checkTree(nodes[0],null,null))
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");

    }
}
