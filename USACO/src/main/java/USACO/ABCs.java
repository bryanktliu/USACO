package USACO;

import java.util.Scanner;

public class ABCs {

    public ABCs() {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = Integer.MAX_VALUE;
        int B = Integer.MAX_VALUE;
        int C = 0;
        for (int i = 0; i < 7; ++i) {
            int input = scanner.nextInt();
            if (input < A) {
                B = A;
                A = input;
            } else if (input < B) {
                B = input;
            }
            if (input > C) {
                C = input;
            }
        }
        C -= A + B;
        System.out.println(A + " " + B + ' ' + C);
    }
}
