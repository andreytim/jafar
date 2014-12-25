import java.io.PrintStream;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;

        long xH = in.nextLong(), yH = in.nextLong();
        long xU = in.nextLong(), yU = in.nextLong();
        int count = 0;
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            long a = in.nextLong(), b = in.nextLong(), c = in.nextLong();
            long sH = a*xH + b*yH + c;
            long sU = a*xU + b*yU + c;
            if ((sH < 0 && sU > 0) || (sH > 0 && sU < 0)) {
                count++;
            }
        }

        out.println(count);
    }

}
