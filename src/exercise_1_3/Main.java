package exercise_1_3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Sergey on 23.04.2017.
 */
public class Main {

    public static class Packet{

        private int arrival;
        private int duration;

        public Packet(int arrival, int duration) {
            this.arrival = arrival;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return Integer.toString(arrival-duration).trim();
        }

        public int getArrival() {
            return arrival;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bufferSize = scanner.nextInt();
        int numOfPackets = scanner.nextInt();

        int begin = 0;


        ArrayDeque<Packet> buffer = new ArrayDeque<>();

        for (int i = 0;i<numOfPackets;i++){

            int arrive = scanner.nextInt();
            int duration = scanner.nextInt();

            while(!buffer.isEmpty() && buffer.peekFirst().getArrival() <= arrive) //print buffer
            {
                System.out.println(buffer.peekFirst());
                if (buffer.peekFirst().getArrival() >= 0) bufferSize ++;
                buffer.removeFirst();
            }

            if(bufferSize <= 0)
            {
                buffer.addLast(new Packet(-1,0));
            }
            else
            {
                if(begin <= arrive)
                    begin = arrive + duration;
                else
                    begin += duration;

                buffer.addLast(new Packet(begin, duration));
                --bufferSize;
            }
        }
        while(!buffer.isEmpty())
        {
            System.out.println(buffer.removeFirst());

        }

    }
}
