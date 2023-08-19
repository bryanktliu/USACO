package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PilingPapers {

    public static int n;
    public static long a;
    public static long b;
    public static int[] digits;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        a = nextInt();
        b = nextInt();
        digits = new int[n];
        for (int i = 0; i < n; ++i) {
            digits[i] = nextInt();
        }
        int q = nextInt();
        for (int query = 0; query < q; ++query) {
            int start = nextInt() - 1;
            int end = nextInt();
            System.out.println(generate(start, end, 0));
        }
    }

    public static int generate(int index, int end, long current) {
        if (current > b) {
            return 0;
        }
        if (index == end) {
            if (current >= a) {
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        res += generate(index + 1, end, current);
        if (current == 0) {
            res += 2 * generate(index + 1, end, digits[index]);
        } else {
            res += generate(index + 1, end, current * 10 + digits[index]);
            res += generate(index + 1, end, current + (long) (Math.pow(10, (int) Math.log10(current) + 1)) * digits[index]);
        }
        return res;
    }
}
