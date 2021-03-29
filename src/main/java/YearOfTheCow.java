import java.util.Arrays;
import java.util.Scanner;

public class YearOfTheCow {

    public YearOfTheCow() {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ancestors = scanner.nextInt();
        int jumps = scanner.nextInt();
        int[] times = new int[ancestors];
        for (int i = 0; i < ancestors; ++i) {
            times[i] = scanner.nextInt();
        }
        Arrays.sort(times);
        int year = 0;
        int[] difs = new int[ancestors];
        for (int i = 0; i < ancestors; ++i) {
            difs[i] = times[i] - year;
            year = times[i];
        }
        int total = times[ancestors - 1] - times[0];
        Arrays.sort(difs);
        for (int i = 0; i < ancestors - jumps + 1; ++i) {
            total -= difs[ancestors - i - 1] - difs[ancestors - i - 1] % 12;
        }
    }
}
