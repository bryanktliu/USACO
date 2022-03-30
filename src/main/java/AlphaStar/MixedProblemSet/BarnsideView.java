package AlphaStar.MixedProblemSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class BarnsideView {

    public static int n;
    public static int[] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new int[n + 1];
        arr[n] = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        long res = 0;
        for (int i = 1; i < n + 1; ++i) {
            while (!stack.empty() && arr[i] >= arr[stack.peek()]) {
                res += i - stack.pop() - 1;
            }
            stack.push(i);
        }
        System.out.println(res);
    }
}
