package USACO;

import java.util.Scanner;

public class WatchingMooLoo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long cost = k + 1;
        long last = sc.nextLong();
        for (int i = 1; i < n; ++i) {
            long current = sc.nextLong();
            cost += Math.min(current - last, k + 1);
            last = current;
        }
        System.out.println(cost);
    }
}
