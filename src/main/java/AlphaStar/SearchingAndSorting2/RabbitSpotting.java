package AlphaStar.SearchingAndSorting2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class RabbitSpotting {

    public static int n;
    public static int a;
    public static int b;
    public static Rabbit[] arr;
    public static StreamTokenizer in;
    public static int res;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = nextInt();
        a = nextInt();
        b = nextInt();
        res = b - a + 1;
        arr = new Rabbit[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = new Rabbit(next().equals("S"), nextInt());
        }
        Arrays.sort(arr);
        int start = findStart();
        int end = findEnd();
        if (res <= 0) {
            System.out.print(0);
            return;
        }
        for (int i = start; i < end; ++i) {
            if (!arr[i].spot) {
                if (!arr[i + 1].spot) {
                    res -= arr[i + 1].weight - arr[i].weight;
                } else {
                    res -= (arr[i + 1].weight - arr[i].weight + 1) / 2;
                }
            } else if (!arr[i + 1].spot) {
                res -= (arr[i + 1].weight - arr[i].weight + 1) / 2 - 1;
            }
        }
        System.out.print(res);
    }

    public static int findStart() {
        int start = Arrays.binarySearch(arr, new Rabbit(false, a));
        if (start < 0) {
            start = -(start + 1);
            if (start == 0) {
                if (!arr[0].spot) {
                    res -= arr[0].weight - a;
                }
            } else if (start == n) {
                res = 0;
            } else if (!arr[start - 1].spot) {
                if (!arr[start].spot) {
                    res -= arr[start].weight - a;
                } else {
                    res -= Math.max(0, (arr[start - 1].weight + arr[start].weight + 1) / 2 - a);
                }
            } else if (!arr[start].spot) {
                res -=
                        Math.min(
                                (arr[start].weight - arr[start - 1].weight + 1) / 2 - 1,
                                arr[start].weight - a);
            }
        }
        return start;
    }

    public static int findEnd() {
        int end = Arrays.binarySearch(arr, new Rabbit(false, b));
        if (end < 0) {
            end = -(end + 1);
            if (end == n) {
                if (!arr[n - 1].spot) {
                    res -= b - arr[n - 1].weight + 1;
                }
            } else if (end == 0) {
                res = 0;
            } else if (!arr[end].spot) {
                if (!arr[end - 1].spot) {
                    res -= b - arr[end - 1].weight + 1;
                } else {
                    res -= Math.max(0, b - ((arr[end].weight + arr[end - 1].weight) / 2));
                }
            } else if (!arr[end - 1].spot) {
                res -=
                        Math.min(
                                b - arr[end - 1].weight + 1,
                                (arr[end].weight - arr[end - 1].weight + 1) / 2);
            }
            --end;
        } else if (!arr[end].spot) {
            --res;
        }
        return end;
    }

    public static class Rabbit implements Comparable<Rabbit> {

        boolean spot;
        int weight;

        public Rabbit(boolean spot, int weight) {
            this.spot = spot;
            this.weight = weight;
        }

        @Override
        public int compareTo(Rabbit rabbit) {
            return Integer.compare(weight, rabbit.weight);
        }
    }
}
