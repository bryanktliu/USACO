package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Herdle {

    public static StreamTokenizer in;

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        String[] correct = new String[3];
        for (int i = 0; i < 3; ++i) {
            correct[i] = next();
        }
        String[] guess = new String[3];
        for (int i = 0; i < 3; ++i) {
            guess[i] = next();
        }
        int ans1 = 0;
        int[] correctl = new int[26];
        int[] guessl = new int[26];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (correct[i].charAt(j) == guess[i].charAt(j)) {
                    ++ans1;
                } else {
                    ++correctl[correct[i].charAt(j) - 'A'];
                    ++guessl[guess[i].charAt(j) - 'A'];
                }
            }
        }
        int ans2 = 0;
        for (int i = 0; i < 26; ++i) {
            ans2 += Math.min(correctl[i], guessl[i]);
        }
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
