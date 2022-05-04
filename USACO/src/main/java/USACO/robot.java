package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class robot {

    public static int n;
    public static int endx;
    public static int endy;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        endx = nextInt();
        endy = nextInt();
        arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            System.out.println(arr[i][0]);
            System.out.println(arr[i][1]);
        }
    }
}
