package AlphaStar.BreadthFirstSearch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FlyingCow {

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
        arr = new int[n * 2 + 1];
        Arrays.fill(arr, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        arr[1] = 0;
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current == n) {
                break;
            }
            int next = current * 3;
            if (next <= 2 * n && arr[next] < 0) {
                arr[next] = arr[current] + 1;
                queue.add(next);
            }
            next = current + 1;
            if (next <= 2 * n && arr[next] < 0) {
                arr[next] = arr[current] + 1;
                queue.add(next);
            }
            next = current - 1;
            if (next > 0 && arr[next] < 0) {
                arr[next] = arr[current] + 1;
                queue.add(next);
            }
        }
        System.out.println(arr[n]);
    }
}
