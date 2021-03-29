import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ComfortableCows {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int count = 0;
        boolean[][] field = new boolean[2000][2000];
        for (int i = 0; i < len; ++i) {
            int x = scanner.nextInt() + 500;
            int y = scanner.nextInt() + 500;
            if (field[x][y]) {
                --count;
                continue;
            }
            Set<String> visited = new HashSet<>();
            visited.add(x + " " + y);
            field[x][y] = true;
            count += isComfortable(visited, field, x, y);
            System.out.println(count);
        }
    }

    public static int isComfortable(Set<String> visited, boolean[][] field, int x, int y) {
        boolean south = false;
        boolean east = false;
        boolean west = false;
        int count = 0;
        int comfort = 0;
        if (field[x - 1][y] && !visited.contains(x - 1 + " " + y)) {
            visited.add(x - 1 + " " + y);
            count += isComfortable(visited, field, x - 1, y);
            west = true;
            ++comfort;
        }
        if (field[x + 1][y] && !visited.contains(x + 1 + " " + y)) {
            visited.add(x + 1 + " " + y);
            count += isComfortable(visited, field, x + 1, y);
            east = true;
            ++comfort;
        }
        if (field[x][y - 1] && !visited.contains(x + " " + (y - 1))) {
            visited.add(x + " " + (y - 1));
            count += isComfortable(visited, field, x, y - 1);
            south = true;
            ++comfort;
        }
        if (field[x][y + 1] && !visited.contains(x + " " + (y + 1))) {
            visited.add(x + " " + (y + 1));
            count += isComfortable(visited, field, x, y + 1);
            ++comfort;
        }
        if (comfort == 3) {
            ++count;
            visited.clear();
            if (!west) {
                visited.add(x - 1 + " " + y);
                field[x - 1][y] = true;
                count += isComfortable(visited, field, x - 1, y);
            } else if (!east) {
                visited.add(x + 1 + " " + y);
                field[x + 1][y] = true;
                count += isComfortable(visited, field, x + 1, y);
            } else if (!south) {
                visited.add(x + " " + (y - 1));
                field[x][y - 1] = true;
                count += isComfortable(visited, field, x, y - 1);
            } else {
                visited.add(x + " " + (y + 1));
                field[x][y + 1] = true;
                count += isComfortable(visited, field, x, y + 1);
            }
        }
        return count;
    }
}
