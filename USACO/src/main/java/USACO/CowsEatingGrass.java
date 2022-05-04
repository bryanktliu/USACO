package USACO;

import java.util.Scanner;

public class CowsEatingGrass {

    public CowsEatingGrass() {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        char[] direction = new char[num];
        int[][] position = new int[num][2];
        for (int i = 0; i < num; ++i) {
            direction[i] = scanner.next().charAt(0);
            position[i][1] = scanner.nextInt();
            position[i][0] = scanner.nextInt();
        }
        for (int i = 0; i < num; ++i) {
            int eaten = Integer.MAX_VALUE;
            int[] first = position[i];
            for (int j = 0; j < num; ++j) {
                if (i == j) {
                    continue;
                }
                int[] second = position[j];
                if (direction[i] == 'E') {
                    if (first[1] <= second[1]
                            && first[0] >= second[0]
                            && second[1] - first[1] > first[0] - second[0]) {
                        int dif = second[1] - first[1];
                        if (eaten > dif) {
                            eaten = dif;
                        }
                    }
                } else {
                    if (first[1] >= second[1]
                            && first[0] >= second[0]
                            && second[1] - first[1] > first[0] - second[0]) {
                        int dif = second[0] - first[0];
                        if (eaten > dif) {
                            eaten = dif;
                        }
                    }
                }
            }
            if (eaten == Integer.MAX_VALUE) {
                System.out.println("Infinity");
            } else {
                System.out.println(eaten);
            }
        }
    }
}
