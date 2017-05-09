package week3.exercise_3_2;

/**
 * Created by Sergey on 01.05.2017.
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


class Main{

    public static long p = 1000000007;
    public static int x = 263;

    public static long mod(long a,long x){
        return ((a % x)+x)%x;
    }

    public static int hash(String s,int osn){

        if (osn==1)
            return 0;

        int step = 0;
        long h = 0;
        long xx = 1;
        for (char c:s.toCharArray()) {
            //long curSum = Character.getNumericValue(c) * xx;
            long curSum = c * xx;
            h+=curSum;
            h=mod(h,p);
            xx*=x;
            xx=mod(xx,p);
        }
        h = mod(h,osn);
        return (int)h;
    }
    public static void main(String... args){

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<String>[] lists = (LinkedList<String>[])new LinkedList[n];
        for (int i=0;i<n;i++)
            lists[i] = new LinkedList<String>();

        for (int i =0;i<=m;i++){
            String command = scanner.nextLine();
            if (command.startsWith("add")){
                String[] param = command.split(" ");
                int ind = hash(param[1],n);
                if (!lists[ind].contains(param[1])){
                    lists[ind].add(0,param[1]);
                }
            }
            if (command.startsWith("find")){
                String[] param = command.split(" ");
                int ind = hash(param[1],n);
                if (!lists[ind].contains(param[1]))
                    System.out.println("no");
                else
                    System.out.println("yes");

            }
            if (command.startsWith("del")){

                String[] param = command.split(" ");
                int ind = hash(param[1],n);
                if (lists[ind].contains(param[1])){
                    lists[ind].remove(param[1]);
                }

            }

            if (command.startsWith("check")){

                String[] param = command.split(" ");
                int ind = Integer.parseInt(param[1]);

                for (String s:lists[ind])
                    System.out.print(s + " ");
                System.out.println();
            }

        }



    }
}