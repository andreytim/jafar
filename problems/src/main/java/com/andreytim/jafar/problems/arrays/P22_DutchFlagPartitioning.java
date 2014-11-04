package com.andreytim.jafar.problems.arrays;

import java.util.Arrays;

/**
 * Write a function that takes an array A and an index i into A, and rearranges
 * the elements such that all elements less than A[i] appear first, followed by
 * elements equal to A[i], followed by elements greater than A[i].
 * O(1) space complexity, O(|A|) time complexity.
 *
 * Created by shpolsky on 05.11.14.
 */
public class P22_DutchFlagPartitioning {

  /**
   * Keeping invariants:
   * arr[0:smaller - 1] - bottom, less then the pivot value,
   * arr[smaller:equal - 1] - middle, equal to the pivot value,
   * arr[equal:larger - 1] - unclassified
   * arr[larger:arr.length] - top, larger than the pivot value.
   *
   * - Why “while ( equal <= larger )” works and “while ( equal < larger )” doesn’t?
   * - Because on previous step we could swap larger and equal decrementing larger and they could
   *   have become ==, but if larger was pointing to element which is less than pivot value,
   *   we still need to swap it.
   *
   * @param arr
   * @param i
   * @return
   */
  public static int[] partition(int[] arr, int i) {
    if (arr == null || arr.length == 0 || i < 0 || i >= arr.length)
        throw new IllegalArgumentException();
    int value = arr[i];
    int smaller = 0, equal = 0, larger = arr.length - 1;
    while (equal <= larger) { // "<=" is important here
      if (arr[equal] < value) {
        swap(arr, equal++, smaller++);
      } else if (arr[equal] > value) {
        swap(arr, larger--, equal);
      } else {
        equal++;
      }
    }
    return arr;
  }

  private static void swap(int[] arr, int i, int j) {
    if (i != j) {
      arr[i] ^= arr[j];
      arr[j] ^= arr[i];
      arr[i] ^= arr[j];
    }
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(partition(new int[]{1, 1, 1, 1, 1}, 3)));
    System.out.println(Arrays.toString(partition(new int[]{ 1, 2, 2, 3, 4 }, 2)));
    System.out.println(Arrays.toString(partition(new int[]{ 1 }, 0)));
    System.out.println(Arrays.toString(partition(new int[]{ 5, 2, 3, 3, 2, 1 }, 3)));
    System.out.println(Arrays.toString(partition(new int[]{ 10, 9, 8, 7, 6, 4, 3, 2, 1 }, 5)));
  }

}
