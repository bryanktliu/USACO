package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.skidesign
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class skidesign {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.skidesign.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.skidesign.out"))) {
            int hills = scanner.nextInt();
            int[] heights = new int[hills];
            for (int i = 0; i < hills; ++i) {
                heights[i] = scanner.nextInt();
            }
            Arrays.sort(heights);
            int least = Integer.MAX_VALUE;
            for (int i = 0; i < heights[hills - 1]; i++) {
                int cost = 0;
                for (int num : heights) {
                    if (num < i) {
                        cost += (i - num) * (i - num);
                    } else if (num > i + 17) {
                        cost += (num - (i + 17)) * (num - (i + 17));
                    }
                }
                least = Math.min(least, cost);
            }
            out.println(least);
        }
    }
}
