package AlphaStar.MixedProblemSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class SchoolYard {

    public static int n;
    public static int s;
    public static int[][] walls;
    public static int[][] students;
    public static int[][] arr;
    public static HashMap<Integer, Integer> xmap;
    public static HashMap<Integer, Integer> ymap;
    public static int[] xchange = {1, 0, -1, 0};
    public static int[] ychange = {0, 1, 0, -1};
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        s = nextInt();
        walls = new int[n][4];
        for (int i = 0; i < n; ++i) {
            walls[i][0] = nextInt();
            walls[i][1] = nextInt();
            walls[i][2] = nextInt();
            walls[i][3] = nextInt();
        }
        students = new int[s][2];
        for (int i = 0; i < s; ++i) {
            students[i][0] = nextInt();
            students[i][1] = nextInt();
        }
        condense();
        int res = 0;
        for (int i = 1; i < s; ++i) {
            int cx = xmap.get(students[i][0]);
            int cy = ymap.get(students[i][1]);
            if (arr[cy][cx] == 1) {
                continue;
            }
            Queue<Integer> queuex = new LinkedList<>();
            Queue<Integer> queuey = new LinkedList<>();
            queuex.add(cx);
            queuey.add(cy);
            arr[cy][cx] = 1;
            int count = 1;
            while (!queuex.isEmpty()) {
                int x = queuex.poll();
                int y = queuey.poll();
                for (int j = 0; j < 4; ++j) {
                    int nx = x + xchange[j];
                    int ny = y + ychange[j];
                    if (nx < 0
                            || nx >= arr[0].length
                            || ny < 0
                            || ny >= arr.length
                            || arr[ny][nx] == 1) {
                        continue;
                    }
                    if (arr[ny][nx] == 2) {
                        ++count;
                    }
                    queuex.add(nx);
                    queuey.add(ny);
                    arr[ny][nx] = 1;
                }
            }
            res = Math.max(res, count);
        }
        System.out.println(res);
    }

    public static void condense() {
        TreeSet<Integer> x = new TreeSet<>();
        TreeSet<Integer> y = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            x.add(walls[i][0]);
            y.add(walls[i][1]);
            x.add(walls[i][2]);
            y.add(walls[i][3]);
        }
        for (int i = 0; i < s; ++i) {
            x.add(students[i][0]);
            y.add(students[i][1]);
        }
        int xsize = x.size();
        int ysize = y.size();
        xmap = new HashMap<>();
        ymap = new HashMap<>();
        for (int i = 0; i < xsize; ++i) {
            int first = x.first();
            xmap.put(first, i * 2 + 1);
            x.remove(first);
        }
        for (int i = 0; i < ysize; ++i) {
            int first = y.first();
            ymap.put(first, i * 2 + 1);
            y.remove(first);
        }
        arr = new int[ysize * 2 + 2][xsize * 2 + 2];
        for (int i = 0; i < n; ++i) {
            if (walls[i][0] == walls[i][2]) {
                int start = ymap.get(walls[i][1]);
                int end = ymap.get(walls[i][3]);
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }
                for (int j = start; j <= end; ++j) {
                    arr[j][xmap.get(walls[i][0])] = 1;
                }
            } else {
                int start = xmap.get(walls[i][0]);
                int end = xmap.get(walls[i][2]);
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }
                for (int j = start; j <= end; ++j) {
                    arr[ymap.get(walls[i][1])][j] = 1;
                }
            }
        }
        for (int i = 0; i < s; ++i) {
            arr[ymap.get(students[i][1])][xmap.get(students[i][0])] = 2;
        }
    }
}
