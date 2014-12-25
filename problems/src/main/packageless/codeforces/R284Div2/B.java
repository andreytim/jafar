import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;

        Map<String, String> f2s = new HashMap<>();
        int n = in.nextInt(), m = in.nextInt();
        for (int i = 0; i < m; i++) {
            f2s.put(in.next(), in.next());
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(" ");
            String word = in.next();
            out.print(f2s.get(word).length() < word.length() ? f2s.get(word) : word);
        }
    }

}
