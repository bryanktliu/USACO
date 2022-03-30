package AlphaStar.DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AcornFarm {

    public static int n;
    public static int acorns;
    public static int[][] arr;
    public static BufferedReader in;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        arr = new int[n][3];
        for (int i = 0; i < n; ++i) {
            StringTokenizer temp = new StringTokenizer(in.readLine());
            arr[i][0] = Integer.parseInt(temp.nextToken());
            arr[i][1] = Integer.parseInt(temp.nextToken());
            arr[i][2] = Integer.parseInt(temp.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(arr[i][1], 0);
        }
        TreeMap<Integer, Integer> set = new TreeMap<>();
        set.put(0, n + 1);
        int count = 0;
        for (int i = 0; i < n; ++i) {
            int current = map.get(arr[i][1]);
            if (set.lastKey() == current) {
                if (set.get(current) > 1) {
                    ++count;
                } else if (arr[i][2] < 0) {
                    set.remove(current);
                    if (current + arr[i][2] <= set.lastKey()) {
                        ++count;
                    }
                    set.put(current, 1);
                }
            } else if (current + arr[i][2] >= set.lastKey()) {
                ++count;
            }
            map.put(arr[i][1], map.get(arr[i][1]) + arr[i][2]);
            if (set.get(current) == 1) {
                set.remove(current);
            } else {
                set.put(current, set.get(current) - 1);
            }
            set.put(current + arr[i][2], set.getOrDefault(current + arr[i][2], 0) + 1);
        }
        System.out.println(count);
    }
}
