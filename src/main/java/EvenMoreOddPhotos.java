import java.util.Scanner;

public class EvenMoreOddPhotos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int oddcount = 0;
        int evencount = 0;
        for (int i = 0; i < length; ++i) {
            if (scanner.nextInt() % 2 == 0) {
                ++evencount;
            } else {
                ++oddcount;
            }
        }
        if (oddcount < evencount) {
            System.out.println(oddcount * 2 + 1);
            return;
        }
        int possible = evencount * 2;
        oddcount -= evencount;
        boolean parity = true;
        while (oddcount > 0) {
            if (parity) {
                if (oddcount == 1) {
                    System.out.println(--possible);
                    return;
                }
                oddcount -= 2;
                ++possible;
                parity = false;
            } else {
                --oddcount;
                ++possible;
                parity = true;
            }
        }
        System.out.println(possible);
    }
}
