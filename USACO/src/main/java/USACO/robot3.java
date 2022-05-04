package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class robot3 {

    public static int n;
    public static int[][] arr;
    public static int[] counts;
    public static int[] target;
    public static int[] ids;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        target = new int[2];
        target[0] = nextInt();
        target[1] = nextInt();
        arr = new int[n][2];
        ids = new int[n];
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        counts = new int[n];
        countSubset(new Position(), 0);
        for (int c : counts) {
            System.out.println(c);
        }
    }

    public static void countSubset(Position position, int index) {
        if (index > n - 1) {
            if (position.match()) {
                ++counts[position.steps - 1];
            }
            return;
        }
        Position newList = new Position(position, ids[index]);
        countSubset(newList, index + 1);
        countSubset(position, index + 1);
    }

    public static class Position {

        int x;
        int y;
        int steps;

        public Position() {}

        public Position(Position position, int index) {
            this.x = position.x + arr[index][0];
            this.y = position.y + arr[index][1];
            this.steps = position.steps + 1;
        }

        public boolean match() {
            return x == target[0] && y == target[1];
        }
    }
}
