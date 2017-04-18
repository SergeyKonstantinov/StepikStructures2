package exercise_1_4;

/**
 * Created by Sergey on 18.04.2017.
 */
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = Integer.MIN_VALUE;
        for (int i=0;i<=n;i++){

            String command = scanner.nextLine();
            //System.out.println(command);
            if (command.startsWith("push")){
                String mas[] = command.split(" ");
                int data = Integer.parseInt(mas[1]);
                stack.push(data);
                if (data>max)
                    max=data;
                maxStack.push(max);
            }
            if (command.equals("pop")){
                stack.pop();
                max = maxStack.pop();
            }
            if (command.equals("max")){
                System.out.println(maxStack.peek());
            }
        }

    }
}