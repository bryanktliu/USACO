import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Cereal2 {

    public static int n;
    public static int m;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        m = nextInt();
        arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        boolean[] eaten = new boolean[m];
        boolean[] seen = new boolean[n];
        int min = dfs(eaten, seen);
        System.out.println(min);
    }

    public static int dfs(boolean[] eaten, boolean[] seen) {
        int min = n;
        for (int i = 0; i < n; ++i) {
            int count = 0;
            if (seen[i]) {
                continue;
            }
            boolean[] eaten2 = eaten.clone();
            boolean[] seen2 = seen.clone();
            seen2[i] = true;
            if (!eaten[arr[i][0] - 1]) {
                eaten2[arr[i][0] - 1] = true;
            } else if (!eaten[arr[i][1] - 1]) {
                eaten2[arr[i][1] - 1] = true;
            } else {
                ++count;
            }
            min = Math.min(min, Math.min(count, dfs(eaten2, seen2)));
        }
        return min;
    }
}
