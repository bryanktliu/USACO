import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DanceMooves {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int turns = scanner.nextInt();
        int[] cows = new int[num];
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < num; ++i) {
            cows[i] = i;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            map.put(i, set);
        }
        for (int i = 0; i < turns; ++i) {
            int pos1 = scanner.nextInt() - 1;
            int pos2 = scanner.nextInt() - 1;
            swap(cows, pos1, pos2);
            map.get(cows[pos1]).add(pos1);
            map.get(cows[pos2]).add(pos2);
        }
        for (int i = 0; i < cows.length; ++i) {
            if (i != cows[i]) {
                int index = i;
                Set<Integer> set = new HashSet<>();
                Set<Integer> visited = new HashSet<>();
                while (index != cows[index] && !visited.contains(index)) {
                    visited.add(index);
                    set.addAll(map.get(index));
                    for (int j = 0; j < cows.length; ++j) {
                        if (cows[j] == index) {
                            index = j;
                            break;
                        }
                    }
                }
                System.out.println(set.size());
            } else {
                System.out.println(map.get(i).size());
            }
        }
    }

    public static void swap(int[] current, int pos1, int pos2) {
        int temp = current[pos1];
        current[pos1] = current[pos2];
        current[pos2] = temp;
    }
}
