package AlphaStar.SearchingAndSorting3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class SleepingGremlins {

    public static int n;
    public static int k;
    public static int b;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        k = nextInt();
        b = nextInt();
        arr = new int[n];
        for (int i = 0; i < b; ++i) {
            ++arr[nextInt() - 1];
        }
        int current = 0;
        for (int i = 0; i < k; ++i) {
            current += arr[i];
        }
        int res = current;
        for (int i = k; i < n; ++i) {
            current -= arr[i - k];
            current += arr[i];
            res = Math.min(res, current);
        }
        System.out.println(res);
    }
}
