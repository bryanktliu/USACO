package USACO;

import java.io.*;

public class Mountains {

    public static int n;
    public static int[] arr;
    public static int q;
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
        boolean[][] seen = new boolean[n - 1][n];
        int total = n - 1;
        for (int i = 0; i < n - 2; ++i) {
            double maxSlope = arr[i + 1] - arr[i];
            for (int j = i + 2; j < n; ++j) {
                double slope = (double) (arr[j] - arr[i]) / (j - i);
                if (slope >= maxSlope) {
                    ++total;
                    seen[i][j] = true;
                    maxSlope = slope;
                }
            }
        }
        q = nextInt();
        for (int change = 0; change < q; ++change) {
            int index = nextInt() - 1;
            int increase = nextInt();
            arr[index] += increase;
            for (int i = 0; i < index; ++i) {
                for (int j = index + 1; j < n; ++j) {
                    if (seen[i][j]) {
                        double slope = (double) (arr[j] - arr[i]) / (j - i);
                        if (arr[index] > arr[i] + slope * (index - i)) {
                            --total;
                            seen[i][j] = false;
                        }
                    }
                }
            }
            if (index > 0) {
                double minSlope = arr[index] - arr[index - 1];
                for (int i = index - 2; i >= 0; --i) {
                    double slope = (double) (arr[index] - arr[i]) / (index - i);
                    if (slope <= minSlope) {
                        minSlope = slope;
                        if (!seen[i][index]) {
                            ++total;
                            seen[i][index] = true;
                        }
                    }
                }
            }
            if (index < n - 1) {
                double maxSlope = arr[index + 1] - arr[index];
                for (int i = index + 2; i < n; ++i) {
                    double slope = (double) (arr[i] - arr[index]) / (i - index);
                    if (slope >= maxSlope) {
                        maxSlope = slope;
                        if (!seen[index][i]) {
                            ++total;
                            seen[index][i] = true;
                        }
                    }
                }
            }
            System.out.println(total);
        }
    }
}