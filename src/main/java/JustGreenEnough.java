import java.util.Scanner;

public class JustGreenEnough {

    public JustGreenEnough() {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[][] land = new int[len][len];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                land[i][j] = scanner.nextInt();
            }
        }
        int x = -1;
        int y = -1;
        for (int i = 0; i < len; ++i) {
            boolean found = false;
            for (int j = 0; j < len; ++j) {
                if (land[i][j] == 100) {
                    x = i;
                    y = j;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        int total = 1;
        int uplimit = countUp(land, x, y);
        int downlimit = countDown(land, x, y);
        int leftlimit = countLeft(land, x, y);
        int rightlimit = countRight(land, x, y);
        for (int i = y; i > y - uplimit; --i) {
            leftlimit = Math.min(leftlimit, countLeft(land, x, i));
            rightlimit = Math.min(rightlimit, countRight(land, x, i));
            total += leftlimit * rightlimit - 1;
        }
        for (int i = y; i < y + downlimit - 1; ++i) {
            leftlimit = Math.min(leftlimit, countLeft(land, x, i));
            rightlimit = Math.min(rightlimit, countRight(land, x, i));
            total += leftlimit * rightlimit - 1;
        }
        System.out.println(total);
    }

    public static int countLeft(int[][] land, int x, int y) {
        int count = 0;
        for (int i = x; i > -1; --i) {
            if (land[i][y] >= 100) {
                ++count;
            }
        }
        return count;
    }

    public static int countRight(int[][] land, int x, int y) {
        int count = 0;
        for (int i = x; i < land.length; ++i) {
            if (land[x][i] >= 100) {
                ++count;
            }
        }
        return count;
    }

    public static int countUp(int[][] land, int x, int y) {
        int count = 0;
        for (int i = y; i > -1; --i) {
            if (land[x][i] >= 100) {
                ++count;
            }
        }
        return count;
    }

    public static int countDown(int[][] land, int x, int y) {
        int count = 0;
        for (int i = y; i < land.length; ++i) {
            if (land[y][i] >= 100) {
                ++count;
            }
        }
        return count;
    }
}
