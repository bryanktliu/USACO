import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConnectingTwoBarns {

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
        tokenizer.nextToken();
        int T = (int) tokenizer.nval;
        for (int n = 0; n < T; ++n) {
            tokenizer.nextToken();
            int N = (int) tokenizer.nval;
            tokenizer.nextToken();
            int M = (int) tokenizer.nval;
            int[] map1 = new int[N + 1];
            int[] map2 = new int[N + 1];
            for (int i = 0; i < M; ++i) {
                tokenizer.nextToken();
                int key = (int) tokenizer.nval;
                tokenizer.nextToken();
                int value = (int) tokenizer.nval;
                if (key > value) {
                    int tmp = key;
                    key = value;
                    value = tmp;
                }
                map1[key] = value;
                map2[value] = key;
            }
            List<List<Integer>> lists = new ArrayList<>();
            List<Integer> list1 = null;
            List<Integer> listN = null;
            for (int i = 1; i <= N; ++i) {
                if (map1[i] != -1) {
                    List<Integer> list = getList(map1, i);
                    if (list.get(list.size() - 1) == N) {
                        listN = list;
                    } else if (i == 1) {
                        list1 = list;
                    } else {
                        lists.add(list);
                    }
                }
            }

            int cost = pow(diff(list1, listN));
            for (List<Integer> mid : lists) {
                int cost1 = diff(list1, mid);
                mid.addAll(list1);
                Collections.sort(mid);
                cost = Math.min(cost, pow(cost1) + pow(diff(mid, listN)));
            }
            System.out.println(cost);
        }
    }

    private static int pow(int val) {
        return val * val;
    }

    private static List<Integer> getList(int[] map, int start) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        while (map[start] > 0) {
            int value = map[start];
            list.add(value);
            map[start] = -1;
            start = value;
        }
        map[start] = -1;
        return list;
    }

    private static int getCost(int path1, int path2) {
        return path1 * path1 + path2 * path2;
    }

    private static int diff(List<Integer> list1, List<Integer> list2) {
        int[] arr1 = list1.stream().mapToInt(i -> i).toArray();
        int[] arr2 = list2.stream().mapToInt(i -> i).toArray();
        return diff(arr1, arr2);
    }

    private static int diff(int[] arr, int val) {
        int index = Arrays.binarySearch(arr, val);
        index = -(index + 1);
        if (index == 0) {
            return arr[0] - val;
        } else if (index >= arr.length) {
            return val - arr[arr.length - 1];
        } else {
            return Math.min(val - arr[index - 1], arr[index] - val);
        }
    }

    private static int diff(int[] arr1, int[] arr2) {
        int diff = diff(arr1, arr2[0]);
        return Math.min(diff, diff(arr1, arr2[arr2.length - 1]));
    }
}
