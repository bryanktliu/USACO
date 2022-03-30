import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Photoshoot {

    public static int n;
    public static List<Integer> list;
    public static int[] nums;
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
        list = new ArrayList<>();
        char[] cows = next().toCharArray();
        for (int i = 0; i < n; i += 2) {
            if (cows[i] != cows[i + 1]) {
                if (cows[i] == 'H') {
                    list.add(1);
                } else {
                    list.add(0);
                }
            }
        }
        nums = list.stream().mapToInt(Integer::intValue).toArray();
        n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] != nums[i + 1]) {
                ++count;
            }
        }
        if (nums[n - 1] == 0) {
            ++count;
        }
        System.out.println(count);
    }
}
