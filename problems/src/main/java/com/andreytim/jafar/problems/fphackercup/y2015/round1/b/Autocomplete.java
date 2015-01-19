package com.andreytim.jafar.problems.fphackercup.y2015.round1.b;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by shpolsky on 18.01.15.
 */
public class Autocomplete {

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private int count = 1;
    }

    private static void add(TrieNode root, String word) {
        for (char ch : word.toCharArray()) {
            int key = ch - 'a';
            if (root.children[key] == null) {
                root.children[key] = new TrieNode();
            } else {
                root.children[key].count++;
            }
            root = root.children[key];
        }
    }

    private static int prefixLength(TrieNode root, String word) {
        int i = 0;
        while (i < word.length()-1 && root.children[word.charAt(i)-'a'].count > 1) {
            root = root.children[word.charAt(i)-'a'];
            i++;
        }
        return i+1;
    }

    private static String solve(Scanner in) {
        TrieNode trie = new TrieNode();
        int total = 0, N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String word = in.next();
            add(trie, word);
            total += prefixLength(trie, word);
        }
        return String.valueOf(total);
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(Autocomplete.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}
