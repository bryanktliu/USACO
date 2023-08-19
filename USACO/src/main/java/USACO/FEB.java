package USACO;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class FEB {

    public static TreeSet<Integer> ammounts = new TreeSet<>();
    public static char[] line;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        line = sc.next().toCharArray();
        ArrayList<type> lengths = new ArrayList<>();
        int start = -1;
        int count = 0;
        for (int i = 0; i < line.length; ++i) {
            if (start == -1) {
                if (line[i] == 'F') {
                    start = i;
                }
            } else {
                 if (line[i] != 'F') {
                    int before = start - 1;
                     int len = i - start;
                    lengths.add(new type(before, i, len));
                    start = -1;
                }
            }
            if (i != 0) {
                if (line[i] == line[i - 1] && line[i] != 'F') {
                    ++count;
                }
            }
        }
        if (line[line.length - 1] == 'F') {
            int before = start - 1;
            int len = line.length - start;
            lengths.add(new type(before, line.length, len));
        }
        possible(lengths, 0, count);
        System.out.println(ammounts.size());
        while (ammounts.size() > 0) {
            int a = ammounts.first();
            System.out.println(a);
            ammounts.remove(a);
        }
    }

    public static void possible(ArrayList<type> lengths, int i, int current) {
        if (i == lengths.size()) {
            ammounts.add(current);
            return;
        }
        type a = lengths.get(i);
        if (a.before == -1 || a.after == line.length) {
            for (int j = 0; j < a.len + 1; ++j) {
                possible(lengths, i + 1, current + j);
            }
        } else if (line[a.before] == line[a.after]) {
            if (a.len % 2 == 0) {
                ++current;
            }
            for (int j = 0; j < (a.len + 1) / 2 + 1; ++j) {
                possible(lengths,i + 1, current + j * 2);
            }
        } else {
            if (a.len % 2 == 1) {
                ++current;
            }
            for (int j = 0; j < a.len / 2 + 1; ++j) {
                possible(lengths,i + 1, current + j * 2);
            }
        }
    }

    public static class type {

        public int before;
        public int after;
        public int len;

        public type(int before, int after, int len) {
            this.before = before;
            this.after = after;
            this.len = len;
        }
    }
}
