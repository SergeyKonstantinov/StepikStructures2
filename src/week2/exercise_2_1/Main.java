package week2.exercise_2_1;

/**
 * Created by Sergey on 25.04.2017.
 */
import java.util.*;
public class Main{

    public static int changes = 0;
    public static List<String> arr = new ArrayList<>();

    public static void heapify(int i,int[] heap,int heapSize)
    {
        int leftChild;
        int rightChild;
        int largestChild;


        for (; ; )
        {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < heapSize && heap[leftChild] < heap[largestChild])
            {
                largestChild = leftChild;
            }

            if (rightChild < heapSize && heap[rightChild] < heap[largestChild])
            {
                largestChild = rightChild;
            }

            if (largestChild == i)
            {
                break;
            }

            arr.add(""+i+"  "+largestChild);
            int temp = heap[i];
            heap[i] = heap[largestChild];
            heap[largestChild] = temp;
            i = largestChild;
            changes++;

        }

    }

    //-----------------



    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heap = new int[n];

        for (int i = 0;i<n;i++){
            int val = scanner.nextInt();
            heap[i] = val;
        }


        for (int i = (n / 2)-1; i >= 0; i--)
        {
            heapify(i,heap,n);
        }

        System.out.println(changes);
        for (String s:arr)
            System.out.println(s);

    }
}