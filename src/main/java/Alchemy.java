import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Alchemy {

    public static int n;
    public static int[] counts;
    public static int k;
    public static HashMap<Integer, List<Integer>> recipe;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        counts = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            counts[i] = nextInt();
        }
        k = nextInt();
        recipe = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            int l = nextInt();
            int m = nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < m; ++j) {
                list.add(nextInt());
            }
            recipe.put(l, list);
        }
        int count = counts[n];
        while (true) {
            if (craft(n)) {
                ++count;
            } else {
                break;
            }
        }
        System.out.println(count);
    }

    public static boolean craft(int target) {
        if (!recipe.containsKey(target)) {
            return false;
        }
        List<Integer> materials = recipe.get(target);
        int size = materials.size();
        for (int material : materials) {
            if (counts[material] > 0) {
                --counts[material];
            } else if (!craft(material)) {
                return false;
            }
        }
        return true;
    }
}
