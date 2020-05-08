import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of strings, group anagrams together.
        All inputs will be in lowercase.
        The order of your output does not matter.

        Examples:

            Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
            Output: [
                      ["ate","eat","tea"],
                      ["nat","tan"],
                      ["bat"]
                    ]
        
    Time  complexity: O(N*K)
    Space complexity: O(N*K), including result
*/
public final class Solution {

    public List<List<String>> solve(String[] strings) {
        Map<String, List<String>> anagrams = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        String hash;

        requireNonNull(strings, "strings is null");
        for (String string : strings) {
            if (string == null) {
                throw new IllegalArgumentException("strings contains null");
            }
            hash = getHash(string);
            anagrams.putIfAbsent(hash, new ArrayList<>());
            anagrams.get(hash).add(string);
        }
        result.addAll(anagrams.values());
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