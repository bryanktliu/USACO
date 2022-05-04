package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.ride
*/

import java.io.*;
import java.util.StringTokenizer;

class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("USACO.ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("USACO.ride.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int count1 = 1;
        for (char c : st.nextToken().toCharArray()) {
            count1 *= c - 'A' + 1;
        }
        st = new StringTokenizer(f.readLine());
        int count2 = 1;
        for (char c : st.nextToken().toCharArray()) {
            count2 *= c - 'A' + 1;
        }
        if (count1 % 47 == count2 % 47) {
            out.println("GO");
        } else {
            out.println("STAY");
        }
        out.close();
    }
}
