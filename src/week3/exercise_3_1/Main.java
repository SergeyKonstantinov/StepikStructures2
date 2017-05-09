package week3.exercise_3_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Sergey on 01.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String,String> book = new HashMap<>();

        for (int i =0;i<=n;i++){
            String command = scanner.nextLine();
            if (command.startsWith("add")){
                String[] param = command.split(" ");
                book.put(param[1],param[2]);
            }
            if (command.startsWith("find")){
                String[] param = command.split(" ");
                String person = book.get(param[1]);
                if (person==null)
                    System.out.println("not found");
                else
                    System.out.println(person);
            }
            if (command.startsWith("del")){
                String[] param = command.split(" ");
                book.remove(param[1]);
            }

        }

    }
}
