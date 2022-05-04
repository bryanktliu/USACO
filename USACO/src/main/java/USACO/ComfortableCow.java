package USACO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ComfortableCow {

    Map<Integer, Cow> addedCow = new HashMap<>();
    int added = 0;

    public void run() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; ++i) {
            cows[i] = new Cow(sc.nextInt(), sc.nextInt());
        }

        for (int i = 0; i < n; ++i) {
            for (Cow cow : addedCow.values()) {
                cow.visited = false;
            }
            Cow cow = cows[i];
            if (addedCow.containsKey(cow.key)) {
                addedCow.put(cow.key, cow);
                --added;
            } else {
                addCow(cow);
            }
            System.out.println(added);
        }
    }

    private void addCow(Cow cow) {
        addedCow.put(cow.key, cow);
        if (cow.visited) {
            return;
        }
        cow.visited = true;
        addIfComfortable(cow);
        addIfComfortable(addedCow.get(makeKey(cow.x, cow.y - 1)));
        addIfComfortable(addedCow.get(makeKey(cow.x, cow.y + 1)));
        addIfComfortable(addedCow.get(makeKey(cow.x - 1, cow.y)));
        addIfComfortable(addedCow.get(makeKey(cow.x + 1, cow.y)));
    }

    private void addIfComfortable(Cow cow) {
        if (cow == null) {
            return;
        }
        Cow newCow = cow.isComfortable();
        if (newCow != null) {
            ++added;
            addCow(newCow);
        }
    }

    public static int makeKey(int x, int y) {
        return (x + 500) * 2000 + y + 500;
    }

    public static void main(String[] args) {
        new ComfortableCow().run();
    }

    private class Cow {
        int x;
        int y;
        int key;
        boolean visited;

        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
            this.key = makeKey(x, y);
        }

        public Cow isComfortable() {
            int count = 0;
            Cow up = addedCow.get(makeKey(x, y - 1));
            Cow down = addedCow.get(makeKey(x, y + 1));
            Cow left = addedCow.get(makeKey(x - 1, y));
            Cow right = addedCow.get(makeKey(x + 1, y));
            if (up != null) {
                ++count;
            }
            if (down != null) {
                ++count;
            }
            if (left != null) {
                ++count;
            }
            if (right != null) {
                ++count;
            }
            if (count == 3) {
                if (up == null) {
                    return new Cow(x, y - 1);
                } else if (down == null) {
                    return new Cow(x, y + 1);
                } else if (left == null) {
                    return new Cow(x - 1, y);
                } else {
                    return new Cow(x + 1, y);
                }
            }
            return null;
        }
    }
}
