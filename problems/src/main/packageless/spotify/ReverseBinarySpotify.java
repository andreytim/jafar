import java.util.Scanner;

public class ReverseBinarySpotify {

    public static int reverseBinary(int n) {
        int res = 0;
        while (n != 0) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.printf("%d\n", reverseBinary(new Scanner(System.in).nextInt()));
    }
}