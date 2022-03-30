package AlphaStar.MixedProblemSet3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class StudentID {

    public static int n;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        int[] first = {0, -1, -1, -1, -1, -1, -1};
        int presum = 0;
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            int num = nextInt();
            presum = (presum + num) % 7;
            if (first[presum] == -1) {
                first[presum] = i;
            } else {
                res = Math.max(res, i - first[presum]);
            }
        }
        System.out.println(res);
    }
}
