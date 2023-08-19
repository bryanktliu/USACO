package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MooRouteII {

    public static int n;
    public static int m;
    public static int[][] flights;
    public static int[] waits;
    public static int[] min;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        m = nextInt();
        flights = new int[m][4];
        min = new int[n];
        waits = new int[n];
        for (int i = 0; i < m; ++i) {
            flights[i][0] = nextInt() - 1;
            flights[i][1] = nextInt();
            flights[i][2] = nextInt() - 1;
            flights[i][3] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            waits[i] = nextInt();
            min[i] = 1000000001;
        }
        min[0] = 0;
        for (int i = 0; i < m; ++i) {
            if (flights[i][0] == 0) {
                if (min[flights[i][2]] > flights[i][3]) {
                    min[flights[i][2]] = flights[i][3];
                    travel(flights[i][2], flights[i][3]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (min[i] == 1000000001) {
                System.out.println(-1);
            } else {
                System.out.println(min[i]);
            }
        }
    }

    public static void travel(int airport, int time) {
        for (int i = 0; i < m; ++i) {
            if (flights[i][0] == airport && time + waits[airport] <= flights[i][1]) {
                if (min[flights[i][2]] > flights[i][3]) {
                    min[flights[i][2]] = flights[i][3];
                    travel(flights[i][2], flights[i][3]);
                }
            }
        }
    }
}
