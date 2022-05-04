package AlphaStar.BreadthFirstSearch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Mootel {

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
        arr = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                arr[i][j] = nextInt();
            }
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(m);
        distance[m] = 0;
        int prev = -1;
        int current;
        while (!queue.isEmpty()) {
            int size = queue.size();
            prev += 1;
            int[] out = new int[size];
            for (int i = 0; i < size; ++i) {
                current = queue.poll();
                out[i] = current;
                for (int j = 1; j < n + 1; ++j) {
                    if (arr[current][j] == 0 || distance[j] >= 0) {
                        continue;
                    }
                    distance[j] = distance[current] + 1;
                    queue.add(j);
                }
            }
            Arrays.sort(out);
            for (int i = 0; i < size; ++i) {
                System.out.print(out[i] + " ");
            }
            System.out.println();
        }
    }
}
