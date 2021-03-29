/*
ID: bryan.k1
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.Scanner;

class friday {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        int years = scanner.nextInt();
        int[] frequency = new int[7];
        int day = 0;
        for (int i = 0; i < years; ++i) {
            for (int j = 1; j < 13; ++j) {
                ++frequency[day % 7];
                switch (j) {
                    case 2:
                        if (isLeapYear(1900 + i)) {
                            day += 29;
                        } else {
                            day += 28;
                        }
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        day += 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        day += 30;
                        break;
                }
            }
        }
        out.println(
                frequency[0]
                        + " "
                        + frequency[1]
                        + " "
                        + frequency[2]
                        + " "
                        + frequency[3]
                        + " "
                        + frequency[4]
                        + " "
                        + frequency[5]
                        + " "
                        + frequency[6]);
        out.close();
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
}
