package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.milk
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class milk {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.milk.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.milk.out"))) {
            int total = scanner.nextInt();
            int farmers = scanner.nextInt();
            int cost = 0;
            int[][] price = new int[farmers][2];
            for (int i = 0; i < farmers; ++i) {
                price[i][0] = scanner.nextInt();
                price[i][1] = scanner.nextInt();
            }
            Arrays.sort(price, Comparator.comparingInt(o -> o[0]));
            for (int i = 0; i < farmers; ++i) {
                if (total <= price[i][1]) {
                    cost += total * price[i][0];
                    break;
                } else {
                    cost += price[i][1] * price[i][0];
                    total -= price[i][1];
                }
            }
            out.println(cost);
        }
    }
}
