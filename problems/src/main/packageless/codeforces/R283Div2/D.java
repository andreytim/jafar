import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class D {

    private List<String> calcOrderedDivisors(int n) {
        if (n == 1) return Arrays.asList("1 1");
        LinkedList<String> small = new LinkedList<>();
        LinkedList<String> big = new LinkedList<>();
        for (int i = 1; i*i <= n; i++) {
            if (n % i == 0) {
                small.add(String.format("%d %d", i, n/i));
                if (i*i != n) {
                    big.addFirst(String.format("%d %d", n / i, i));
                }
            }
        }
        small.addAll(big);
        return small;
    }

    private boolean validate(String p, String g, String w) {
        String[] games = g.split(" ");
        String[] parr = p.split(" ");
        int s = Integer.valueOf(parr[0]);
        int t = Integer.valueOf(parr[1]);
        int curr1 = 0, curr2 = 0;
        int curr1s = 0, curr2s = 0;
        for (int i = 0; i < games.length; i++) {
            if (games[i].equals(w)) {
                curr1++;
            } else {
                curr2++;
            }
            if (curr1 == t) {
                curr1 = 0; curr2 = 0; curr1s++;
            } else if (curr2 == t) {
                curr1 = 0; curr2 = 0; curr2s++;
            }
        }
        return curr1s > curr2s && curr1s == s && curr2 == 0 && curr1 == 0;
    }

    private void solve(String games) {
        int count = 0;
        String[] gs = games.split(" ");
        String last = gs[gs.length-1];
        for (String str : gs) {
            if (str.equals(last)) count++;
        }
        List<String> divisors = calcOrderedDivisors(count);
        List<String> filtered = new ArrayList<>();
        for (String possibility : divisors) {
            if (validate(possibility, games, last)) {
                filtered.add(possibility);
            }
        }
        printf("%d\n", filtered.size());
        for (String s : filtered) {
            printf("%s\n", s);
        }
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        solve(reader.readLine());
    }

    public static void main(String[] args) throws Exception {
//        new D().solve();
//        new D().solve("1 1 1 2 2 2 1 1 1 2 2 2 1 1 1 1 2 1 1 1");
//        new D().solve("1 2 1 2 1");
//        new D().solve("1");
//        new D().solve("1 1");
//        new D().solve("2 1 2 1 2");
//        new D().solve("1 1 1 1");
//        new D().solve("1 2 1 2");
//        new D().solve("2 1 2 1 1 1 1 1");
        new D().solve("2 1 2 1 1 1 1 1 2 1 1 2 2 2 1 1 2 2 1 1 1 2 1 1 2 2 1 1 1 2 2 1 1 1 1 1 2 1 1 1 2 1 2 1 1 2 1 1 1 2 2 2 2 2 2 2 1 2 1 2 1 1 2 1 2 2 1 1 1 1 1 2 2 1 2 2 1 2 2 1 1 1 2 2 1 1 2 2 1 2 2 1 2 2 2 2 2 1 1 1 1 2 1 1 2 2 2 2 2 2 1 1 1 1 1 2 1 1 2 2 1 2 2 1 1 1 1 1 2 2 1 1 2 2 1 2 2 2 1 2 1 2 1 1 2 1 2 2 2 2 1 2 1 2 2 1 2 1 1 1 1 1 2 1 1 2 2 1 1 1 2 2 2 1 2 2 1 1 2 1 1 1 1 2 1 1");
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
