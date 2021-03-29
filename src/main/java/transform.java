/*
ID: bryan.k1
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class transform {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("transform.in"));
                PrintWriter out = new PrintWriter(new FileWriter("transform.out"))) {
            int length = scanner.nextInt();
            char[][] current = new char[length][length];
            for (int i = 0; i < length; ++i) {
                current[i] = scanner.next().toCharArray();
            }
            char[][] result = new char[length][length];
            for (int i = 0; i < length; ++i) {
                result[i] = scanner.next().toCharArray();
            }
            char[][] rotate90 = rotate90(current);
            if (Arrays.deepEquals(rotate90, result)) {
                out.println(1);
                return;
            }
            char[][] rotate180 = rotate90(rotate90);
            if (Arrays.deepEquals(rotate180, result)) {
                out.println(2);
                return;
            }
            char[][] rotate270 = rotate90(rotate180);
            if (Arrays.deepEquals(rotate270, result)) {
                out.println(3);
                return;
            }
            if (isReflect(current, result)) {
                out.println(4);
                return;
            }
            if (isReflect(rotate90, result)
                    || isReflect(rotate180, result)
                    || isReflect(rotate270, result)) {
                out.println(5);
                return;
            }
            if (Arrays.deepEquals(current, result)) {
                out.println(6);
                return;
            }
            out.println(7);
        }
    }

    public static char[][] rotate90(char[][] array) {
        int n = array.length;
        char[][] result = new char[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result[j][n - i - 1] = array[i][j];
            }
        }
        return result;
    }

    public static boolean isReflect(char[][] current, char[][] result) {
        int n = current.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (current[i][n - j - 1] != result[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
