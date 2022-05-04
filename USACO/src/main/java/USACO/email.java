package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class email {

    public static int t;
    public static int n;
    public static int m;
    public static int k;
    public static int[] arr;
    public static int[] count;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        t = nextInt();
        for (int sub = 0; sub < t; ++sub) {
            m = nextInt();
            n = nextInt();
            k = nextInt();
            arr = new int[n + 1];
            count = new int[m + 1];
            for (int i = 1; i <= n; ++i) {
                arr[i] = nextInt();
                ++count[arr[i]];
            }
            System.out.println(compute());
        }
    }

    public static String compute() {
        int lower = 1;
        int index = k + 1;
        int[] seen = new int[m + 1];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= k; ++i) {
            list.add(arr[i]);
        }
        Stack<Integer> passed = new Stack<>();
        while (!list.isEmpty()) {
            int max = lower + k;
            boolean found = false;
            boolean[] remove = new boolean[k];
            int len = Math.min(k, list.size());
            for (int i = 0; i < len; ++i) {
                int current = list.get(i);
                if (current >= lower && current < max) {
                    found = true;
                    ++seen[current];
                    remove[i] = true;
                    if (index > n) {
                        if (!passed.isEmpty()) {
                            int p = passed.pop();
                            list.add(p);
                        }
                    } else {
                        list.add(arr[index]);
                        ++index;
                    }
                }
            }
            for (int i = k - 1; i >= 0; --i) {
                if (remove[i]) {
                    list.remove(i);
                }
            }
            if (!found) {
                if (seen[lower] == count[lower]) {
                    ++lower;
                } else if (index <= n) {
                    passed.push(list.get(0));
                    list.remove(0);
                    list.add(arr[index]);
                    ++index;
                } else {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
