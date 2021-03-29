import java.util.Arrays;
import java.util.Scanner;

public class JustStalling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] cow = new int[length];
        for (int i = 0; i < length; ++i) {
            cow[i] = scanner.nextInt();
        }
        Arrays.sort(cow);
        int[] height = new int[length];
        for (int i = 0; i < length; ++i) {
            height[i] = scanner.nextInt();
        }
        Arrays.sort(height);
        long total = 1;
        for (int i = cow.length - 1; i >= 0; --i) {
            int count = 0;
            for (int j = i; j >= 0; --j) {
                if (cow[i] <= height[j]) {
                    ++count;
                } else {
                    break;
                }
            }
            total *= count;
        }
        System.out.println(total);
    }
}
