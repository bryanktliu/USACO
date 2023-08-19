package USACO;

import java.io.*;

public class MooOperations {

    public static int n;
    public static String[] arr;
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
        arr = new String[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = next();
        }
        for (String moo: arr) {
            if (moo.length() < 3) {
                System.out.println(-1);
                continue;
            }
            char[] chars = moo.toCharArray();
            boolean possible = false;
            for (int i = 1; i < chars.length - 1; ++i) {
                if (chars[i] == 'O') {
                    possible = true;
                    break;
                }
            }
            if (!possible) {
                System.out.println(-1);
            } else if (moo.contains("MOO")) {
                System.out.println(chars.length - 3);
            } else if (moo.contains("MO")) {
                if (moo.indexOf("MO") != chars.length - 1) {
                    System.out.println(chars.length - 2);
                } else {
                    System.out.println(chars.length - 1);
                }
            } else if (moo.indexOf("OO", 1) != -1){
                System.out.println(chars.length - 2);
            } else {
                System.out.println(chars.length - 1);
            }
        }
    }
}
