package USACO;/*
ID: bryan.k1
LANG: JAVA
TASK: USACO.gift1
*/

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

class gift1 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("USACO.gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("USACO.gift1.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int count = Integer.parseInt(st.nextToken());
        LinkedHashMap<String, Integer> bal = new LinkedHashMap<>();
        for (int i = 0; i < count; ++i) {
            bal.put(f.readLine(), 0);
        }
        for (int i = 0; i < count; ++i) {
            String giver = f.readLine();
            st = new StringTokenizer(f.readLine());
            int giving = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            if (people == 0) {
                continue;
            }
            bal.put(giver, bal.get(giver) - giving + (giving % people));
            giving /= people;
            for (int j = 0; j < people; ++j) {
                String given = f.readLine();
                bal.put(given, bal.get(given) + giving);
            }
        }
        for (Map.Entry<String, Integer> entry : bal.entrySet()) {
            out.println(entry.getKey() + " " + entry.getValue());
        }
        out.close();
    }
}
