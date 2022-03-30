import java.io.*;
import java.util.Scanner;

public class WalkingHome {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int size = scanner.nextInt();
            int turns = scanner.nextInt();
            char[][] barn = new char[size][size];
            for (int j = 0; j < size; ++j) {
                barn[j] = scanner.next().toCharArray();
            }
            out.println(findPath(turns, barn, 0, 0, 0, -1));
        }
        out.close();
    }

    public static int findPath(
            int turns, char[][] barn, int turned, int xpos, int ypos, int direction) {
        if (turned > turns) {
            return 0;
        }
        if (xpos == barn[0].length - 1 && ypos == barn[0].length - 1) {
            return 1;
        }
        int paths = 0;
        if (ypos != barn[0].length - 1 && barn[ypos + 1][xpos] != 'H') {
            if (direction == 0) {
                paths += findPath(turns, barn, turned + 1, xpos, ypos + 1, 1);
            } else {
                paths += findPath(turns, barn, turned, xpos, ypos + 1, 1);
            }
        }
        if (xpos != barn[0].length - 1 && barn[ypos][xpos + 1] != 'H') {
            if (direction == 1) {
                paths += findPath(turns, barn, turned + 1, xpos + 1, ypos, 0);
            } else {
                paths += findPath(turns, barn, turned, xpos + 1, ypos, 0);
            }
        }
        return paths;
    }
}
