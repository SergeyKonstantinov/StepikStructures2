package week2.exercise_2_3;

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
        a = findSet(a);
        b = findSet(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) {
        int n,m,max;
        max=0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        List<Integer> records = new ArrayList<>(1000);
        for (int i = 0; i < n; ++i)
        {
            int rec = scanner.nextInt();
            records.add(rec);
            if (rec > max) max = rec;
            makeSet(i+1);
        }

        //----------

        for (int i = 1; i <= m; ++i)
        {
            int d = scanner.nextInt();
            int s = scanner.nextInt();

            if (findSet(d) != findSet(s))
            {
                int k = findSet(d)-1;
                int temp = records.get(k);
                temp+=records.get(findSet(s)-1);
                records.set(k,temp);
                unionSets (d, s);
                int maybeMax = records.get(findSet(d)-1);
                if (maybeMax > max) max = maybeMax;
            }

            System.out.println(max);
        }
    }


}
