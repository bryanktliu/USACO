package USACO;

import java.io.*;

public class LonelyPhoto {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        int num = nextInt();
        char[] cows = next().toCharArray();
        int count = 0;
        int g = 0;
        int h = 0;
        if (cows[0] == 'G') {
            g = 1;
        } else {
            h = 1;
        }
        for (int i = 3; i < num + 1; ++i) {
            if (cows[i - 2] == 'G') {
                ++g;
            } else {
                ++h;
            }
            int gnum = g;
            int hnum = h;
            for (int j = i - 1; j < num; ++j) {
                if (cows[j] == 'G') {
                    ++gnum;
                } else {
                    ++hnum;
                }
                if (gnum == 1 || hnum == 1) {
                    ++count;
                }
                if (cows[j - i + 1] == 'G') {
                    --gnum;
                } else {
                    --hnum;
                }
            }
        }
        out.println(count);
        out.close();
    }
}
