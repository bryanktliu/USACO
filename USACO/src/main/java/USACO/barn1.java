package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.barn1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class barn1 {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.barn1.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.barn1.out"))) {
            int boards = scanner.nextInt();
            int stalls = scanner.nextInt();
            int cows = scanner.nextInt();
            int count = cows;
            int[] cowStalls = new int[cows];
            for (int i = 0; i < cows; ++i) {
                cowStalls[i] = scanner.nextInt();
            }
            Arrays.sort(cowStalls);
            ArrayList<Integer> emptyStalls = countEmpty(cowStalls);
            if (emptyStalls.size() + 1 > boards) {
                for (int i = 0; i < (emptyStalls.size() + 1) - boards; ++i) {
                    count += emptyStalls.get(i);
                }
            }
            out.println(count);
        }
    }

    public static ArrayList<Integer> countEmpty(int[] cowStalls) {
        ArrayList<Integer> emptyStalls = new ArrayList<>();
        for (int i = 1; i < cowStalls.length; ++i) {
            if (cowStalls[i] != cowStalls[i - 1] + 1) {
                emptyStalls.add(cowStalls[i] - cowStalls[i - 1] - 1);
            }
        }
        emptyStalls.sort(null);
        return emptyStalls;
    }
}
