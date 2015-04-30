package com.andreytim.jafar.problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tim on 30/04/15.
 */
public class TextJustify {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            List<String> currWords = new ArrayList<>();
            int wordsLength = 0;
            while (i < words.length &&
                    wordsLength + words[i].length() + currWords.size() <= maxWidth) {
                currWords.add(words[i]);
                wordsLength += words[i].length();
                i++;
            }
            StringBuilder line = new StringBuilder();
            if (i != words.length && currWords.size() > 1) {
                int spacesLength = maxWidth - wordsLength;
                for (int j = 0; j < currWords.size(); j++) {
                    if (j > 0) {
                        int spaceL = spacesLength / (currWords.size()-j) +
                                ((spacesLength % (currWords.size()-j) != 0) ? 1 : 0);
                        spacesLength -= spaceL;
                        while (spaceL-- > 0) line.append(' ');
                    }
                    line.append(currWords.get(j));
                }
            } else {
                for (int j = 0; j < currWords.size(); j++) {
                    if (j > 0) line.append(' ');
                    line.append(currWords.get(j));
                    if (j == currWords.size()-1)
                        while (line.length() < maxWidth) line.append(' ');
                }
            }
            res.add(line.toString());
        }
        return res;
    }

    private static void test(String[] words, int length) {
        System.out.printf("Input: %s, %d; Output:\n", Arrays.toString(words), length);
        for (String line : new TextJustify().fullJustify(words, length)) {
            System.out.printf("\"%s\"\n", line);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test(new String[]{"Simple."}, 10);
        test(new String[]{"Make", "it", "simple."}, 9);
        test(new String[]{"Make", "it", "simple."}, 7);
        test(new String[]{"Make", "me", "feel", "bad."}, 4);
        test(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
    }

}
