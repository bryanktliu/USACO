package USACO;

import java.util.Scanner;

public class NoTimeToPaint2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wallLength = scanner.nextInt();
        int skipCount = scanner.nextInt();
        char[] pattern = scanner.next().toCharArray();
        int[][] skipRange = new int[skipCount][2];
        for (int i = 0; i < skipCount; ++i) {
            skipRange[i][0] = scanner.nextInt() - 1;
            skipRange[i][1] = scanner.nextInt() - 1;
        }
        for (int i = 0; i < skipCount; ++i) {
            System.out.println(
                    countStart(pattern, skipRange[i][0]) + countEnd(pattern, skipRange[i][1]));
        }
    }

    public static int countStart(char[] pattern, int start) {
        int[] order = new int[26];
        for (int i = 0; i < start; ++i) {
            order[pattern[i] - 65] = 1;
        }
        int count = 0;
        int foundtimes = 0;
        for (int i = 0; i < order.length; ++i) {
            if (foundtimes == start) {
                break;
            }
            if (order[i] == 0) {
                continue;
            }
            boolean found = false;
            for (int j = 0; j < start; ++j) {
                if (found) {
                    if (pattern[j] - 65 < i) {
                        found = false;
                    }
                } else {
                    if (pattern[j] - 65 == i) {
                        ++count;
                        found = true;
                        ++foundtimes;
                    }
                }
            }
        }
        return count;
    }

    public static int countEnd(char[] pattern, int end) {
        int[] order = new int[26];
        for (int i = end + 1; i < pattern.length; i++) {
            order[pattern[i] - 65] = 1;
        }
        int count = 0;
        int foundtimes = 0;
        for (int i = 0; i < order.length; ++i) {
            if (foundtimes == pattern.length) {
                break;
            }
            if (order[i] == 0) {
                continue;
            }
            boolean found = false;
            for (int j = end + 1; j < pattern.length; ++j) {
                if (found) {
                    if (pattern[j] - 65 < i) {
                        found = false;
                    }
                } else {
                    if (pattern[j] - 65 == i) {
                        ++count;
                        found = true;
                        ++foundtimes;
                    }
                }
            }
        }
        return count;
    }
}
