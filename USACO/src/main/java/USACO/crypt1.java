package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("USACO.crypt1.in"));
                PrintWriter out = new PrintWriter(new FileWriter("USACO.crypt1.out"))) {
            int length = scanner.nextInt();
            Set<Integer> allowed = new HashSet<>();
            int count = 0;
            for (int i = 0; i < length; ++i) {
                allowed.add(scanner.nextInt());
            }
            for (int i : allowed) {
                for (int j : allowed) {
                    for (int k : allowed) {
                        List<Integer> list = new ArrayList<>();
                        int num = (i * 100) + (j * 10) + k;
                        for (int l : allowed) {
                            int result = num * l;
                            if (result > 999) {
                                continue;
                            }
                            if (contains(allowed, result)) {
                                list.add(l);
                            }
                        }
                        for (int l : list) {
                            for (int m : list) {
                                int multiple = (l * 10) + m;
                                int result = num * (multiple);
                                if (result > 9999) {
                                    continue;
                                }
                                if (contains(allowed, result)) {
                                    ++count;
                                }
                            }
                        }
                    }
                }
            }
            out.println(count);
        }
    }

    public static boolean contains(Set<Integer> allowed, int result) {
        char[] digits = Integer.toString(result).toCharArray();
        for (char digit : digits) {
            if (!allowed.contains(digit - '0')) {
                return false;
            }
        }
        return true;
    }
}
