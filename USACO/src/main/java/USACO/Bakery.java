package USACO;

import java.util.Scanner;

public class Bakery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int testcases = 0; testcases < t; ++testcases) {
            int n = sc.nextInt();
            int tC = sc.nextInt();
            int tM = sc.nextInt();
            long[][] order = new long[n][3];
            for (int i = 0; i < n; ++i) {
                order[i][0] = sc.nextInt();
                order[i][1] = sc.nextInt();
                order[i][2] = sc.nextLong();
            }
            int minC = 0;
            int minM = 0;
            for (int i = 0; i < n; ++i) {
                minC = Math.max(minC, (int) (tC - ((double) order[i][2] / order[i][0])));
                minM = Math.max(minM, (int) (tM - ((double) order[i][2] / order[i][1])));
            }
            int minTotal = tC + tM;
            for (int i = minC; i <= tC; ++i) {
                if (i + minM > minTotal) {
                    break;
                }
                for (int j = minM; j <= tM; ++j) {
                    if (i + j > minTotal) {
                        break;
                    }
                    boolean work = true;
                    for (int k = 0; k < n; ++k) {
                        if (order[k][0] * (tC - i) + order[k][1] * (tM - j) > order[k][2]) {
                            work = false;
                            break;
                        }
                    }
                    if (work) {
                        minTotal = Math.min(minTotal, i + j);
                    }
                }
            }
            System.out.println(minTotal);
        }
    }
}
