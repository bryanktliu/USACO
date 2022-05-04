package USACO;

import java.util.Scanner;

public class NoTimeToPaint {

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
            char[] skippedleft = new char[skipRange[i][0]];
            System.arraycopy(pattern, 0, skippedleft, 0, skipRange[i][0]);
            char[] skippedright = new char[wallLength - skipRange[i][1] - 1];
            System.arraycopy(
                    pattern,
                    skipRange[i][1] + 1,
                    skippedright,
                    0,
                    wallLength - skipRange[i][1] - 1);
            System.out.println(countTimes(skippedleft) + countTimes(skippedright));
        }
    }

    public static int countTimes(char[] pattern) {
        int[] order = new int[26];
        for (int value : pattern) {
            order[value - 65] = 1;
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
            for (char value : pattern) {
                if (found) {
                    if (value - 65 < i) {
                        found = false;
                    }
                } else {
                    if (value - 65 == i) {
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
