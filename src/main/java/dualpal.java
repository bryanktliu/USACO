/*
ID: bryan.k1
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.Scanner;

public class dualpal {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("dualpal.in"));
                PrintWriter out = new PrintWriter(new FileWriter("dualpal.out"))) {
            int n = scanner.nextInt();
            int lowest = scanner.nextInt();
            int count = 0;
            for (int i = lowest + 1; i < Integer.MAX_VALUE; ++i) {
                if (isDual(i)) {
                    ++count;
                    out.println(i);
                }
                if (count == n) {
                    return;
                }
            }
        }
    }

    public static boolean isDual(int num) {
        int count = 0;
        for (int i = 2; i < 11; ++i) {
            if (isPalindrome(Integer.toString(num, i))) {
                ++count;
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
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
