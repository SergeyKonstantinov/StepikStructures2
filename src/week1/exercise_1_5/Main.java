package week1.exercise_1_5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by Sergey on 18.04.2017.
 */
public class Main {

    public static void addToDeq(Deque<Integer> q,int added_element){
        while (!q.isEmpty() && q.peekLast() < added_element)
            q.pollLast();
        q.addLast(added_element);
    }

    public static void removeFromDeq(Deque<Integer> q,int removed_element){
        if (!q.isEmpty() && q.peekFirst() == removed_element)
            q.poll();
    }

    public static int getMax(Deque<Integer> q){
        return q.peekFirst();
    }

    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] a = new int[n];

        for (int i =0;i<n;i++)
            a[i] = scanner.nextInt();

        int k = scanner.nextInt();
        for (int i = 0;i<k;i++)
            addToDeq(deque,a[i]);
        System.out.print(getMax(deque)+" ");

        for (int i = k;i<n;i++){

            addToDeq(deque,a[i]);
            removeFromDeq(deque,a[i-k]);
            System.out.print(getMax(deque)+" ");
        }

  }
}
