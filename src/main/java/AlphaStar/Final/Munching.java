package AlphaStar.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Munching {

    public static int r;
    public static int c;
    public static char[][] arr;
    public static int startRow;
    public static int startCol;
    public static BufferedReader in;
    public static int[] rowChange = {-1, 0, 1, 0};
    public static int[] colChange = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] size = in.readLine().split(" ");
        r = Integer.parseInt(size[0]);
        c = Integer.parseInt(size[1]);
        arr = new char[r][c];
        for (int i = 0; i < r; ++i) {
            arr[i] = in.readLine().toCharArray();
            for (int j = 0; j < c; ++j) {
                if (arr[i][j] == 'C') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        boolean[][] visted = new boolean[r][c];
        Queue<Integer> rowqueue = new LinkedList<>();
        Queue<Integer> colqueue = new LinkedList<>();
        arr[startRow][startCol] = '*';
        visted[startRow][startCol] = true;
        rowqueue.add(startRow);
        colqueue.add(startCol);
        int count = 0;
        here:
        while (!rowqueue.isEmpty()) {
            ++count;
            int repeat = rowqueue.size();
            for (int i = 0; i < repeat; ++i) {
                int crow = rowqueue.poll();
                int ccol = colqueue.poll();
                for (int j = 0; j < 4; ++j) {
                    int row = crow + rowChange[j];
                    int col = ccol + colChange[j];
                    if (row < r
                            && row >= 0
                            && col < c
                            && col >= 0
                            && !visted[row][col]
                            && arr[row][col] != '*') {
                        if (arr[row][col] == 'B') {
                            break here;
                        }
                        rowqueue.add(row);
                        colqueue.add(col);
                        visted[row][col] = true;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
