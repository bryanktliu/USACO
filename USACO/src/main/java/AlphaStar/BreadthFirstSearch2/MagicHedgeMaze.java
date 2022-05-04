package AlphaStar.BreadthFirstSearch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MagicHedgeMaze {

    public static int n;
    public static int m;
    public static char[][] arr;
    public static BufferedReader in;
    public static int[] rowChange = {-1, 0, 1, 0};
    public static int[] colChange = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] size = in.readLine().split(" ");
        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);
        arr = new char[n][m];
        int startrow = -1;
        int startcol = -1;
        int endrow = -1;
        int endcol = -1;
        int[][] portals = new int[26][4];
        for (int i = 0; i < 26; ++i) {
            portals[i][0] = -1;
            portals[i][1] = -1;
        }
        for (int i = 0; i < n; ++i) {
            arr[i] = in.readLine().toCharArray();
            for (int j = 0; j < m; ++j) {
                if (arr[i][j] == '@') {
                    startrow = i;
                    startcol = j;
                } else if (arr[i][j] == '=') {
                    endrow = i;
                    endcol = j;
                } else if (arr[i][j] >= 'A' && arr[i][j] <= 'Z') {
                    if (portals[arr[i][j] - 'A'][0] == -1) {
                        portals[arr[i][j] - 'A'][0] = i;
                        portals[arr[i][j] - 'A'][1] = j;
                    } else {
                        portals[arr[i][j] - 'A'][2] = i;
                        portals[arr[i][j] - 'A'][3] = j;
                    }
                }
            }
        }
        boolean[][] visted = new boolean[n][m];
        Queue<Integer> rowqueue = new LinkedList<>();
        Queue<Integer> colqueue = new LinkedList<>();
        arr[startrow][startcol] = 0;
        visted[startrow][startcol] = true;
        rowqueue.add(startrow);
        colqueue.add(startcol);
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
                    if (row < n
                            && row >= 0
                            && col < m
                            && col >= 0
                            && !visted[row][col]
                            && arr[row][col] != '#') {
                        if (row == endrow && col == endcol) {
                            break here;
                        }
                        if (arr[row][col] >= 'A' && arr[row][col] <= 'Z') {
                            int portal = arr[row][col] - 'A';
                            if (portals[portal][0] == row && portals[portal][1] == col) {
                                rowqueue.add(portals[portal][2]);
                                colqueue.add(portals[portal][3]);
                            } else {
                                rowqueue.add(portals[portal][0]);
                                colqueue.add(portals[portal][1]);
                            }
                        } else {
                            rowqueue.add(row);
                            colqueue.add(col);
                        }
                        visted[row][col] = true;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
