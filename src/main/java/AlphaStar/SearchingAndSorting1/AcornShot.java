package AlphaStar.SearchingAndSorting1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class AcornShot {

    public static int n;
    public static int k;
    public static int[] arr;
    public static StreamTokenizer in;
    public static int res;

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
        int low = 0;
        int high = arr[n - 1];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isValid(mid)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(res);
    }

    public static boolean isValid(int radius) {
        int count = 1;
        int pos = arr[0] + 2 * radius;
        for (int i = 0; i < n; ++i) {
            if (arr[i] > pos) {
                ++count;
                pos = arr[i] + 2 * radius;
            }
        }
        return count <= k;
    }
}
