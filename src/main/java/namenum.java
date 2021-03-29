/*
ID: bryan.k1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.Scanner;

public class namenum {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("namenum.in"));
                PrintWriter out = new PrintWriter(new FileWriter("namenum.out"))) {
            Scanner dict = new Scanner(new File("dict.txt"));
            char[] num = scanner.next().toCharArray();
            boolean empty = true;
            while (dict.hasNext()) {
                String word = dict.next();
                if (word.length() != num.length) {
                    continue;
                }
                boolean next = false;
                for (int i = 0; i < word.length(); ++i) {
                    if (toNum(word.charAt(i)) != num[i]) {
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    empty = false;
                    out.println(word);
                }
            }
            if (empty) {
                out.println("NONE");
            }
        }
    }

    public static char toNum(char letter) {
        switch (letter) {
            case 'A':
            case 'B':
            case 'C':
                return '2';
            case 'D':
            case 'E':
            case 'F':
                return '3';
            case 'G':
            case 'H':
            case 'I':
                return '4';
            case 'J':
            case 'K':
            case 'L':
                return '5';
            case 'M':
            case 'N':
            case 'O':
                return '6';
            case 'P':
            case 'R':
            case 'S':
                return '7';
            case 'T':
            case 'U':
            case 'V':
                return '8';
        }
        return '9';
    }
}
