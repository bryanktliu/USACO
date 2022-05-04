package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.wormhole
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class wormhole {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.wormhole.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.wormhole.out"))) {
            int n = scanner.nextInt();

            Point[] points = new Point[n];
            for (int i = 0; i < n; ++i) {
                points[i] = new Point(scanner.nextInt(), scanner.nextInt());
            }
            Arrays.sort(points);
            Point prev = points[0];
            for (int i = 1; i < n; ++i) {
                if (prev.y == points[i].y) {
                    prev.next = points[i];
                }
                prev = points[i];
            }

            int[] ints = IntStream.range(0, n).toArray();
            List<int[]> pairs = new ArrayList<>();
            wormhole.findPairs(pairs, ints, 0);

            int count = 0;
            for (int[] pair : pairs) {
                if (checkLoop(points, pair)) {
                    ++count;
                }
            }
            out.println(count);
        }
    }

    static boolean checkLoop(Point[] points, int[] pair) {
        for (int i = 0; i < pair.length; i += 2) {
            points[pair[i]].pair = points[pair[i + 1]];
            points[pair[i + 1]].pair = points[pair[i]];
        }

        for (Point point : points) {
            Point current = point.pair;
            while (current.next != null) {
                if (current.next == point) {
                    return true;
                }
                current = current.next.pair;
            }
        }
        return false;
    }

    static void findPairs(List<int[]> ret, int[] ints, int start) {
        if (start == ints.length) {
            return;
        }
        int[] copy = ints.clone();
        if (start == ints.length - 2) {
            ret.add(copy);
            return;
        }
        findPairs(ret, copy, start + 2);
        for (int i = start + 2; i < ints.length; ++i) {
            copy = ints.clone();
            int tmp = copy[start + 1];
            copy[start + 1] = copy[i];
            copy[i] = tmp;
            findPairs(ret, copy, start + 2);
        }
    }

    private static final class Point implements Comparable<Point> {

        int x;
        int y;
        Point pair;
        Point next;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            int ret = Integer.compare(y, o.y);
            if (ret == 0) {
                return Integer.compare(x, o.x);
            }
            return ret;
        }
    }
}
