import java.io.*;

public class SearchingForSoulmates {

    public static int n;
    public static long[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        arr = new long[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextLong();
            arr[i][1] = nextLong();
        }
        for (long[] cow : arr) {
            if (cow[0] == cow[1]) {
                System.out.println(0);
            } else if (cow[0] > cow[1]) {
                System.out.println(large(cow[0], cow[1]));
            } else {
                System.out.println(small(cow[0], cow[1]));
            }
        }
    }

    public static long small(long cow1, long cow2) {
        long count = 0;
        while (cow2 > cow1 * 2) {
            if (cow2 % 2 == 1) {
                --cow1;
                ++count;
            }
            cow2 /= 2;
            ++count;
        }
        count += calc(cow1, cow2);
        return count;
    }

    public static long large(long cow1, long cow2) {
        long count = 0;
        while (cow1 > cow2) {
            if (cow1 % 2 == 1) {
                ++cow1;
                ++count;
            }
            cow1 /= 2;
            ++count;
        }
        count += calc(cow1, cow2);
        return count;
    }

    public static long calc(long cow1, long cow2) {
        if (cow2 - cow1 < 6) {
            return cow2 - cow1;
        }
        long count = 0;
        if (cow1 % 2 == 1) {
            ++cow1;
            ++count;
        }
        ++count;
        count += calc(cow1 / 2, cow2 / 2);
        ++count;
        if (cow2 % 2 == 1) {
            ++count;
        }
        return count;
    }
}
