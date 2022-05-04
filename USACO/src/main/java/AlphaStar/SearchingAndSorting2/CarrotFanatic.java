package AlphaStar.SearchingAndSorting2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class CarrotFanatic {

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
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        Arrays.sort(arr);
        int index = 0;
        int count = 0;
        int[] lower = new int[n];
        int last = 0;
        for (int i = 0; i < n; ++i) {
            ++count;
            while (arr[i] - k > arr[index]) {
                ++index;
                --count;
            }
            lower[i] = Math.max(last, count);
            last = lower[i];
        }
        index = n - 1;
        count = 0;
        int[] higher = new int[n];
        last = 0;
        for (int i = n - 1; i >= 0; --i) {
            ++count;
            while (arr[i] + k < arr[index]) {
                --index;
                --count;
            }
            higher[i] = Math.max(last, count);
            last = higher[i];
        }
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            res = Math.max(res, lower[i] + higher[i + 1]);
        }
        System.out.println(res);
    }
}
