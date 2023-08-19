package USACO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FindAndReplace {

    public static int t;
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
        t = nextInt();
        for (int subcase = 0; subcase < t; ++subcase) {
            HashMap<Character, Character> map = new HashMap<>();
            char[] original = next().toCharArray();
            char[] target = next().toCharArray();
            boolean impossible = false;
            for (int i = 0; i < original.length; ++i) {
                if (map.containsKey(original[i])) {
                    if (map.get(original[i]) != target[i]) {
                        impossible = true;
                        break;
                    }
                } else {
                    map.put(original[i], target[i]);
                }
            }
            if (impossible) {
                System.out.println(-1);
                continue;
            }
            if (map.size() == 0) {
                System.out.println(0);
                continue;
            }
            Character[] keys = map.keySet().toArray(new Character[0]);
            for (Character key : keys) {
                if (map.get(key) == key) {
                    map.remove(key);
                }
            }
            HashSet<Character> seen = new HashSet<>();
            keys = map.keySet().toArray(new Character[0]);
            int total = keys.length;
            for (Character key : keys) {
                List<Character> loop = new ArrayList<>();
                char val = map.get(key);
                loop.add(key);
                seen.add(key);
                while (map.containsKey(val) && !seen.contains(val)) {
                    loop.add(val);
                    seen.add(val);
                    val = map.get(val);
                    if (seen.contains(val)) {
                        if (loop.size() == 26) {
                            System.out.println(-1);
                        }
                        for (Character k: keys) {
                            if (loop.contains(k)) {
                                continue;
                            }
                            if (loop.contains(map.get(k))) {
                                --total;
                            }
                        }
                        ++total;
                    }
                }
            }
            System.out.println(total);
        }
    }
}
