package AlphaStar.DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class AcornSurveillance {

    public static int n;
    public static Camera[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new Camera[n * 2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new Camera(nextInt(), i, true);
            arr[i + n] = new Camera(nextInt(), i, false);
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o.pos));
        int total = 0;
        int count = 0;
        int prev = 0;
        for (int i = 0; i < n * 2; ++i) {
            if (count != 0) {
                total += arr[i].pos - prev;
            }
            if (arr[i].left) {
                ++count;
            } else {
                --count;
            }
            prev = arr[i].pos;
        }
        int[] alone = new int[n];
        prev = 0;
        HashSet<Integer> current = new HashSet<>();
        for (int i = 0; i < n * 2; ++i) {
            if (current.size() == 1) {
                alone[(int) current.toArray()[0]] += arr[i].pos - prev;
            }
            if (arr[i].left) {
                current.add(arr[i].id);
            } else {
                current.remove(arr[i].id);
            }
            prev = arr[i].pos;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            min = Math.min(min, alone[i]);
        }
        System.out.println(total - min);
    }

    public static class Camera {

        int pos;
        int id;
        boolean left;

        public Camera(int pos, int id, boolean left) {
            this.pos = pos;
            this.id = id;
            this.left = left;
        }
    }
}
