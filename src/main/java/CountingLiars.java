import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class CountingLiars {

    public static int n;
    public static ArrayList<Integer> greater;
    public static ArrayList<Integer> less;
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
        HashSet<Integer> positions = new HashSet<>();
        greater = new ArrayList<>();
        less = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (next().equals("G")) {
                int num = nextInt();
                positions.add(num);
                greater.add(num);
            } else {
                int num = nextInt();
                positions.add(num);
                less.add(num);
            }
        }
        greater.sort(Comparator.reverseOrder());
        less.sort(null);
        int[] pos = positions.stream().mapToInt(Integer::intValue).toArray();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < pos.length; ++i) {
            res = Math.min(res, countLiar(pos[i]));
        }
        System.out.println(res);
    }

    public static int countLiar(int pos) {
        int lsize = less.size();
        int gsize = greater.size();
        int count = 0;
        for (int i = 0; i < lsize; ++i) {
            if (less.get(i) < pos) {
                ++count;
            } else {
                break;
            }
        }
        for (int i = 0; i < gsize; ++i) {
            if (greater.get(i) > pos) {
                ++count;
            } else {
                break;
            }
        }
        return count;
    }
}
