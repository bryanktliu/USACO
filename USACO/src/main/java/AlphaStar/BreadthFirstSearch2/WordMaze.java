package AlphaStar.BreadthFirstSearch2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class WordMaze {

    public static String start;
    public static String end;
    public static HashSet<String> dict = new HashSet<>();
    public static StreamTokenizer in;

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        start = next();
        end = next();
        int len = start.length();
        String input = next();
        while (input != null) {
            if (input.length() == len) {
                dict.add(input);
            }
            input = next();
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int count = 0;
        here:
        while (!queue.isEmpty()) {
            ++count;
            int repeat = queue.size();
            for (int i = 0; i < repeat; ++i) {
                String current = queue.poll();
                for (int j = 0; j < len; ++j) {
                    for (int k = 'a'; k <= 'z'; ++k) {
                        String word = current.substring(0, j) + (char) k + current.substring(j + 1);
                        if (dict.contains(word)) {
                            if (word.equals(end)) {
                                break here;
                            }
                            dict.remove(word);
                            queue.add(word);
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
