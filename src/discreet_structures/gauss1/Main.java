package discreet_structures.gauss1;

/**
 * Created by Sergey on 30.04.2017.
 */


/**
 * Created by Sergey on 30.04.2017.
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static BigInteger determinant(final double[][] matr) {

        int accuracy = 20;

        BigDecimal EPS = BigDecimal.valueOf(0.00000000001);

        int n = matr.length;
        BigDecimal[][] a = new BigDecimal[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) {
                a[i][j] = new BigDecimal(matr[i][j]);
                a[i][j].setScale(accuracy, BigDecimal.ROUND_HALF_UP);
            }

        BigDecimal det = new BigDecimal(1.0);
        det.setScale(accuracy, BigDecimal.ROUND_HALF_UP);

        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j)
                if (a[j][i].abs().compareTo(a[k][i].abs()) > 0)
                    k = j;
            if (a[k][i].abs().compareTo(EPS) < 0) {
                det = new BigDecimal(0.0);
                det.setScale(accuracy, BigDecimal.ROUND_HALF_UP);
                break;
            }
            BigDecimal[] tmp = a[i];
            a[i] = a[k];
            a[k] = tmp;

            if (i != k)
                det = det.divide(new BigDecimal(-1), accuracy, BigDecimal.ROUND_HALF_UP);
            det = det.multiply(a[i][i]);
            for (int j = i + 1; j < n; ++j)
                a[i][j] = a[i][j].divide(a[i][i], accuracy, BigDecimal.ROUND_HALF_UP);
            for (int j = 0; j < n; ++j)
                if (j != i && a[j][i].abs().compareTo(EPS) > 0)
                    for (int kk = i + 1; kk < n; ++kk) {
                        BigDecimal aikji = new BigDecimal(1.0);
                        aikji.setScale(accuracy, BigDecimal.ROUND_HALF_UP);
                        aikji = aikji.multiply(a[i][kk]);
                        aikji = aikji.multiply(a[j][i]);
                        aikji = aikji.multiply(new BigDecimal(-1));
                        a[j][kk] = a[j][kk].add(aikji);
                    }
        }

        det = det.abs();
        det = det.add(new BigDecimal(0.00001));
        return det.abs().toBigInteger();

    }

    public static double[] gauss(double[][] a, double[] b) {
        int n = a.length;
        for (int row = 0; row < n; row++) {
            int best = row;
            for (int i = row + 1; i < n; i++)
                //if (Math.abs(a[best][row]) < Math.abs(a[i][row]))
                //    best = i;
                if (Double.compare(a[best][row],a[i][row])==-1)
                    best = i;
            double[] tt = a[row];
            a[row] = a[best];
            a[best] = tt;
            double t = b[row];
            b[row] = b[best];
            b[best] = t;
            for (int i = row + 1; i < n; i++)
                a[row][i] /= a[row][row];
            b[row] /= a[row][row];
            // a[row][row] = 1;
            for (int i = 0; i < n; i++) {
                double x = a[i][row];
                if (i != row && x != 0) {
                    // row + 1 instead of row is an optimization
                    for (int j = row + 1; j < n; j++)
                        a[i][j] -= a[row][j] * x;
                    b[i] -= b[row] * x;
                }
            }
        }

        return b;
    }

    public static void main(String... args){
        //int[][] matr = {{4,7,9},{2,8,1},{1,9,3}}; YES
        //int[][] matr = {{1,2,0},{3,1,0},{4,4,0}}; INF
        //int[][] matr = {{1,2,1},{3,6,4},{2,4,3}}; NO
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        double[][] matr = new double[m][m];
        double[] b = new double[m];

        for (int i = 0;i<m;i++)
            Arrays.fill(matr[i],0);
        Arrays.fill(b,0);

        if (n>m){

            b = new double[m];

            for (int row = 0;row<m;row++) {
                for (int col = 0; col < m; col++)
                    matr[row][col] = scanner.nextDouble();
                b[row] = scanner.nextDouble();
            }

            for (int row = m;row<n;row++) {
                for (int col = 0; col < m; col++)
                    matr[m-1][col] += scanner.nextDouble();
                b[m-1] += scanner.nextDouble();
            }

        }
        else{

            for (int row = 0;row<n;row++) {
                for (int col = 0; col < m; col++)
                    matr[row][col] = scanner.nextDouble();
                b[row] = scanner.nextDouble();
            }

            for (int row = m;row<n;row++) {
                b[row] = 0;
            }

        }


        BigInteger det = determinant(matr);


        double[] ans = gauss(matr,b);



        StringBuilder sb = new StringBuilder();
        boolean isNO = false;
        boolean isINF = false;
        for (int d=0;d<m;d++){
            double x = ans[d];
            sb.append(ans[d]+" ");
            if (Double.compare(x,Double.NaN)==0){
                isINF = true;
                break;
            }
            if (Double.compare(x,Double.NEGATIVE_INFINITY)==0 || Double.compare(x,Double.POSITIVE_INFINITY)==0){
                isNO = true;
                break;
            }
        }

        for (int i =0;i<matr.length;i++) {
            boolean is0 = true;
            for (int j = 0; j < matr[0].length; j++){
                if (Double.compare(matr[i][j],0)!=0)
                    is0 = false;
            }
            if (Double.compare(ans[i],Double.NaN)==0)
                continue;
            if (Double.compare(ans[i],Double.NEGATIVE_INFINITY)==0)
                continue;
            if (Double.compare(ans[i],Double.POSITIVE_INFINITY)==0)
                continue;
            if (is0 && Double.compare(ans[i],0)!=0)
            {
                isNO = true;
                break;
            }
        }



        if (n>m)
            System.out.println("NO");
        else
        {

            if (isNO)
                System.out.println("NO");
            else if (isINF)
                if (det.intValue()==0)
                    System.out.println("INF");
                else
                    System.out.println("NO");
            else {
                System.out.println("YES");
                System.out.println(sb.toString());
            }}

    }

}

