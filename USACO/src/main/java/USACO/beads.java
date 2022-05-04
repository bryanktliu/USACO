package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.beads
*/

import java.io.*;
import java.util.Scanner;

class beads {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("USACO.beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("USACO.beads.out")));
        int beadLength = scanner.nextInt();
        char[] beads = scanner.next().toCharArray();
        int greatest = 0;
        for (int i = 0; i < beadLength; ++i) {
            int count = 0;
            int start = i;
            char first = 'w';
            for (int j = 0; j < beadLength; ++j) {
                if (beads[start] != first) {
                    if (first == 'w') {
                        first = beads[start];
                    } else if (beads[start] != 'w') {
                        count += j;
                        break;
                    }
                }
                if (++start == beadLength) {
                    start = 0;
                }
            }
            if (count == 0) {
                greatest = beadLength;
                break;
            }
            first = 'w';
            start = i;
            for (int j = 0; j < beadLength; ++j) {
                if (--start < 0) {
                    start = beadLength - 1;
                }
                if (beads[start] != first) {
                    if (first == 'w') {
                        first = beads[start];
                    } else if (beads[start] != 'w') {
                        count += j;
                        break;
                    }
                }
            }
            greatest = Math.max(greatest, count);
        }
        out.println(Math.min(greatest, beadLength));
        out.close();
    }
}
