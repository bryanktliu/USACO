package AlphaStar.Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class COW {

    public static int n;
    public static char[] arr;
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
        arr = next().toCharArray();
        int w = 0;
        long res = 0;
        long ow = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (arr[i] == 'W') {
                ++w;
            } else if (arr[i] == 'O') {
                ow += w;
            } else {
                res += ow;
            }
        }
        System.out.println(res);
    }
}
