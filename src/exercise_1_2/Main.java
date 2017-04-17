package exercise_1_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Sergey on 17.04.2017.
 */
public class Main {

    public static class Pair implements Comparable<Pair>{
        public int ind;
        public int data;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (ind != pair.ind) return false;
            return data == pair.data;

        }

        @Override
        public int hashCode() {
            int result = ind;
            result = 31 * result + data;
            return result;
        }

        @Override

        public int compareTo(Pair o) {
            if (o.data==data)
                return Integer.compare(ind,o.ind);
            else
                return Integer.compare(data,o.data);
        }

        public Pair(int ind, int data) {
            this.ind = ind;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "ind=" + ind +
                    ", data=" + data +
                    '}';
        }
    }

    public static class Node{

        public int data;


        public ArrayList<Node> node = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }

        public void printTree(Node root,int level){
            for (int i=0;i<level;i++)
                System.out.print("-----");
            System.out.println(root.data);
            for (Node child:root.node)
                printTree(child,level+1);
        }

        public int getHeight(Node root){
            int height = 1;
            for (Node child:root.node){
                height = Math.max(height,1+getHeight(child));
            }
            return height;
        }



        public void add(Node root,ArrayList<Pair> nodes){
            if (nodes.size()==0) return;
            ArrayList<Pair> copy = new ArrayList<>();
            for (Pair pair:nodes) {

                if (pair.data == root.data) {
                    root.node.add(new Node(pair.ind));
                    copy.add(pair);
                }

            }

            if (copy.size()==0)
                return;

            for (Pair pair:copy)
                nodes.remove(pair);

            for (Node child:root.node)
                add(child,nodes);

        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", node=" + node +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        ArrayList<Pair> arrayOfNodes = new ArrayList<>();

        Pair root = null;

        for (int i=0;i<n;i++)
        {
            int data = scanner.nextInt();
            Pair pair = new Pair(i,data);
            if (data==-1){
                root = pair;
            }
            arrayOfNodes.add(pair);
        }

        //Collections.sort(arrayOfNodes);

        //Pair root = arrayOfNodes.remove(0);

        Node tree = new Node(root.ind);
        arrayOfNodes.remove(root);

        tree.add(tree,arrayOfNodes);
        //tree.printTree(tree,0);
        System.out.println(tree.getHeight(tree));





    }
}
