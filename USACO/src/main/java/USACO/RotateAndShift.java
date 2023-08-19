package USACO;

import java.util.Arrays;

public class RotateAndShift {

    public static void main(String[] args) {
        int[] a = {0, 2, 3};
        int[] order = {0, 1, 2, 3, 4};
        rotate(order, a);
        System.out.println(Arrays.toString(order));
        while (!same(order)) {
            rotate(order, a);
            System.out.println(Arrays.toString(order));
        }
    }

    public static void rotate(int[] order, int[] a) {
        int temp = order[a[a.length - 1]];
        for (int i = a.length - 1; i > 0; --i) {
            order[a[i]] = order[a[i - 1]];
        }
        order[a[0]] = temp;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == order.length - 1) {
                a[i] = 0;
            } else {
                ++a[i];
            }
        }
    }

    public static boolean same(int[] order) {
        for (int i = 0; i < 5; ++i) {
            if (order[i] != i) {
                return false;
            }
        }
        return true;
    }
}
