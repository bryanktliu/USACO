package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.palsquare
*/

import java.io.*;
import java.util.Scanner;

public class palsquare {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.palsquare.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.palsquare.out"))) {
            int base = scanner.nextInt();
            for (int i = 1; i < 301; ++i) {
                String num = Integer.toString(i * i, base).toUpperCase();
                if (isPalindrome(num)) {
                    out.println(Integer.toString(i, base).toUpperCase() + " " + num);
                }
            }
        }
    }

    public static boolean isPalindrome(String num) {
        for (int i = 0; i < num.length() / 2; ++i) {
            if (num.charAt(i) != num.charAt(num.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
