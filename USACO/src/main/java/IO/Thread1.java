package IO;

import java.util.ArrayList;
import java.util.List;

public class Thread1 extends Thread {

    public Num number;

    public Thread1(Num number) {
        this.number = number;
    }

    public Num getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread1> list = new ArrayList<>();
        Num n = new Num();
        for (int i = 0; i < 10; ++i) {
            Thread1 t = new Thread1(n);
            list.add(t);
            t.start();
        }
        for (Thread1 t : list) {
            t.join();
        }
        System.out.println(n.num);
        System.exit(1);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            number.increment();
        }
    }

    private static class Num {

        int num;

        synchronized void increment() {
            num++;
        }

        synchronized void decrement() {
            num--;
        }
    }
}
