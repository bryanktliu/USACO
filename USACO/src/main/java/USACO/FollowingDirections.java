package USACO;

import java.io.*;

public class FollowingDirections {

    public static int n;
    public static char[][] arr;
    public static int[][] dp;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new char[n + 1][n + 1];
        dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            String line = next();
            for (int j = 0; j < line.length(); ++j) {
                arr[i][j] = line.charAt(j);
            }
            dp[i][n] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            dp[n][i] = nextInt();
        }
        int cost = 0;
        for (int i = 0; i < n; ++i) {
            cost += fill(n, i);
            cost += fill(i, n);
        }
        System.out.println(cost);
        int q = nextInt();
        for (int i = 0; i < q; ++i) {
            int posy = nextInt() - 1;
            int posx = nextInt() - 1;
            if (arr[posy][posx] == 'D') {
                arr[posy][posx] = 'R';
                cost += fill(posy, posx + 1);
            } else {
                arr[posy][posx] = 'D';
                cost += fill(posy + 1, posx);
            }
            System.out.println(cost);
        }
    }

    public static int fill(int i, int j) {
        int total = 0;
        if (i > 0 && arr[i - 1][j] == 'D') {
            total += dp[i][j] - dp[i - 1][j];
            dp[i - 1][j] = dp[i][j];
            total += fill(i - 1, j);
        }
        if (j > 0 && arr[i][j - 1] == 'R') {
            total += dp[i][j] - dp[i][j - 1];
            dp[i][j - 1] = dp[i][j];
            total += fill(i, j - 1);
        }
        return total;
    }
}
