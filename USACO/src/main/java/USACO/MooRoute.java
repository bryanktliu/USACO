package USACO;

import java.io.*;

public class MooRoute {

    public static int n;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n + 1];
        int steps = 0;
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
            steps += arr[i];
        }
        StringBuilder route = new StringBuilder();
        int index = 0;
        boolean direction = true;
        while (steps > 0) {
            if (direction) {
                if (arr[index] == 0) {
                    direction = false;
                    continue;
                }
                --steps;
                --arr[index++];
                route.append("R");
            } else {
                if (index == 0 || (arr[index - 1] == 1 && arr[index] != 0)) {
                    direction = true;
                    continue;
                }
                --steps;
                --arr[--index];
                route.append("L");
            }
        }
        System.out.println(route);
    }
}

