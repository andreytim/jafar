package com.andreytim.jafar.problems.arrstr;

/**
 * RLE - encode successive repeated characters by the repetition count and the character.
 * Examples: "aaaabcccaa" => "4a1b3c2a", "3e4f2e" => "eeeffffee".
 * Implement run-length encoding and decoding functions.
 * Assume the string to be encoded consists of letters of the alphabet,
 * with no digits, and the string to be encoded is a valid encoding.
 *
 * Created by shpolsky on 05.11.14.
 */
public class P23_RleEncoding {

  public static String encode(String str) {
    if (str == null || str.isEmpty()) return "";
    int count = 1;
    StringBuilder result = new StringBuilder();
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i-1) == str.charAt(i)) {
        count++;
      } else {
        result.append(count);
        result.append(str.charAt(i-1));
        count = 1;
      }
    }
    result.append(count);
    result.append(str.charAt(str.length()-1));
    return result.toString();
  }

  public static String decode(String str) {
    if (str == null || str.isEmpty() || (str.length() & 1) == 1) return ""; // it's better to throw en exception here
    int count = 0;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        count = count * 10 + str.charAt(i) - '0';
      } else {
          for (int j = 0; j < count; j++) {
              result.append(str.charAt(i));
          }
          count = 0;
      }
    }
    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(decode("3e4f2e"));
    System.out.println(encode("aaaabcccaa"));
    System.out.println(decode("11e34f2e"));
  }

}
