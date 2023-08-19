package USACO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HungryCow {

    public static Scanner sc;
    public static int n;
    public static long t;


    public static void main(String[] args) {
        sc = new Scanner(System.in);
        n = sc.nextInt();
        t = sc.nextLong();
        long total;
        if (t < 100001) {
            total = solveSlow();
        } else {
            total = solveFast();
        }
        System.out.println(total);
    }

    public static long solveSlow() {
        int[] time = new int[(int) t + 1];
        for (int i = 0; i < n; ++i) {
            time[sc.nextInt()] = sc.nextInt();
        }
        for (int i = 1; i < time.length; ++i) {
            time[i] += Math.max(0, time[i - 1] - 1);
        }
        int total = 0;
        for (int i : time) {
            if (i > 0) {
                ++total;
            }
        }
        return total;
    }

    public static long solveFast() {
        List<Long> day = new ArrayList<>();
        List<Integer> bale = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            long d = sc.nextLong();
            int b = sc.nextInt();
            if (d > t) {
                break;
            } else if (d == t) {
                day.add(d);
                bale.add(b);
                break;
            }
            day.add(d);
            bale.add(b);
        }
        long remainder = bale.get(0) - 1;
        long total = 1;
        if (day.get(day.size() - 1) < t) {
            day.add(t);
            bale.add(0);
            --total;
        }
        for (int i = 1; i < day.size(); ++i) {
            ++total;
            long eaten = Math.min(remainder, day.get(i) - day.get(i - 1) - 1);
            total += eaten;
            remainder += bale.get(i) - 1 - eaten;
        }
        return total;
    }
}
