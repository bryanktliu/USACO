/*
ID: bryan.k1
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class milk2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        int length = scanner.nextInt();
        int[][] time = new int[length][2];
        for (int i = 0; i < length; ++i) {
            time[i][0] = scanner.nextInt();
            time[i][1] = scanner.nextInt();
        }
        Arrays.sort(time, Comparator.comparingInt(o -> o[0]));
        int start = time[0][0];
        int end = time[0][1];
        int ontime = 0;
        int offtime = 0;
        for (int i = 1; i < length; ++i) {
            if (time[i][0] >= start && time[i][0] <= end) {
                end = Math.max(end, time[i][1]);
            } else {
                ontime = Math.max(ontime, end - start);
                offtime = Math.max(offtime, time[i][0] - end);
                start = time[i][0];
                end = time[i][1];
            }
        }
        ontime = Math.max(ontime, end - start);
        out.println(ontime + " " + offtime);
        out.close();
    }
}
