package AlphaStar.MixedProblemSet2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class FarmEnclosures {

    public static int n;
    public static Tree[] x;
    public static Tree[] y;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        x = new Tree[n];
        y = new Tree[n];
        for (int i = 0; i < n; ++i) {
            x[i] = new Tree(nextInt(), nextInt());
            y[i] = x[i];
        }
        Arrays.sort(x, Comparator.comparingInt(o -> o.x));
        Arrays.sort(y, Comparator.comparingInt(o -> o.y));
        HashSet<Tree> trees = new HashSet<>();
        for (int i = 0; i < 3; ++i) {
            trees.add(x[i]);
            trees.add(x[i + n - 3]);
            trees.add(y[i]);
            trees.add(y[i + n - 3]);
        }
        Long res = Long.MAX_VALUE;
        Tree[] choices = trees.toArray(new Tree[0]);
        for (int i = 0; i < choices.length; ++i) {
            for (int j = 0; j < choices.length; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < choices.length; ++k) {
                    if (i == k || j == k) {
                        continue;
                    }
                    res = Math.min(res, area(choices[i], choices[j], choices[k]));
                }
            }
        }
        System.out.println(res);
    }

    public static int area(Tree t1, Tree t2, Tree t3) {
        int minx = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        for (int i = 0; i < 4; ++i) {
            if (x[i] != t1 && x[i] != t2 && x[i] != t3) {
                minx = Math.min(minx, x[i].x);
            }
            int j = i + n - 4;
            if (x[j] != t1 && x[j] != t2 && x[j] != t3) {
                maxx = Math.max(maxx, x[j].x);
            }
        }
        int miny = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        for (int i = 0; i < 4; ++i) {
            if (y[i] != t1 && y[i] != t2 && y[i] != t3) {
                miny = Math.min(miny, y[i].y);
            }
            int j = i + n - 4;
            if (y[j] != t1 && y[j] != t2 && y[j] != t3) {
                maxy = Math.max(maxy, y[j].y);
            }
        }
        return (maxx - minx) * (maxy - miny);
    }

    public static class Tree {

        int x;
        int y;

        public Tree(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
