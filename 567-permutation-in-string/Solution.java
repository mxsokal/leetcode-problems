import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
        In other words, one of the first string's permutations is the substring of the second string.        
            The input strings only contain lower case letters.
            The length of both given strings is in range [1, 10,000].

        Examples:

            Input: s1 = "ab" s2 = "eidbaooo"
            Output: True

            Input: s1= "ab" s2 = "eidboaoo"
            Output: False

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    private static final int LETTER_COUNT = 26; // 'a'-'z'

    public boolean solve(String target, String string) {
        boolean result = false;

        requireNonNull(string, "string is null");
        requireNonNull(target, "target is null");
        if ((string.length() >= target.length()) && (target.length() > 0)) {
            result = solve(string.toCharArray(), target.toCharArray());
        }
        return result;
    }

    private boolean solve(char[] string, char[] target) {
        int[] diff = new int[LETTER_COUNT]; // letter -> target.count(letter) - substring.count(letter)
        int count = 1;
        int index = 0;

        count = calcDiffByLetter(diff, target, string, target.length);
        while ((count != 0) && (index < (string.length - target.length))) {
            count += (incLetter(diff, string, index) + decLetter(diff, string, index + target.length));
            index++;
        }
        return count == 0;
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