package AlphaStar.Final;

import java.io.*;
import java.util.*;

public class UnfriendlyFriends {

    public static int n;
    public static int k;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        k = nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k * 2; i += 2) {
            int cow1 = nextInt();
            int cow2 = nextInt();
            map.put(cow1, cow2);
            map.put(cow2, cow1);
        }
        int count = 1;
        HashSet<Integer> seen = new HashSet<>();
        int[] keys = new int[map.size()];
        int index = 0;
        for (int key : map.keySet()) {
            keys[index] = key;
            ++index;
        }
        Arrays.sort(keys);
        for (int pos : keys) {
            if (seen.contains(map.get(pos))) {
                ++count;
                seen = new HashSet<>();
            }
            seen.add(pos);
        }
        System.out.println(count);
    }
}
