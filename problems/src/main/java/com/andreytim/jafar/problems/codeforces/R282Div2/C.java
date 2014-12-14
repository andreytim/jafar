package com.andreytim.jafar.problems.codeforces.R282Div2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C {

    private int[] ans(String map) {
        int oc = 0, bc = 0, cc = 0;
        for (char ch : map.toCharArray()) {
            if (ch == '(') oc++;
            else if (ch == '#') bc++;
            else if (ch == ')') cc++;
            if (oc < bc + cc) return null;
        }
        if (bc > oc - cc) return null;
        int[] res = new int[bc];
        for (int i = 0; i < bc-1; i++) {
            res[i] = 1;
        }
        res[bc-1] = oc - cc - bc + 1;
        return res;
    }

    public void solve() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = ans(reader.readLine().trim());
        if (arr == null) printf("-1\n");
        else {
            for (int i : arr) {
                printf("%s\n", i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        new C().solve();
        System.out.println(Arrays.toString(new C().ans("(((#)((#)")));
        System.out.println(Arrays.toString(new C().ans("()((#((#(#()")));
        System.out.println(Arrays.toString(new C().ans("(#)")));
        System.out.println(Arrays.toString(new C().ans("#")));
        System.out.println(Arrays.toString(new C().ans("(#")));
        System.out.println(Arrays.toString(new C().ans("#(")));
    }

    public static void printf(String str, Object ... args) {
        System.out.printf(str, args);
    }

}
