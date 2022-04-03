package AlphaStar.MixedProblemSet3;

import java.io.*;

public class Mittens {

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
        arr = new int[1000002][2];
        int index = 1;
        for (int i = 0; i < n; ++i) {
            int speed = nextInt();
            int time = nextInt();
            for (int j = 0; j < time; ++j) {
                arr[index][0] = arr[index - 1][0] + speed;
                ++index;
            }
        }
        for (int i = index; i < 1000002; ++i) {
            arr[i][0] = arr[i - 1][0];
        }
        index = 1;
        for (int i = 0; i < m; ++i) {
            int speed = nextInt();
            int time = nextInt();
            for (int j = 0; j < time; ++j) {
                arr[index][1] = arr[index - 1][1] + speed;
                ++index;
            }
        }
        for (int i = index; i < 1000002; ++i) {
            arr[i][1] = arr[i - 1][1];
        }
        int start = 0;
        boolean john = true;
        for (int i = 0; i < 1000002; ++i) {
            if (arr[i][0] > arr[i][1]) {
                start = i + 1;
                break;
            } else if (arr[i][1] > arr[i][0]) {
                start = i + 1;
                john = false;
                break;
            }
        }
        int count = 0;
        for (int i = start; i < 1000002; ++i) {
            if (john && arr[i][1] > arr[i][0]) {
                john = false;
                ++count;
            } else if (!john && arr[i][0] >= arr[i][1]) {
                john = true;
                ++count;
            }
        }
        System.out.println(count);
    }
}
