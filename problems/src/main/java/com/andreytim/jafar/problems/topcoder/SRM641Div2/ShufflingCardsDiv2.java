package com.andreytim.jafar.problems.topcoder.SRM641Div2;

/**
 * Created by shpolsky on 11.12.14.
 */
public class ShufflingCardsDiv2 {

    public String shuffle(int[] permutation)
    {
        int[] leftHalf = new int[permutation.length/2];
        for (int i = 0; i < permutation.length; i += 2) {
            leftHalf[i/2] = permutation[i];
        }
        int count = 0;
        for (int i = 0; i < leftHalf.length; i++) {
            if (leftHalf[i] > permutation.length/2) count++;
        }
        if (count == permutation.length/4) return "Possible";
        else return "Impossible";
    }

}
