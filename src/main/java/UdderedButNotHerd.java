import java.util.HashMap;
import java.util.Scanner;

public class UdderedButNotHerd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cowphabet = scanner.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < cowphabet.length(); ++i) {
            map.put(cowphabet.charAt(i), i);
        }
        char[] heard = scanner.next().toCharArray();
        int count = 1;
        for (int i = 1; i < heard.length; ++i) {
            if (map.get(heard[i - 1]) >= map.get(heard[i])) {
                ++count;
            }
        }
        System.out.println(count);
    }
}
