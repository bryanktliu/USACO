package USACO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class MooLanguage {

    public static StreamTokenizer in;

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
        int t = nextInt();
        for (int time = 0; time < t; ++time) {
            int n = nextInt();
            int c = nextInt();
            int p = nextInt();
            ArrayList<String> noun = new ArrayList<>();
            ArrayList<String> iv = new ArrayList<>();
            ArrayList<String> tv = new ArrayList<>();
            ArrayList<String> conj = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                String[] l = next().split(" ");
                switch (l[1]) {
                    case "noun":
                        noun.add(l[0]);
                        break;
                    case "transitive-verb":
                        tv.add(l[0]);
                        break;
                    case "intransitive-verb":
                        iv.add(l[0]);
                        break;
                    default:
                        conj.add(l[0]);
                        break;
                }
            }
            boolean conjunction = true;
            while (iv.size() > 0 && (p > 1 || conjunction) && noun.size() > 0) {
                System.out.print(noun.remove(noun.size() - 1) + " " + iv.remove(iv.size() - 1));
                if (conj.size() > 0) {
                    if (conjunction) {
                        System.out.print(" " + conj.remove(conj.size() - 1) + " ");
                        conjunction = false;
                    } else {
                        --p;
                        System.out.print(".");
                        conjunction = true;
                    }
                }
            }
            while (tv.size() > 1 && (p > 1 || conjunction) && noun.size() > 0) {
                System.out.print(noun.remove(noun.size() - 1) + " " + tv.remove(tv.size() - 1) + noun.remove(noun.size() - 1));
                if (conjunction) {
                    System.out.print(" " + conj.remove(conj.size() - 1) + " ");
                    conjunction = false;
                } else {
                    --p;
                    System.out.print(".");
                    conjunction = true;
                }
            }
            if (noun.size() == 0) {
                break;
            }
            if (tv.size() == 0) {
                break;
            }
            if (p == 1) {

            }
        }
    }
}
