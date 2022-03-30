package AlphaStar.SearchingAndSorting3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class BunnysBarbeque {

    public static int n;
    public static Bunny[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new Bunny[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = new Bunny(nextLong(), next().equals("S"));
        }
        Arrays.sort(arr, Comparator.comparingLong(o -> o.position));
        long max = 0;
        Bunny last = arr[0];
        for (int i = 1; i < n; ++i) {
            if (last.spot != arr[i].spot) {
                max = Math.max(max, arr[i - 1].position - last.position);
                last = arr[i];
            }
        }
        max = Math.max(max, arr[n - 1].position - last.position);
        int[] preSum = new int[n];
        int prev = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i].spot) {
                preSum[i] = prev + 1;
            } else {
                preSum[i] = prev - 1;
            }
            prev = preSum[i];
        }
        HashMap<Integer, Long> seen = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            if (seen.containsKey(preSum[i])) {
                max = Math.max(max, arr[i].position - seen.get(preSum[i]));
            } else {
                seen.put(preSum[i], arr[i + 1].position);
            }
        }
        if (seen.containsKey(preSum[n - 1])) {
            max = Math.max(max, arr[n - 1].position - seen.get(preSum[n - 1]));
        }
        System.out.println(max);
    }

    public static class Bunny {

        long position;
        boolean spot;

        public Bunny(long position, boolean spot) {
            this.position = position;
            this.spot = spot;
        }
    }
}
