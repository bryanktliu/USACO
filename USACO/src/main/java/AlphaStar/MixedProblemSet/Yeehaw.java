package AlphaStar.MixedProblemSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Yeehaw {

    public static long start;
    public static long end;
    public static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        start = nextLong();
        end = nextLong();
        int lstart = (int) Math.log10(start) + 1;
        int lend = (int) Math.log10(end) + 2;
        int res = 0;
        for (int i = lstart; i < lend; ++i) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; k++) {
                    if (j == k) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int l = 0; l < i - 1; ++l) {
                        sb.append(k);
                    }
                    sb.append(j);
                    for (int l = 0; l < i; ++l) {
                        long num = Long.parseLong(sb.toString());
                        if (sb.charAt(0) != '0' && num >= start && num <= end) {
                            ++res;
                        }
                        sb.deleteCharAt(0);
                        sb.append(k);
                    }
                }
            }
        }
        System.out.println(res);
    }
}
