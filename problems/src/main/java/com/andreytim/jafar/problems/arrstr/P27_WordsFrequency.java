package com.andreytim.jafar.problems.arrstr;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a method to find the frequency of occurrences of any given word in a book.
 * Here's a HashMap implementation. Could be done also with a Trie/SuffixTree (TODO).
 * CtCI, 17.9
 *
 * Created by shpolsky on 15.11.14.
 */
public class P27_WordsFrequency {

    public final Map<String, Integer> WORD_FREQ = new HashMap<>();

    public P27_WordsFrequency() {}
    public P27_WordsFrequency(String book) { init(book); }

    public void init(String book) {
        for (String word : book.split("\\s")) {
            String lowerWord = word.toLowerCase();
            if (!WORD_FREQ.containsKey(lowerWord)) {
                WORD_FREQ.put(lowerWord, 1);
            } else {
                WORD_FREQ.put(lowerWord, WORD_FREQ.get(lowerWord) + 1);
            }
        }
    }

    public int freq(String word) {
        String lowerWord = word.toLowerCase();
        if (WORD_FREQ.containsKey(lowerWord)) {
            return WORD_FREQ.get(lowerWord);
        }
        return 0;
    }

    public static void main(String[] args) {
        P27_WordsFrequency freqDict = new P27_WordsFrequency("No, no, no, my dear! I don't consider punctuation!");
        System.out.printf("%s - %d\n", "No", freqDict.freq("No"));
        System.out.printf("%s - %d\n", "No,", freqDict.freq("No,"));
        System.out.printf("%s - %d\n", "no,", freqDict.freq("no,"));
        System.out.printf("%s - %d\n", "my", freqDict.freq("my"));
        System.out.printf("%s - %d\n", "consider", freqDict.freq("consider"));
    }
}
