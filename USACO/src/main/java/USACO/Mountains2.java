package USACO;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Mountains2 {

    public static int n;
    public static int[] arr;
    public static int q;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        HashSet<See> seen = new HashSet<>();
        int total = n - 1;
        for (int i = 0; i < n - 2; ++i) {
            double maxSlope = arr[i + 1] - arr[i];
            for (int j = i + 2; j < n; ++j) {
                double slope = (double) (arr[j] - arr[i]) / (j - i);
                if (slope >= maxSlope) {
                    ++total;
                    seen.add(new See(i, j));
                    maxSlope = slope;
                }
            }
        }
        q = nextInt();
        for (int change = 0; change < q; ++change) {
            int index = nextInt() - 1;
            int increase = nextInt();
            arr[index] += increase;
            for (Iterator<See> i = seen.iterator(); i.hasNext();) {
                See s = i.next();
                if (s.i < index && s.j > index) {
                    double slope = (double) (arr[s.j] - arr[s.i]) / (s.j - s.i);
                    if (arr[index] > arr[s.i] + slope * (index - s.i)) {
                        --total;
                        i.remove();
                    }
                }
            }
            if (index > 0) {
                double minSlope = arr[index] - arr[index - 1];
                for (int i = index - 2; i >= 0; --i) {
                    double slope = (double) (arr[index] - arr[i]) / (index - i);
                    if (slope <= minSlope) {
                        minSlope = slope;
                        if (!seen.contains(new See(i, index))) {
                            ++total;
                            seen.add(new See(i, index));
                        }
                    }
                }
            }
            if (index < n - 1) {
                double maxSlope = arr[index + 1] - arr[index];
                for (int i = index + 2; i < n; ++i) {
                    double slope = (double) (arr[i] - arr[index]) / (i - index);
                    if (slope >= maxSlope) {
                        maxSlope = slope;
                        if (!seen.contains(new See(index, i))) {
                            ++total;
                            seen.add(new See(index, i));
                        }
                    }
                }
            }
            System.out.println(total);
        }
    }

    public static class See {

        public int i;
        public int j;

        public See(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            See see = (See) o;
            return i == see.i && j == see.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}