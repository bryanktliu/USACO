package AlphaStar.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class MixedUpNames {

    public static int n;
    public static Acorn[] arr;
    public static StreamTokenizer in;

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
        n = nextInt();
        arr = new Acorn[2 * n];
        for (int i = 0; i < n; ++i) {
            char[] word = next().toCharArray();
            Arrays.sort(word);
            StringBuilder chars = new StringBuilder();
            chars.append(word);
            arr[i] = new Acorn(chars.toString(), i, true);
            arr[i + n] = new Acorn(chars.reverse().toString(), i, false);
        }
        Arrays.sort(arr);
        int mincount = 0;
        int maxcount = 0;
        int[][] res = new int[n][2];
        for (int i = 0; i < 2 * n; ++i) {
            if (arr[i].small) {
                res[arr[i].position][0] = maxcount + 1;
                ++mincount;
            } else {
                res[arr[i].position][1] = mincount;
                ++maxcount;
            }
        }
        for (int i = 0; i < n; ++i) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }

    public static class Acorn implements Comparable<Acorn> {

        String name;
        int position;
        boolean small;

        public Acorn(String name, int position, boolean small) {
            this.name = name;
            this.position = position;
            this.small = small;
        }

        @Override
        public int compareTo(Acorn acorn) {
            if (name.equals(acorn.name)) {
                if (small) {
                    return -1;
                }
                return 1;
            }
            return name.compareTo(acorn.name);
        }
    }
}
