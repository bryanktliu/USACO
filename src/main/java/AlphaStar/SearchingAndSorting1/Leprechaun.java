package AlphaStar.SearchingAndSorting1;

import java.io.*;

public class Leprechaun {

    public static int n;
    public static int[][] arr;
    public static StreamTokenizer in;
    public static int res = Integer.MIN_VALUE;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[i][j] = nextInt();
            }
        }
        for (int i = 0; i < n; ++i) {
            col(i);
            row(i);
            ldiag(i);
            rdiag(i);
        }
        System.out.println(res);
    }

    public static void col(int x) {
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = arr[i][x];
        }
        calcSum(nums);
    }

    public static void row(int y) {
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = arr[y][i];
        }
        calcSum(nums);
    }

    public static void ldiag(int y) {
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = arr[i][(y - i + n) % n];
        }
        calcSum(nums);
    }

    public static void rdiag(int y) {
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = arr[i][(y + i) % n];
        }
        calcSum(nums);
    }

    public static void calcSum(int[] nums) {
        int max = nums[0];
        int current = 0;
        for (int i = 0; i < n; ++i) {
            current = Math.max(nums[i], current + nums[i]);
            max = Math.max(current, max);
        }
        int min = nums[0];
        current = 0;
        for (int i = 0; i < n; ++i) {
            current = Math.min(nums[i], current + nums[i]);
            min = Math.min(current, min);
        }
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total - min == 0) {
            res = Math.max(res, max);
        } else {
            res = Math.max(res, Math.max(max, total - min));
        }
    }
}
