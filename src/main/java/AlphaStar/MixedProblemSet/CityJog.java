package AlphaStar.MixedProblemSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class CityJog {

    public static int l;
    public static int n;
    public static int fspeed;
    public static int bspeed;
    public static int[][] arr;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        nextInt();
        l = 0;
        n = nextInt();
        fspeed = nextInt();
        bspeed = nextInt();
        int dif = fspeed - bspeed;
        arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
            l = Math.max(l, arr[i][0]);
        }
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o2[1], o1[1]));
        int lastpos = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i][0] > lastpos) {
                res += (long) (arr[i][0] - lastpos) * dif * arr[i][1];
                lastpos = arr[i][0];
            }
            if (lastpos == l) {
                break;
            }
        }
        System.out.println(res);
    }
}
