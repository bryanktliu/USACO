package AlphaStar.BreadthFirstSearch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PaintJob {

    public static int a;
    public static int b;
    public static int n;
    public static int p;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        a = nextInt();
        b = nextInt();
        n = nextInt();
        p = nextInt();
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        int[] steps = new int[p + 1];
        Arrays.fill(steps, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        steps[a] = 0;
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current == b) {
                break;
            }
            for (int i = 0; i < n; ++i) {
                int next = current * arr[i] % p;
                if (steps[next] < 0) {
                    steps[next] = steps[current] + 1;
                    queue.add(next);
                }
            }
        }
        System.out.println(steps[b]);
    }
}
