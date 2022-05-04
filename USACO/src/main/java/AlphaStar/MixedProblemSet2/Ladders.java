package AlphaStar.MixedProblemSet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Ladders {

    public static int n;
    public static char[] steps;
    public static int[][] arr;
    public static int[] xchange = {1, 0, -1, 0};
    public static int[] ychange = {0, 1, 0, -1};
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        steps = next().toCharArray();
        fill();
        int count = -1;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == 0) {
                    ++count;
                    Queue<Integer> queuex = new LinkedList<>();
                    Queue<Integer> queuey = new LinkedList<>();
                    queuex.add(j);
                    queuey.add(i);
                    arr[i][j] = 1;
                    while (!queuex.isEmpty()) {
                        int cx = queuex.poll();
                        int cy = queuey.poll();
                        for (int k = 0; k < 4; ++k) {
                            int nx = cx + xchange[k];
                            int ny = cy + ychange[k];
                            if (nx < 0
                                    || nx >= arr[0].length
                                    || ny < 0
                                    || ny >= arr.length
                                    || arr[ny][nx] == 1) {
                                continue;
                            }
                            queuex.add(nx);
                            queuey.add(ny);
                            arr[ny][nx] = 1;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void fill() {
        int maxx = 0;
        int maxy = 0;
        int minx = 0;
        int miny = 0;
        int cx = 0;
        int cy = 0;
        for (int i = 0; i < n; ++i) {
            if (steps[i] == 'N') {
                cy -= 2;
                miny = Math.min(miny, cy);
            } else if (steps[i] == 'E') {
                cx += 2;
                maxx = Math.max(maxx, cx);
            } else if (steps[i] == 'S') {
                cy += 2;
                maxy = Math.max(maxy, cy);
            } else {
                cx -= 2;
                minx = Math.min(minx, cx);
            }
        }
        arr = new int[maxy - miny + 3][maxx - minx + 3];
        cx = -minx + 1;
        cy = -miny + 1;
        for (int i = 0; i < n; ++i) {
            if (steps[i] == 'N') {
                arr[cy - 1][cx] = 1;
                arr[cy - 2][cx] = 1;
                cy -= 2;
            } else if (steps[i] == 'E') {
                arr[cy][cx + 1] = 1;
                arr[cy][cx + 2] = 1;
                cx += 2;
            } else if (steps[i] == 'S') {
                arr[cy + 1][cx] = 1;
                arr[cy + 2][cx] = 1;
                cy += 2;
            } else {
                arr[cy][cx - 1] = 1;
                arr[cy][cx - 2] = 1;
                cx -= 2;
            }
        }
    }
}
