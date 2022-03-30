package AlphaStar.SearchingAndSorting3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PawEarTail {

    public static int n;
    public static int[] arr;
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
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            String s = next();
            if (s.equals("T")) {
                arr[i] = 1;
            } else if (s.equals("P")) {
                arr[i] = 2;
            }
        }
        int[] p1 = new int[n + 1];
        int[] e1 = new int[n + 1];
        int[] t1 = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            p1[i] = p1[i - 1];
            e1[i] = e1[i - 1];
            t1[i] = t1[i - 1];
            if (arr[i - 1] == 0) {
                ++p1[i];
            } else if (arr[i - 1] == 1) {
                ++e1[i];
            } else {
                ++t1[i];
            }
        }
        int[] max = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            max[i] = Math.max(Math.max(p1[i], e1[i]), t1[i]);
        }
        int[] p2 = new int[n + 1];
        int[] e2 = new int[n + 1];
        int[] t2 = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            p2[i] = p2[i + 1];
            e2[i] = e2[i + 1];
            t2[i] = t2[i + 1];
            if (arr[i] == 0) {
                ++p2[i];
            } else if (arr[i] == 1) {
                ++e2[i];
            } else {
                ++t2[i];
            }
        }
        int res = 0;
        for (int i = 0; i < n + 1; ++i) {
            res = Math.max(res, Math.max(Math.max(p2[i], e2[i]), t2[i]) + max[i]);
        }
        System.out.println(res);
    }
}
