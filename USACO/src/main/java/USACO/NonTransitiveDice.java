package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class NonTransitiveDice {

    public static int t;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        t = nextInt();
        for (int times = 0; times < t; ++times) {
            int[] d1 = new int[4];
            for (int i = 0; i < 4; ++i) {
                d1[i] = nextInt();
            }
            int[] d2 = new int[4];
            for (int i = 0; i < 4; ++i) {
                d2[i] = nextInt();
            }
            if (isBetter(d2, d1)) {
                int[] temp = d2.clone();
                d2 = d1.clone();
                d1 = temp.clone();
            }
            boolean found = false;
            end:
            for (int i = 1; i <= 10; ++i) {
                for (int j = 1; j <= 10; ++j) {
                    for (int k = 1; k <= 10; ++k) {
                        for (int l = 1; l <= 10; ++l) {
                            if (isValid(new int[] {i, j, k, l}, d1, d2)) {
                                System.out.println("yes");
                                found = true;
                                break end;
                            }
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("no");
            }
        }
    }

    public static boolean isBetter(int[] d1, int[] d2) {
        int dice1 = 0;
        int dice2 = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (d1[i] > d2[j]) {
                    ++dice1;
                } else if (d2[j] > d1[i]) {
                    ++dice2;
                }
            }
        }
        return dice1 > dice2;
    }

    public static boolean isValid(int[] dice, int[] d1, int[] d2) {
        return isBetter(d2, dice) && isBetter(dice, d1);
    }
}
