import java.io.BufferedReader;
import java.io.InputStreamReader;

public class C {

    private boolean isGood(char[][] m) {
        if (m.length == 0 || m[0].length == 0) return true;
        for (int i = 0; i < m.length-1; i++) {
            if (new String(m[i]).compareTo(new String(m[i+1])) > 0) return false;
        }
        return true;
    }

    private boolean isGoodColumn(char[][] m, int j) {
        for (int i = 0; i < m.length-1; i++) {
            if (m[i][j] > m[i+1][j]) return false;
        }
        return true;
    }

    private int ans(char[][] m) {
        int res = 0;
        while (!isGood(m)) {
            res++;
            int j = 0;
            while (isGoodColumn(m, j)) j++;
            char[][] newM = new char[m.length][m[0].length-1];
            for (int i = 0; i < m.length; i++) {
                for (int k = 0; k < j; k++) newM[i][k] = m[i][k];
                for (int k = j+1; k < m[0].length; k++) newM[i][k-1] = m[i][k];
            }
            m = newM;
        }
        return res;
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = reader.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        char[][] m = new char[N][M];
        for (int i = 0; i < N; i++) {
            String row = reader.readLine();
            for (int j = 0; j < M; j++) {
                m[i][j] = row.charAt(j);
            }
        }
        printf("%d\n", ans(m));
    }

    public static void main(String[] args) throws Exception {
        new C().solve();
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
