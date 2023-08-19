package USACO;

public class rand {

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print((int) (Math.random() * 1000) + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print((int) (Math.random() * 1000) + " ");
            }
            System.out.println();
        }
    }
}
