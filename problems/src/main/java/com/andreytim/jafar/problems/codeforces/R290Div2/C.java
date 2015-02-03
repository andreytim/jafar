package com.andreytim.jafar.problems.codeforces.R290Div2;

import java.util.*;

public class C {

    private static String solve(Scanner in) {
        int n = in.nextInt();
        List<Character> order = new ArrayList<>();
        Deque<String> names = new LinkedList<>();
        names.add(in.next());
        for (int i = 0; i < n-1; i++) {
            String name = in.next();
            if (name.equals(names.peekFirst())) continue;
            if (names.contains(name)) return "Impossible";
            for (String prev : names) {
                int j = 0;
                while (j < name.length() && j < prev.length()
                        && prev.charAt(j) == name.charAt(j)) j++;
                if (j == prev.length()) continue;
                if (j == name.length()) return "Impossible";
                char nch = name.charAt(j), pch = prev.charAt(j);
                int nchIdx = order.indexOf(nch), pchIdx = order.indexOf(pch);
                if (nchIdx != -1 && nchIdx < pchIdx) return "Impossible";
                List<Character> newOrder = new LinkedList<>();
                if (pchIdx == -1) {
                    newOrder.add(pch);
                    if (nchIdx == -1) newOrder.add(nch);
                    newOrder.addAll(order);
                } else if (nchIdx == -1) {
                    newOrder.addAll(order);
                    newOrder.add(pchIdx+1, nch);
                } else {
                    newOrder.addAll(order);
                }
                order = newOrder;
            }
            names.addFirst(name);
        }
        return string(order);
    }

    private static String string(List<Character> order) {
        String res = "";
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!order.contains(ch)) res += ch;
        }
        for (char ch : order) {
            res += ch;
        }
        return res;
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(A.class.getResourceAsStream("in.txt"));
        System.out.println(solve(in));
    }

}
