package week1.exercise_1_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Sergey on 17.04.2017.
 */
public class Main {

    public static void printTree(HashMap<Integer,ArrayList<Integer>> tree){
        for (Map.Entry entry : tree.entrySet()) {
            System.out.print("Key: " + entry.getKey() + " Value: [");

            ArrayList<Integer> value = (ArrayList) entry.getValue();

            for (int i:value)
                System.out.print(i+", ");

            System.out.println("]");
        }
    }

    public static int getHeight(HashMap<Integer,ArrayList<Integer>> tree,int root){
        int height = 1;
        ArrayList<Integer> childs = tree.get(root);
        for (int c:childs)
            height = Math.max(height,1+getHeight(tree,c));
        return height;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] mas = new int[n];

        HashMap<Integer,ArrayList<Integer>> tree = new HashMap<>();

        Integer root = 0;

        for (int i=0;i<n;i++)
        {
            int data = scanner.nextInt();
            mas[i] = data;
            if (data==-1){
                root = i;
            }

            tree.put(i,new ArrayList<Integer>());
        }

        for (int i=0;i<n;i++)
        {
            if (mas[i]==-1)
                continue;

            ArrayList<Integer> node = tree.get(mas[i]);
            node.add(i);
        }

        //printTree(tree);
        System.out.println(getHeight(tree,root));


    }
}
