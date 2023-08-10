import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;


/*
    Problem Description:

        Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
        Strings consists of lowercase English letters only and the length of both strings s and p will not be larger
        than 20,100.
        The order of output does not matter.

        Examples:

            Input: s: "cbaebabacd" p: "abc"
            Output: [0, 6]

            Input: s: "abab" p: "ab"
            Output: [0, 1, 2]

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    private static final int LETTER_COUNT = 26; // 'a'-'z'

    public List<Integer> solve(String string, String target) {
        List<Integer> result = new ArrayList<>();

        requireNonNull(string, "string is null");
        requireNonNull(target, "target is null");
        if ((string.length() >= target.length()) && (target.length() > 0)) {
            result = solve(string.toCharArray(), target.toCharArray());
        }
        return result;
    }

    private List<Integer> solve(char[] string, char[] target) {
        List<Integer> result = new ArrayList<>();
        int[] diff = new int[LETTER_COUNT]; // letter -> target.count(letter) - substring.count(letter)
        int count = 1;

        count = calcDiffByLetter(diff, target, string, target.length);
        if (count == 0) {
            result.add(0);
        }
        for (int i = 0; i < (string.length - target.length); i++) {
            count += (incLetter(diff, string, i) + decLetter(diff, string, i + target.length));
            if (count == 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    private int calcDiffByLetter(int[] diff, char[] stringA, char[] stringB, int length) {
        int letter;
        int count = 0;

        for (int i = 0; i < length; i++) {
            count += (incLetter(diff, stringA, i) + decLetter(diff, stringB, i));
        }
        return count;
    }

    private int incLetter(int[] diff, char[] string, int index) {
        int letter;

        letter = getLetter(string, index);
        diff[letter]++;
        return (diff[letter] == 1)
                ? 1                              // 0 -> 1  - difference++
                : (diff[letter] == 0) ? -1 : 0;  // -1 -> 0 - difference--
    }

    private int decLetter(int[] diff, char[] string, int index) {
        int letter;

        letter = getLetter(string, index);
        diff[letter]--;
        return (diff[letter] == -1)
                ? 1                              // 0 -> -1 - difference++
                : (diff[letter] == 0) ? -1 : 0;  // 1 -> 0  - difference--
    }

    private int getLetter(char[] string, int index) {
        final int ASCII_OFFSET = 97;
        int result;

        result = string[index] - ASCII_OFFSET;
        if ((result < 0) || (result >= LETTER_COUNT)) {
            throw new IllegalArgumentException("letter " + string[index] + " is not supported");
        }
        return result;
    }

}