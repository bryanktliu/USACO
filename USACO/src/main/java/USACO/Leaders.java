package USACO;

import java.io.*;
import java.util.HashSet;

public class Leaders {

    public static int n;
    public static char[] types;
    public static int[] groups;
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
        n = nextInt();
        types = next().toCharArray();
        groups = new int[n];
        for (int i = 0; i < n; ++i) {
            groups[i] = nextInt();
        }
        int lastH = -1;
        int lastG = -1;
        for (int i = n - 1; i >= 0; --i) {
            if (lastG != -1 && lastH != -1) {
                break;
            }
            if (lastH == -1 && types[i] == 'H') {
                lastH = i;
            }
            if (lastG == -1 && types[i] == 'G') {
                lastG = i;
            }
        }
        HashSet<Integer> possibleH = new HashSet<>();
        HashSet<Integer> possibleG = new HashSet<>();
        int pH = Integer.MAX_VALUE;
        int pG = Integer.MAX_VALUE;
        char first = types[0];
        if (first == 'H') {
            if (groups[0] > lastH) {
                pH = 0;
                possibleH.add(0);
            }
        } else if (groups[0] > lastG) {
            pG = 0;
            possibleG.add(0);
        }
        for (int i = 1; i < n; ++i) {
            if (types[i] != first) {
                if (types[i] == 'H') {
                    if (groups[i] > lastH) {
                        pH = i;
                        possibleH.add(i);
                    }
                } else if (groups[i] > lastG) {
                    pG = i;
                    possibleG.add(i);
                }
                break;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i > pH && i > pG) {
                break;
            }
            if (types[i] == 'H') {
                if (i < pG && groups[i] > pG) {
                    possibleH.add(i);
                }
            } else if (i < pH && groups[i] > pH) {
                possibleG.add(i);
            }
        }
        System.out.println(possibleG.size() * possibleH.size());
    }
}
