package AlphaStar.MixedProblemSet3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class TheBovineShuffle {

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
            arr[i] = nextInt() - 1;
        }
        int[] positions = new int[n];
        for (int i = 0; i < n; ++i) {
            ++positions[arr[i]];
        }
        int res = n;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (positions[i] == 0) {
                --res;
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            --positions[arr[pos]];
            if (positions[arr[pos]] == 0) {
                --res;
                queue.add(arr[pos]);
            }
        }
        System.out.println(res);
    }
}
