package USACO;

import java.io.*;

public class AirCownditioning {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(System.out);
        int num = nextInt();
        int[] differences = new int[num];
        for (int i = 0; i < num; ++i) {
            differences[i] = nextInt();
        }
        for (int i = 0; i < num; ++i) {
            differences[i] -= nextInt();
        }
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < num; ++i) {
            smallest = Math.min(differences[i], smallest);
        }
        for (int i = 0; i < num; i++) {
            differences[i] -= smallest;
        }
        out.println(minNumberOperations(differences));
        out.close();
    }

    public static int minNumberOperations(int[] target) {
        int res = 0, pre = 0;
        for (int a : target) {
            res += Math.max(a - pre, 0);
            pre = a;
        }
        return res;
    }
}
