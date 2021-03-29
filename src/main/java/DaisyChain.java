import java.util.HashSet;
import java.util.Scanner;

public class DaisyChain {

    public DaisyChain() {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < length; ++i) {
            nums[i] = scanner.nextInt();
        }
        int total = length;
        for (int i = 0; i < length; ++i) {
            HashSet<Integer> set = new HashSet<>();
            set.add(nums[i]);
            int sum = nums[i];
            for (int j = i + 1; j < length; ++j) {
                set.add(nums[j]);
                sum += nums[j];
                if (sum % (j - i + 1) == 0) {
                    if (set.contains(sum / (j - i + 1))) {
                        ++total;
                    }
                }
            }
        }
        System.out.println(total);
    }
}
