import java.io.*;

public class COWOperations {

    public static char[] s;
    public static int q;
    public static char[] arr;
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
        s = next().toCharArray();
        arr = new char[s.length + 1];
        arr[0] = ' ';
        for (int i = 0; i < s.length; ++i) {
            arr[i + 1] = compute(s[i], arr[i]);
        }
        q = nextInt();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < q; ++i) {
            int l = nextInt();
            int r = nextInt();
            if (compute(arr[l - 1], arr[r]) == 'C') {
                out.append("Y");
            } else {
                out.append("N");
            }
        }
        System.out.println(out);
    }

    public static char compute(char left, char right) {
        switch ("" + left + right) {
            case ("C "):
            case (" C"):
            case ("OW"):
            case ("WO"):
                return 'C';
            case ("W "):
            case (" W"):
            case ("CO"):
            case ("OC"):
                return 'W';
            case ("O "):
            case (" O"):
            case ("CW"):
            case ("WC"):
                return 'O';
            default:
                return ' ';
        }
    }
}
