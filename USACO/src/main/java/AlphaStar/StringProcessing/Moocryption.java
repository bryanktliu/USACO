package AlphaStar.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Moocryption {

    public static int n;
    public static int m;
    public static char[][] arr;
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
        m = nextInt();
        arr = new char[n][];
        for (int i = 0; i < n; ++i) {
            arr[i] = next().toCharArray();
        }
        int max = 0;
        for (int i = 'A'; i <= 'Z'; ++i) {
            char M = (char) (i + 'A');
            if (M == 'M') {
                continue;
            }
            for (int j = 'A'; j <= 'Z'; ++j) {
                char O = (char) (j + 'A');
                if (i == j || O == 'O') {
                    continue;
                }
                int count = 0;
                for (int k = 0; k < n; ++k) {
                    for (int l = 0; l < m; ++l) {
                        if (arr[k][l] == M) {
                            if (left(l, k, O)) {
                                ++count;
                            }
                            if (right(l, k, O)) {
                                ++count;
                            }
                            if (up(l, k, O)) {
                                ++count;
                            }
                            if (down(l, k, O)) {
                                ++count;
                            }
                            if (topLeft(l, k, O)) {
                                ++count;
                            }
                            if (topRight(l, k, O)) {
                                ++count;
                            }
                            if (bottomLeft(l, k, O)) {
                                ++count;
                            }
                            if (bottomRight(l, k, O)) {
                                ++count;
                            }
                        }
                    }
                }
                max = Math.max(max, count);
            }
        }
        System.out.println(max);
    }

    public static boolean left(int x, int y, char O) {
        if (x < 2) {
            return false;
        }
        return arr[y][x - 1] == O && arr[y][x - 2] == O;
    }

    public static boolean right(int x, int y, char O) {
        if (x > m - 3) {
            return false;
        }
        return arr[y][x + 1] == O && arr[y][x + 2] == O;
    }

    public static boolean up(int x, int y, char O) {
        if (y < 2) {
            return false;
        }
        return arr[y - 1][x] == O && arr[y - 2][x] == O;
    }

    public static boolean down(int x, int y, char O) {
        if (y > n - 3) {
            return false;
        }
        return arr[y + 1][x] == O && arr[y + 2][x] == O;
    }

    public static boolean topLeft(int x, int y, char O) {
        if (y < 2 || x < 2) {
            return false;
        }
        return arr[y - 1][x - 1] == O && arr[y - 2][x - 2] == O;
    }

    public static boolean topRight(int x, int y, char O) {
        if (y < 2 || x > m - 3) {
            return false;
        }
        return arr[y - 1][x + 1] == O && arr[y - 2][x + 2] == O;
    }

    public static boolean bottomLeft(int x, int y, char O) {
        if (y > n - 3 || x < 2) {
            return false;
        }
        return arr[y + 1][x - 1] == O && arr[y + 2][x - 2] == O;
    }

    public static boolean bottomRight(int x, int y, char O) {
        if (y > n - 3 || x > m - 3) {
            return false;
        }
        return arr[y + 1][x + 1] == O && arr[y + 2][x + 2] == O;
    }
}
