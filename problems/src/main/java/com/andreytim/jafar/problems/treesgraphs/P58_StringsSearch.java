package com.andreytim.jafar.problems.treesgraphs;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s and an array of smaller strings T,
 * design a method to search s for each small string in T.
 * CtCI, 18.8
 *
 * Created by shpolsky on 27.11.14.
 */
public class P58_StringsSearch {

    public static class TrieNode {

        private final Map<Character, TrieNode> children = new HashMap<>();

        public void insert(String str) {
            if (str == null || str.isEmpty()) return;
            if (!children.containsKey(str.charAt(0))) {
                children.put(str.charAt(0), new TrieNode());
            }
            if (str.length() > 1) {
                children.get(str.charAt(0)).insert(str.substring(1));
            }
        }

        public boolean search(String str) {
            if (str == null) {
                return false;
            } else if (str.isEmpty() || (str.length() == 1 && children.containsKey(str.charAt(0)))) {
                return true;
            } else if (str.length() > 1 && children.containsKey(str.charAt(0))) {
                return children.get(str.charAt(0)).search(str.substring(1));
            }
            return false;
        }
    }

    public static TrieNode init(String text) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < text.length(); i++) {
            root.insert(text.substring(i).toLowerCase());
        }
        return root;
    }

    public static void test(String text, String[] ss) {
        TrieNode root = init(text);
        System.out.printf("Input text: %s\n", text);
        for (String s : ss) {
            System.out.printf("Input: \"%s\"; Result: %b\n", s, root.search(s));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test("Abrakadabra", new String[]{ "abr", "kadabra", "omega", "a", "A", "bra", "" });
    }

}
