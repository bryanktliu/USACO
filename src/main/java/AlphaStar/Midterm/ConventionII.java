package AlphaStar.Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ConventionII {

    public static int n;
    public static Cow[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new Cow[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = new Cow(nextInt(), nextInt(), i);
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o.arrive));
        TreeMap<Integer, Cow> queue = new TreeMap<>();
        queue.put(0, arr[0]);
        int index = 1;
        int res = 0;
        int end = arr[0].arrive;
        while (index < n) {
            if (queue.isEmpty()) {
                Cow cow = arr[index];
                queue.put(cow.seniority, cow);
                end = cow.arrive;
                ++index;
            } else {
                Map.Entry<Integer, Cow> current = queue.firstEntry();
                queue.remove(current.getKey());
                while (index < n) {
                    Cow cow = arr[index];
                    if (cow.arrive <= end + current.getValue().time) {
                        queue.put(cow.seniority, cow);
                        ++index;
                    } else {
                        break;
                    }
                }
                res = Math.max(res, end - current.getValue().arrive);
                end += current.getValue().time;
            }
        }
        System.out.println(res);
    }

    public static class Cow {

        int arrive;
        int time;
        int seniority;

        public Cow(int arrive, int time, int seniority) {
            this.arrive = arrive;
            this.time = time;
            this.seniority = seniority;
        }
    }
}
