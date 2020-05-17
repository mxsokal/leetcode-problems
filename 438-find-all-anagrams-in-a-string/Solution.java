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
    Space complexity: O(m)
*/
public final class Solution {

    public List<Integer> solve(String string, String target) {
        String targetHash;
        String substring;
        String hash;
        List<Integer> result = new ArrayList<>();

        requireNonNull(string, "string is null");
        requireNonNull(target, "target is null");
        targetHash = getHash(target);
        if (string.length() >= target.length()) {
            for (int i = 0; i <= string.length() - target.length(); i++) {
                substring = string.substring(i, i + target.length());
                hash = getHash(substring);
                if (hash.equals(targetHash)) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private String getHash(String string) {
        final char COUNT_DELIMITER = '-';
        final char LETTER_DELIMITER = ':';
        StringBuilder builder = new StringBuilder();
        int[] letters;

        letters = countLetters(string);
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                builder.append(i)
                        .append(COUNT_DELIMITER)
                        .append(letters[i])
                        .append(LETTER_DELIMITER);
            }
        }
        return builder.toString();
    }

    private int[] countLetters(String string) {
        final int LETTER_ASCII_OFFSET = 97; // 'a'
        final int LETTER_COUNT = 26; // 'a'-'z'
        int[] result = new int[LETTER_COUNT];
        int letter;

        for (int i = 0; i < string.length(); i++) {
            letter = string.charAt(i) - LETTER_ASCII_OFFSET;
            if ((letter < 0) || (letter >= LETTER_COUNT)) {
                throw new IllegalArgumentException("letter " + string.charAt(i) + " is not supported");
            }
            result[letter]++;
        }
        return result;
    }

}