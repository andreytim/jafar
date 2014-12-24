import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B {

    private String ans(int a, int b) {
        if (a == b) return "infinity";
        else if (a < b) return "0";
        Set<Integer> nums = new HashSet<>();
        for (int i = 1; i*i <= a-b; i++) {
            if ((a-b) % i == 0) {
                if (i > b) nums.add(i);
                if ((a-b)/i > b) nums.add((a-b)/i);
            }
        }
        return String.valueOf(nums.size());
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] args = reader.readLine().split(" ");
        printf("%s\n", ans(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

    public static void main(String[] args) throws Exception {
//        new B().solve();
        System.out.println(new B().ans(21,5));
        System.out.println(new B().ans(9435152,272));
        System.out.println(new B().ans(1000000000,272));
        System.out.println(new B().ans(0,0));
        System.out.println(new B().ans(10,10));
        System.out.println(new B().ans(10,11));
        System.out.println(new B().ans(1,0));
        System.out.println(new B().ans(2,0));
        System.out.println(new B().ans(2,1));
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
