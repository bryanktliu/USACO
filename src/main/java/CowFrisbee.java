import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CowFrisbee {

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
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        long count = 0;
        for (int i = 0; i < n; ++i) {
            int largest = 0;
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] > arr[i]) {
                    count += j - i + 1;
                    break;
                }
                if (arr[j] > largest) {
                    largest = arr[j];
                    count += j - i + 1;
                }
            }
        }
        System.out.println(count);
    }
}
