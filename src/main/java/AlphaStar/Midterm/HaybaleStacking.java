package AlphaStar.Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class HaybaleStacking {

    public static int n;
    public static int k;
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
        arr = new int[n + 1];
        for (int i = 0; i < k; ++i) {
            int start = nextInt();
            int end = nextInt();
            ++arr[start - 1];
            --arr[end];
        }
        for (int i = 1; i < n; ++i) {
            arr[i] += arr[i - 1];
        }
        Arrays.sort(arr);
        System.out.println(arr[n / 2 + 1]);
    }
}
