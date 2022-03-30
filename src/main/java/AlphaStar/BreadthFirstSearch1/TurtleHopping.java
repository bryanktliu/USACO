package AlphaStar.BreadthFirstSearch1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TurtleHopping {

    public static int m;
    public static int n;
    public static int m1;
    public static int m2;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        m = nextInt();
        n = nextInt();
        m1 = nextInt();
        m2 = nextInt();
        int[] xdif = {-m1, -m1, -m2, m2, m1, m1, m2, -m2};
        int[] ydif = {-m2, m2, m1, m1, m2, -m2, -m1, -m1};
        arr = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(arr[i], -1);
        }
        int startx = 0;
        int starty = 0;
        int endx = 0;
        int endy = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int input = nextInt();
                if (input == 0 || input == 2) {
                    arr[i][j] = -2;
                } else if (input == 3) {
                    startx = i;
                    starty = j;
                } else if (input == 4) {
                    endx = i;
                    endy = j;
                }
            }
        }
        Queue<Integer> queuex = new LinkedList<>();
        Queue<Integer> queuey = new LinkedList<>();
        queuex.add(startx);
        queuey.add(starty);
        arr[startx][starty] = 0;
        int currentx;
        int currenty;
        while (!queuex.isEmpty()) {
            currentx = queuex.poll();
            currenty = queuey.poll();
            if (currentx == endx && currenty == endy) {
                break;
            }
            for (int i = 0; i < 8; ++i) {
                int x = currentx + xdif[i];
                int y = currenty + ydif[i];
                if (x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                if (arr[x][y] != -1) {
                    continue;
                }
                arr[x][y] = arr[currentx][currenty] + 1;
                queuex.add(x);
                queuey.add(y);
            }
        }
        System.out.println(arr[endx][endy]);
    }
}
