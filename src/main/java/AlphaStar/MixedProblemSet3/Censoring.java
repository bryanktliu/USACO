package AlphaStar.MixedProblemSet3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Censoring {

    public static char[] s;
    public static String t;
    public static int[] arr;
    public static StreamTokenizer in;

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        s = next().toCharArray();
        t = next();
        StringBuilder res = new StringBuilder();
        for (char c : s) {
            res.append(c);
            if (res.length() >= t.length()
                    && res.substring(res.length() - t.length(), res.length()).equals(t)) {
                res.delete(res.length() - t.length(), res.length());
            }
        }
        System.out.println(res);
    }
}
