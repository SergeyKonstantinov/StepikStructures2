package exercise_1_1;

import java.util.Stack;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        int k = 1;

        for (char c:line.toCharArray()){

            if (c=='(' || c=='[' || c=='{')
            {stack.push(c);stack2.push(k);k++;}
            else{

                if (c!=')' && c!=']' && c!='}')
                {
                    k++;
                    continue;
                }

                if (stack.empty())
                {

                    stack.push(c);
                    stack2.push(k);
                    k++;
                    break;
                }

                char pred = stack.pop();
                stack2.pop();

                if ((c==')' && pred=='('))
                    k++;
                else if ((c==']' && pred=='['))
                    k++;
                else if ((c=='}' && pred=='{'))
                    k++;
                else
                {

                    stack.push(c);
                    stack2.push(k);
                    k++;
                    break;

                }
            }
        }

        if (stack.empty())
            System.out.println("Success");
        else
            System.out.println(stack2.pop());
    }

}
