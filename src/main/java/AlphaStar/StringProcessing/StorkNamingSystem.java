package AlphaStar.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class StorkNamingSystem {

    public static long num;
    public static ArrayList<Integer> arr;
    public static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        num = nextLong();
        int[] digit = new int[(int) Math.log10(num) + 1];
        for (int i = (int) Math.log10(num); i >= 0; --i) {
            digit[i] = (int) (num % 10);
            num /= 10;
        }
        String input = next();
        ArrayList<String> out = new ArrayList<>();
        while (input != null) {
            if (input.length() != digit.length) {
                input = next();
                continue;
            }
            char[] chars = input.toCharArray();
            boolean exit = false;
            for (int i = 0; i < chars.length; ++i) {
                if (toNum(chars[i]) != digit[i]) {
                    exit = true;
                    break;
                }
            }
            if (!exit) {
                out.add(input);
            }
            input = next();
        }
        if (out.size() == 0) {
            System.out.println("NONE");
        } else {
            Collections.sort(out);
            for (String i : out) {
                System.out.println(i);
            }
        }
    }

    public static int toNum(char letter) {
        switch (letter) {
            case 'A':
            case 'B':
            case 'C':
                return 2;
            case 'D':
            case 'E':
            case 'F':
                return 3;
            case 'G':
            case 'H':
            case 'I':
                return 4;
            case 'J':
            case 'K':
            case 'L':
                return 5;
            case 'M':
            case 'N':
            case 'O':
                return 6;
            case 'P':
            case 'R':
            case 'S':
                return 7;
            case 'T':
            case 'U':
            case 'V':
                return 8;
        }
        return 9;
    }
}
