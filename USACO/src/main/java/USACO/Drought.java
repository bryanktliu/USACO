package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Drought {

    public static int t;
    public static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        t = nextInt();
        for (int times = 0; times < t; ++times) {
            int n = nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = nextInt();
            }
            long res = 0;
            int dif = 0;
            int min = Integer.MAX_VALUE;
            boolean end = false;
            for (int i = 0; i < n; ++i) {
                min = Math.min(min, arr[i]);
                if (i != n - 1) {
                    if (i % 2 == 0) {
                        if (i != 0) {
                            if (arr[i] - dif < 0) {
                                end = true;
                            } else {
                                res += dif;
                                arr[i] -= dif;
                                min = Math.min(min, arr[i]);
                            }
                        }
                    } else {
                        if (arr[i] < arr[i - 1]) {
                            end = true;
                        } else {
                            dif = arr[i] - arr[i - 1];
                            res += dif;
                            arr[i] -= dif;
                            min = Math.min(min, arr[i]);
                        }
                    }
                } else if (i % 2 == 0) {
                    if (i != 0) {
                        arr[i] -= dif;
                        if (arr[i] < 0) {
                            end = true;
                        }
                        res += dif;
                        min = Math.min(min, arr[i]);
                        if (min != arr[i]) {
                            end = true;
                        }
                    }
                } else if (arr[i - 1] != arr[i]) {
                    end = true;
                }
            }
            if (end) {
                System.out.println(-1);
            } else {
                for (int i = 0; i < n; ++i) {
                    res += arr[i] - min;
                }
                System.out.println(res);
            }
        }
    }
}
