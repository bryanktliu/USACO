package USACO;

import java.io.*;
import java.util.*;

public class Visits {

    public static int n;
    public static Cow[] arr;
    public static int[] visiting;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new Cow[n];
        visiting = new int[n];
        long total = 0;
        for (int i = 0; i < n; ++i) {
            arr[i] = new Cow(i, nextInt() - 1, nextInt());
            ++visiting[arr[i].buddy];
            total += arr[i].joy;
        }
        for (int i = 0; i < n; ++i) {
            if (visiting[i] == 0) {
                removeDangling(i, i);
            }
        }
        List<Cow> remaining = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (visiting[i] != 0) {
                remaining.add(arr[i]);
            }
        }
        HashSet<Integer> visited = new HashSet<>();
        for (Cow cow : remaining) {
            if (!visited.contains(cow.pos)) {
                int min = cow.joy;
                visited.add(cow.pos);
                int next = cow.buddy;
                while (!visited.contains(next)) {
                    visited.add(next);
                    min = Math.min(min, arr[next].joy);
                    next = arr[next].buddy;
                }
                total -= min;
            }
        }
        System.out.println(total);
    }

    public static void removeDangling(int start, int pos) {
        int buddy = arr[pos].buddy;
        --visiting[buddy];
        if (visiting[buddy] == 0 && buddy < start) {
            removeDangling(start, buddy);
        }
    }

    public static class Cow {

        int pos;
        int buddy;
        int joy;

        public Cow(int pos, int buddy, int joy) {
            this.pos = pos;
            this.buddy = buddy;
            this.joy = joy;
        }
    }
}
