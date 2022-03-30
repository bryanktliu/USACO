package AlphaStar.SearchingAndSorting1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class AcornHarvest {

    public static int n;
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static int m;
    public static Acorn[] arr;
    public static StreamTokenizer in;
    public static int res;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        a = nextInt();
        b = nextInt();
        c = nextInt();
        d = nextInt();
        e = nextInt();
        f = nextInt();
        g = nextInt();
        h = nextInt();
        m = nextInt();
        arr = new Acorn[n * 3];
        for (int i = 0; i < n * 3; ++i) {
            long weight = a * power(i, 5, d) + b * power(i, 2, d) + c;
            long utility = e * power(i, 5, h) + f * power(i, 3, h) + g;
            arr[i] = new Acorn(utility % h, weight % d);
        }
        sort(n);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            res = (res + arr[i].weight) % m;
        }
        System.out.println(res);
    }

    public static long power(int base, int power, int mod) {
        long num = 1;
        for (int i = 0; i < power; ++i) {
            num = (num * base) % mod;
        }
        return num % mod;
    }

    public static class Acorn {

        long utility;
        long weight;

        public Acorn(long utility, long weight) {
            this.utility = utility;
            this.weight = weight;
        }
    }

    public static int compare(Acorn a1, Acorn a2) {
        if (a1.utility == a2.utility) {
            return Long.compare(a1.weight, a2.weight);
        }
        return Long.compare(a2.utility, a1.utility);
    }

    public static void sort(int n) {
        int low = 0;
        int high = n - 1;
        while (high > low) {
            int mid = partition(low, high);
            if (mid > n) {
                high = mid - 1;
            } else if (mid < n) {
                low = mid + 1;
            } else {
                break;
            }
        }
    }

    public static int partition(int start, int end) {
        Acorn split = arr[start];
        int small = start;
        for (int i = start + 1; i <= end; ++i) {
            if (compare(arr[i], split) < 0) {
                ++small;
                swap(small, i);
            }
        }
        swap(start, small);
        return small;
    }

    public static void swap(int x, int y) {
        Acorn temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
