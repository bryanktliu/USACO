package AlphaStar.SearchingAndSorting2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;

public class AcornDiversity {

    public static int n;
    public static int m;
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
        m = nextInt();
        arr = new String[n * 2];
        for (int i = 0; i < n * 2; ++i) {
            arr[i] = next();
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                for (int k = j + 1; k < m; ++k) {
                    HashSet<String> set = new HashSet<>();
                    for (int l = 0; l < n; ++l) {
                        set.add("" + arr[l].charAt(i) + arr[l].charAt(j) + arr[l].charAt(k));
                    }
                    boolean found = false;
                    for (int l = n; l < n * 2; ++l) {
                        if (set.contains(
                                "" + arr[l].charAt(i) + arr[l].charAt(j) + arr[l].charAt(k))) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ++res;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
