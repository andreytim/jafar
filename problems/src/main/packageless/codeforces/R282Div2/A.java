import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by shpolsky on 13.12.14.
 */
public class A {

    private int nums(String n) {
        int[] nums = new int[]{ 2, 7, 2, 3, 3, 4, 2, 5, 1, 2 };
        return nums[n.charAt(0)-'0']*nums[n.charAt(1)-'0'];
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printf("%d\n", nums(reader.readLine()));
    }

    public static void main(String[] args) throws Exception {
        new A().solve();
//        System.out.println(new A().nums("89"));
//        System.out.println(new A().nums("00"));
//        System.out.println(new A().nums("73"));
//        System.out.println(new A().nums("22"));
//        System.out.println(new A().nums("24"));
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
