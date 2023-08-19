package USACO;

import java.io.*;

public class AirCownditioningII {

    public static int n;
    public static int m;
    public static int[][] cows;
    public static int[][] ac;
    public static StreamTokenizer in;
    public static int[] occupied;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        m = nextInt();
        cows = new int[n][3];
        ac = new int[m][4];
        for (int i = 0; i < n; ++i) {
            cows[i][0] = nextInt();
            cows[i][1] = nextInt();
            cows[i][2] = nextInt();
        }
        for (int i = 0; i < m; ++i) {
            ac[i][0] = nextInt();
            ac[i][1] = nextInt();
            ac[i][2] = nextInt();
            ac[i][3] = nextInt();
        }
        occupied = new int[101];
        for (int[] cow : cows) {
            for (int j = cow[0]; j <= cow[1]; ++j) {
                occupied[j] = cow[2];
            }
        }
        int min = Integer.MAX_VALUE;
        int len = (int) Math.pow(2, m);
        for (int i = 1; i < len; ++i) {
            boolean[] on = new boolean[m];
            char[] a = Integer.toBinaryString(i).toCharArray();
            for (int j = 0; j < a.length; ++j) {
                on[j + m - a.length] = a[j] == '1';
            }
            int cost = 0;
            for (int j = 0; j < m; ++j) {
                if (on[j]) {
                    cost += ac[j][3];
                }
            }
            if (cost < min && valid(on)) {
                min = cost;
            }
        }
        System.out.println(min);
    }

    public static boolean valid(boolean[] on) {
        int[] cool = new int[101];
        for (int i = 0; i < m; ++i) {
            if (on[i]) {
                for (int j = ac[i][0]; j < ac[i][1] + 1; ++j) {
                    cool[j] += ac[i][2];
                }
            }
        }
        for (int i = 0; i < 101; ++i) {
            if (occupied[i] > cool[i]) {
                return false;
            }
        }
        return true;
    }
}
