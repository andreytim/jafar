package com.andreytim.jafar.problems.arrstr;

/**
 * Consider an X x Y array of 1's and 0s. The X axis represents "influences" meaning that X influences Y.
 * So, for example, if $array[3,7] is 1 that means that 3 influences 7.
 * An "influencer" is someone who influences every other person, but is not influenced by any other member.
 * Given such an array, write a function to determine whether or not an "influencer" exists in the array.
 *
 * Solution: just find the diagonal zero cell which is intersection of column of zeros and row of 1 ones.
 *
 * Created by shpolsky on 27.10.14.
 */
public class P21_Influencer {

    public static final byte[][] TEST_ARR = new byte[][] {
            new byte[]{ 0, 1, 1, 0, 1, 1, 1 },
            new byte[]{ 0, 0, 1, 0, 1, 1, 1 },
            new byte[]{ 0, 1, 0, 0, 1, 1, 1 },
            new byte[]{ 1, 1, 1, 0, 1, 1, 1 },
            new byte[]{ 0, 1, 1, 1, 0, 1, 1 },
            new byte[]{ 0, 1, 1, 0, 1, 0, 1 },
            new byte[]{ 0, 1, 1, 0, 1, 1, 0 }
    };

    public static boolean hasInfluencer(byte[][] inf) {
        if (inf.length == 0 || inf.length != inf[0].length) return false;
        loop:
        for (int i = 0; i < inf.length; i++) {
            if (inf[i][i] == 0) {
                for (int j = 0; j < inf.length; j++) {
                    if (j != i && inf[i][j] == 0) continue loop;
                }
                for (int j = 0; j < inf.length; j++) {
                    if (inf[j][i] == 1) continue loop;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasInfluencer(TEST_ARR));
    }

}
