package USACO;

import java.util.Scanner;

public class StampGrid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int time = 0; time < t; ++time) {
            int n = sc.nextInt();
            char[][] canvas = new char[n][n];
            for (int i = 0; i < n; ++i) {
                String line = sc.next();
                canvas[i] = line.toCharArray();
            }
            int k = sc.nextInt();
            char[][] stamp = new char[k][k];
            for (int i = 0; i < k; ++i) {
                String line = sc.next();
                stamp[i] = line.toCharArray();
            }
            char[][] res = new char[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; j++) {
                    res[i][j] = '.';
                }
            }
            int max = n - k + 1;
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < max; ++j) {
                    for (int l = 0; l < max; ++l) {
                        res = stamp(stamp, canvas, res, j, l);
                    }
                }
                stamp = spin(stamp);
            }
            boolean no = false;
            a:
            for (int i = 0; i < res.length; ++i) {
                for (int j = 0; j < res[0].length; ++j) {
                    if (res[i][j] != canvas[i][j]) {
                        System.out.println("NO");
                        no = true;
                        break a;
                    }
                }
            }
            if (!no) {
                System.out.println("YES");
            }
        }
    }

    public static char[][] stamp(char[][] stamp, char[][] canvas, char[][] res, int i, int j) {
        char[][] copy = new char[res.length][res[0].length];
        for (int k = 0; k < res.length; ++k) {
            System.arraycopy(res[k], 0, copy[k], 0, res.length);
        }
        for (int k = 0; k < stamp.length; ++k) {
            for (int l = 0; l < stamp[0].length; ++l) {
                if (stamp[k][l] == '*') {
                    if (canvas[i + k][j + l] == '.') {
                        return res;
                    }
                    copy[i + k][j + l] = '*';
                }
            }
        }
        return copy;
    }

    public static char[][] spin(char[][] stamp) {
        int len = stamp.length;
        char[][] res = new char[len][len];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                res[j][len - 1 - i] = stamp[i][j];
            }
        }
        return res;
    }
}
