import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), x = in.nextInt();
        int currMin = 1;
        int total = 0;
        for (int i = 0; i < n; i++) {
            int l = in.nextInt(), r = in.nextInt();
            while (currMin + x <= l) currMin += x;
            total += r - currMin + 1;
            currMin = r + 1;
        }
        System.out.println(total);
    }

}
