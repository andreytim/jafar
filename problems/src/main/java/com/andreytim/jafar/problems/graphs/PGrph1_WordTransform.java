package com.andreytim.jafar.problems.graphs;

import java.util.*;

/**
 * Created by shpolsky on 27.10.14.
 */
public class PGrph1_WordTransform {

    private static final Set<String> DICT = new HashSet<>();
    static {
        DICT.add("GOD"); DICT.add("GID"); DICT.add("DID"); DICT.add("DIG");
        DICT.add("DOG"); DICT.add("GAD"); DICT.add("ADA"); DICT.add("ADR");
        DICT.add("ARD"); DICT.add("AFR"); DICT.add("DFR"); DICT.add("FRG");
    }

    private static Map<String, Set<String>> formIndex(Set<String> dictionary) {
        Map<String, Set<String>> result = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0,i) + word.substring(i+1) + i;
                if (!result.containsKey(key)) {
                    result.put(key, new HashSet<String>());
                }
                result.get(key).add(word);
            }
        }
        return result;
    }

    public static List<String> transform(String startWord, String endWord, Set<String> dictionary) {
        Map<String, Set<String>> idx = formIndex(dictionary);
        Map<String, String> parents = new HashMap<>();
        parents.put(startWord, null);
        Set<String> visited = new HashSet<>();
        visited.add(startWord);
        Queue<String> queue = new LinkedList<>();
        queue.offer(startWord);
        while (!queue.isEmpty()) {
            String currWord = queue.poll();
            if (currWord.equals(endWord)) {
                LinkedList<String> path = new LinkedList<>();
                while (parents.get(endWord) != null) {
                    path.addFirst(endWord);
                    endWord = parents.get(endWord);
                }
                path.addFirst(startWord);
                return path;
            }
            for (int i = 0; i < currWord.length(); i++) {
                String key = currWord.substring(0,i) + currWord.substring(i+1) + i;
                for (String nextWord : idx.get(key)) {
                    if (!visited.contains(nextWord)) {
                        parents.put(nextWord, currWord);
                        queue.offer(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        System.out.println(transform("GOD", "DOG", DICT));
        System.out.println(transform("GOD", "FROG", DICT));
        System.out.println(transform("ADA", "AFR", DICT));
    }

}
