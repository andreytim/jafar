import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

    private int maxDiff(int[] arr) {
        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length-2; i++) {
            if (arr[i+2] - arr[i] < min) {
                min = arr[i+2] - arr[i];
                minIdx = i;
            }
            maxDiff = Math.max(arr[i+1] - arr[i], maxDiff);
        }
        maxDiff = Math.max(arr[arr.length-1] - arr[arr.length-2], maxDiff);
        return Math.max(maxDiff, arr[minIdx+2] - arr[minIdx]);
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int arr[] = new int[N];
        int i = 0;
        for (String num : reader.readLine().split(" ")) {
            arr[i++] = Integer.parseInt(num);
        }
        printf("%d\n", maxDiff(arr));
    }

    public static void test(int[] arr) {
        System.out.println(new A().maxDiff(arr));
    }

    public static void main(String[] args) throws Exception {
        new A().solve();
//        test(new int[]{ 1, 4, 6 });
//        test(new int[]{ 1, 2, 3, 4, 5 });
//        test(new int[]{ 1, 2, 3, 7, 8 });
//        test(new int[]{ 1, 10, 11, 12 });
//        test(new int[]{ 1, 10, 11, 19 });
//        test(new int[]{ 1, 10, 11, 20 });
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
