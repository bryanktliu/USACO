package AlphaStar.MixedProblemSet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CowJog {

    public static int n;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        int current = Integer.MAX_VALUE;
        int count = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (arr[i][1] <= current) {
                ++count;
                current = arr[i][1];
            }
        }
        System.out.println(count);
    }
}
