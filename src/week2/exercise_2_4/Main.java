package week2.exercise_2_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sergey on 26.04.2017.
 */
public class Main {

    public static int[] parent = new int[100000];

    public static void makeSet(int i){
        parent[i] = i;
    }

    public static int findSet (int i){
        if (i == parent[i])
            return i;
        return parent[i] = findSet (parent[i]);
    }

    public static void unionSets (int a, int b)
    {
        //a = findSet(a);
        //b = findSet(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean check = true;

        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int d = scanner.nextInt();

        for (int i = 0; i < n; ++i)
            makeSet(i);

        for (int i = 0; i < e; ++i)
        {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            unionSets(x, y);
        }

        for (int i = 0; i < d; ++i)
        {

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (x == y)
            {
                check = false;
                break;
            }
            else if (findSet(x) == findSet(y))
            {
                check = false;
                break;
            }
        }
        System.out.println(check ? 1: 0);

    }


}
