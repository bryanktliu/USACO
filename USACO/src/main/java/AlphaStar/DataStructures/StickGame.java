package AlphaStar.DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StickGame {

    public static int n;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        HashMap<Integer, ArrayList<Integer>> x = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> y = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            ArrayList<Integer> currentx = x.getOrDefault(arr[i][0], new ArrayList<>());
            currentx.add(arr[i][1]);
            x.put(arr[i][0], currentx);
            ArrayList<Integer> currenty = y.getOrDefault(arr[i][1], new ArrayList<>());
            currenty.add(arr[i][0]);
            y.put(arr[i][1], currenty);
        }
        if (x.size() <= 3 || y.size() <= 3) {
            System.out.println(1);
            return;
        }
        if (check(x, y)) {
            System.out.println(1);
            return;
        } else if (check(y, x)) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }

    public static boolean check(
            HashMap<Integer, ArrayList<Integer>> x, HashMap<Integer, ArrayList<Integer>> y) {
        for (Map.Entry<Integer, ArrayList<Integer>> xentry : x.entrySet()) {
            int count = 0;
            for (Map.Entry<Integer, ArrayList<Integer>> yentry : y.entrySet()) {
                if (count > 2) {
                    break;
                }
                for (int pos : yentry.getValue()) {
                    if (pos != xentry.getKey()) {
                        ++count;
                        break;
                    }
                }
            }
            if (count <= 2) {
                return true;
            }
        }
        return false;
    }
}
