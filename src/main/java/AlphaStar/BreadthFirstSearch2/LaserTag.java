package AlphaStar.BreadthFirstSearch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class LaserTag {

    public static int w;
    public static int h;
    public static char[][] arr;
    public static BufferedReader in;
    public static int[] rowChange = {-1, 0, 1, 0};
    public static int[] colChange = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] size = in.readLine().split(" ");
        w = Integer.parseInt(size[0]);
        h = Integer.parseInt(size[1]);
        arr = new char[h][w];
        int startrow = -1;
        int startcol = -1;
        int endrow = -1;
        int endcol = -1;
        for (int i = 0; i < h; ++i) {
            arr[i] = in.readLine().toCharArray();
            for (int j = 0; j < w; ++j) {
                if (arr[i][j] == 'C') {
                    if (startrow == -1) {
                        startrow = i;
                        startcol = j;
                    } else {
                        endrow = i;
                        endcol = j;
                    }
                }
            }
        }
        boolean[][] visted = new boolean[h][w];
        Queue<Integer> rowqueue = new LinkedList<>();
        Queue<Integer> colqueue = new LinkedList<>();
        arr[startrow][startcol] = 0;
        visted[startrow][startcol] = true;
        rowqueue.add(startrow);
        colqueue.add(startcol);
        int count = -1;
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
                    while (row < h && row >= 0 && col < w && col >= 0 && arr[row][col] != '*') {
                        if (row == endrow && col == endcol) {
                            break here;
                        }
                        if (!visted[row][col]) {
                            rowqueue.add(row);
                            colqueue.add(col);
                            visted[row][col] = true;
                        }
                        row += rowChange[j];
                        col += colChange[j];
                    }
                }
            }
        }
        System.out.println(count);
    }
}
