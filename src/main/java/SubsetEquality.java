import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetEquality {

    public static char[] s;
    public static char[] t;
    public static int q;
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
        s = next().toCharArray();
        t = next().toCharArray();
        Set<String> map = new HashSet<>();
        List<String> list = getCombinations("abcdefghijklmnopqr".toCharArray());
        for (char c = 'a'; c <= 'r'; ++c) {
            list.add(String.valueOf(c));
        }
        for (String q : list) {
            if (!compare(s, t, q)) {
                map.add(q);
            }
        }
        int q = nextInt();
        String[] queries = new String[q];
        for (int i = 0; i < q; i++) {
            queries[i] = next();
        }
        StringBuilder sb = new StringBuilder();
        for (String query : queries) {
            boolean match;
            if (query.length() > 1) {
                match = checkCombinations(query.toCharArray(), map);
            } else {
                match = !map.contains(query);
            }
            if (match) {
                sb.append('Y');
            } else {
                sb.append('N');
            }
        }
        System.out.println(sb);
    }

    public static boolean compare(char[] s, char[] t, String query) {
        boolean[] set = new boolean[18];
        for (char c : query.toCharArray()) {
            set[c - 'a'] = true;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (char c : s) {
            if (set[c - 'a']) {
                sb1.append(c);
            }
        }
        for (char c : t) {
            if (set[c - 'a']) {
                sb2.append(c);
            }
        }
        return sb1.compareTo(sb2) == 0;
    }

    public static List<String> getCombinations(char[] arr) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                list.add("" + arr[i] + arr[j]);
            }
        }
        return list;
    }

    public static boolean checkCombinations(char[] arr, Set<String> map) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (map.contains("" + arr[i] + arr[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
