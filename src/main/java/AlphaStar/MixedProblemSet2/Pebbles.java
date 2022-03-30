package AlphaStar.MixedProblemSet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Pebbles {

    public static int n;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        Arrays.sort(arr);
        min();
        max();
    }

    public static void min() {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int pebbles = 0;
            if (arr[n - 1] - arr[i] < n) {
                if (i == 0) {
                    res = 0;
                }
                break;
            }
            while (pebbles + i < n && arr[pebbles + i] - arr[i] < n) {
                ++pebbles;
            }
            res = Math.min(n - pebbles, res);
            if (res == 1) {
                if ((arr[i + n - 2] != arr[i] + n - 1 && arr[n - 1] - arr[i + n - 2] > 2)
                        || arr[i] + n - 1 > arr[n - 1]) {
                    ++res;
                }
                break;
            }
        }
        System.out.println(res);
    }

    public static void max() {
        int spaces = arr[n - 1] - arr[0] + 1 - n;
        System.out.println(spaces - Math.min(arr[1] - arr[0], arr[n - 1] - arr[n - 2]) + 1);
    }
}
