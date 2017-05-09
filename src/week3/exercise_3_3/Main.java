package week3.exercise_3_3;

/**
 * Created by Sergey on 01.05.2017.
 */
import java.util.Scanner;

//Реализация алгоритма Рабина-Карпа
public class Main {

    private static final int p = 1_000_001;
    private static final int x = 31;


    private static long powMod(int base, int exp, int mod) {
        long x = 1;
        long y = base;

        while (exp > 0) {
            if(exp % 2 == 1) {
                x = ((x % mod) * (y % mod)) % mod;
            }
            y = ((y % mod) * (y % mod)) % mod;
            exp /= 2;
        }
        return x % mod;
    }

    private static int hash(String s) {
        char[] chars = s.toCharArray();
        long sumP = 0;

        for (int i = 0; i < chars.length; i++) {
            int charI = (int) chars[i];

            long add = ((charI % p) * powMod(x, i, p)) % p;

            sumP += add % p;
        }
        return (int) (sumP % p);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String pattern = sc.nextLine();
        String text = sc.nextLine();

        int lengthPattern = pattern.length();
        int lengthText = text.length();

        StringBuilder result = new StringBuilder();

        if (lengthPattern == lengthText) {
            if (text.equals(pattern)) {
                result.append(0);
            }
        } else {

            long[] arrayOfDegree = new long[lengthPattern];


            for (int i = 0; i < lengthPattern; i++) {
                arrayOfDegree[i] = powMod(x, i, p);
            }

            int hashPattern = hash(pattern);

            int[] arrayOfPatternsHashes = new int[lengthText - lengthPattern + 1];

            String window = text.substring(lengthText - lengthPattern, lengthText);

            arrayOfPatternsHashes[lengthText - lengthPattern] = hash(window);


            for (int i = lengthText - 2; i >= lengthPattern - 1; i--) {

                int charPredLast = (int) text.charAt(i + 1);
                int charLast = (int) text.charAt(i - lengthPattern + 1);

                long hash_i = arrayOfPatternsHashes[i - lengthPattern + 2];
                hash_i -= ((charPredLast % p) * arrayOfDegree[lengthPattern - 1]) % p;
                hash_i = (hash_i + p) % p;
                hash_i = ((hash_i % p) * (x % p)) % p;
                hash_i = ((hash_i % p) + (charLast % p)) % p;

                arrayOfPatternsHashes[i - lengthPattern + 1] = (int) hash_i;
            }

            for (int i = 0; i <= lengthText - lengthPattern; i++) {
                if (arrayOfPatternsHashes[i] == hashPattern) {
                    if (text.substring(i, i + lengthPattern).equals(pattern)) {
                        result.append(i);
                        result.append(" ");
                    }
                }
            }
        }

        System.out.println(result.toString().trim());

    }
}
