package USACO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CowLibi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        int n = sc.nextInt();
        int[][] grazing = new int[g][3];
        for (int i = 0; i < g; ++i) {
            grazing[i][0] = sc.nextInt();
            grazing[i][1] = sc.nextInt();
            grazing[i][2] = sc.nextInt();
        }
        Arrays.sort(grazing, Comparator.comparingInt(o -> o[2]));
        int[] gt = new int[g];
        for (int i = 0; i < g; ++i) {
            gt[i] = grazing[i][2];
        }
        int innocent = 0;
        for (int i = 0; i < n; ++i) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int t = sc.nextInt();
            int index = Arrays.binarySearch(gt, t);
            if (index > -1) {
                if (grazing[index][0] != x || grazing[index][1] != y) {
                    ++innocent;
                }
                continue;
            }
            index = -(index + 1);
            if (index > 0 && cantReach(grazing[index - 1], x, y, t)) {
                ++innocent;
            } else if (index < g && cantReach(grazing[index], x, y, t)) {
                ++innocent;
            }
        }
        System.out.println(innocent);
    }

    public static boolean cantReach(int[] grazing, int x, int y, int time) {
        return Math.abs(grazing[0] - x) + Math.abs(grazing[1] - y) > Math.abs(grazing[2] - time);
    }
}
