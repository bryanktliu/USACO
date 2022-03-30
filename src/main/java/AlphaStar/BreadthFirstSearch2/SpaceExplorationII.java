package AlphaStar.BreadthFirstSearch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SpaceExplorationII {

    public static int n;
    public static char[][] arr;
    public static BufferedReader in;
    public static int[] rowChange = {1, 0, -1, 0};
    public static int[] colChange = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new char[n][n];
        for (int i = 0; i < n; ++i) {
            arr[i] = in.readLine().toCharArray();
        }
        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i][j] == '*') {
                    ++count;
                    Queue<Integer> rowqueue = new LinkedList<>();
                    Queue<Integer> colqueue = new LinkedList<>();
                    rowqueue.add(i);
                    colqueue.add(j);
                    arr[i][j] = '.';
                    while (!rowqueue.isEmpty()) {
                        int currentrow = rowqueue.poll();
                        int currentcol = colqueue.poll();
                        for (int k = 0; k < 4; ++k) {
                            int row = currentrow + rowChange[i];
                            int col = currentcol + colChange[i];
                            if (row < n && row >= 0 && col < n && col >= 0) {
                                if (arr[row][col] == '*') {
                                    rowqueue.add(row);
                                    colqueue.add(col);
                                    arr[row][col] = '.';
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
