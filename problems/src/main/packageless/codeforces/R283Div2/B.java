import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {

    public String addOnes(String str) {
        char[] res = new char[str.length()];
        int i = 0;
        for (char ch : str.toCharArray()) {
            res[i++] = (char) (((ch - '0' + 1) % 10) + '0');
        }
        return new String(res);
    }

    private String ans(String number) {
        String min = new String(new char[]{255});
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < number.length(); j++) {
                if (number.charAt(j) == '0') {
                    min = min.compareTo(number.substring(j) + number.substring(0,j)) > 0 ?
                            number.substring(j) + number.substring(0,j) : min;
                }
            }
            number = addOnes(number);
        }
        return min;
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        printf("%s\n", ans(reader.readLine()));
    }

    public static void main(String[] args) throws Exception {
        new B().solve();
//        System.out.println(new B().ans("579"));
//        System.out.println(new B().ans("2014"));
//        System.out.println(new B().ans("5"));
//        System.out.println(new B().ans("2"));
//        System.out.println(new B().ans("0"));
//        System.out.println(new B().ans("2014123412341235211235123512341235123515125214214321"));
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
