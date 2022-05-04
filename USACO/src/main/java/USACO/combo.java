package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.combo
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class combo {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.combo.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.combo.out"))) {
            int size = scanner.nextInt();
            Set<String> set = new HashSet<>();
            int[] combo1 = new int[3];
            for (int i = 0; i < 3; ++i) {
                combo1[i] = scanner.nextInt();
            }
            int[] combo2 = new int[3];
            for (int i = 0; i < 3; ++i) {
                combo2[i] = scanner.nextInt();
            }
            findPossible(set, combo1, size);
            findPossible(set, combo2, size);
            out.println(set.size());
        }
    }

    public static void findPossible(Set<String> set, int[] combo, int size) {
        int num1 = previous(previous(combo[0], size), size);
        for (int i = 0; i < 5; ++i) {
            int num2 = previous(previous(combo[1], size), size);
            for (int j = 0; j < 5; ++j) {
                int num3 = previous(previous(combo[2], size), size);
                for (int k = 0; k < 5; ++k) {
                    set.add("" + num1 + ',' + num2 + ',' + num3);
                    num3 = next(num3, size);
                }
                num2 = next(num2, size);
            }
            num1 = next(num1, size);
        }
    }

    public static int previous(int num, int size) {
        if (num == 1) {
            return size;
        }
        return num - 1;
    }

    public static int next(int num, int size) {
        if (num == size) {
            return 1;
        }
        return num + 1;
    }
}
