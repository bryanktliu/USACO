import java.util.Scanner;

public class Cowntagion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int farms = scanner.nextInt();
        int[] count = new int[100001];
        for (int i = 0; i < (farms - 1) * 2; ++i) {
            ++count[scanner.nextInt()];
        }
        int days = farms - 1;
        for (int i = 0; i < count.length; ++i) {
            int value = count[i];
            if (i != 1) {
                --value;
            }
            if (value != -1 && value != 0) {
                days += Math.log10(value) / Math.log10(2) + 1;
            }
        }
        System.out.println(days);
    }
}
