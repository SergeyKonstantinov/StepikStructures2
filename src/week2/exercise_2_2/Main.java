package week2.exercise_2_2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Sergey on 26.04.2017.
 */
public class Main {
    public static class Pair{

        public long time;
        public int proc;

        @Override
        public String toString() {
            return "Pair{" +
                    "time=" + time +
                    ", proc=" + proc +
                    '}';
        }

        public Pair(long time, int proc) {
            this.time = time;
            this.proc = proc;
        }

    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Pair> heap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (Long.compare(o1.time,o2.time)==0){
                        return Integer.compare(o1.proc,o2.proc);}
                    else
                        return Long.compare(o1.time,o2.time);
            }
        });

        int proc = scanner.nextInt();
        int works = scanner.nextInt();

        if (proc>=works){
            for (int i = 0;i<works;i++){
                int w = scanner.nextInt();
                System.out.println(i+" "+0);
            }
        }else {
            for (int i = 0;i<proc;i++){
                long w = scanner.nextLong();
                heap.add(new Pair(w,i));
                System.out.println(i+" "+0);
            }

            for (int i = proc;i<works;i++){
                long w = scanner.nextLong();
                Pair top = heap.poll();
                System.out.println(top.proc+" "+top.time);
                heap.add(new Pair(w+top.time,top.proc));
            }
        }
    }
}
