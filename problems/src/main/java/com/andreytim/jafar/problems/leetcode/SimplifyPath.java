package com.andreytim.jafar.problems.leetcode;

import java.util.*;

/**
 * Created by shpolsky on 21.01.15.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.trim().split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) {
            res = "/" + dir + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    private static void test(String path) {
        System.out.printf("Input: %s; Output: %s\n", path, new SimplifyPath().simplifyPath(path));
    }

    public static void main(String[] args) {
        test("/");
        test("/..");
        test("/../");
        test("/./");
        test("/a/./b/../../c/");
    }

}
