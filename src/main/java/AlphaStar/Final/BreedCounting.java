package AlphaStar.Final;

import java.io.*;

public class BreedCounting {

    public static int n;
    public static int q;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        q = nextInt();
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        int[][] preSum = new int[n + 1][3];
        for (int i = 1; i < n + 1; ++i) {
            preSum[i][0] = preSum[i - 1][0];
            preSum[i][1] = preSum[i - 1][1];
            preSum[i][2] = preSum[i - 1][2];
            ++preSum[i][arr[i - 1] - 1];
        }
        for (int i = 0; i < q; ++i) {
            StringBuilder out = new StringBuilder();
            int start = nextInt() - 1;
            int end = nextInt();
            out.append(preSum[end][0] - preSum[start][0] + " ");
            out.append(preSum[end][1] - preSum[start][1] + " ");
            out.append(preSum[end][2] - preSum[start][2]);
            System.out.println(out);
        }
    }
}
