package net.mxs.leetcode.problem.lswrc;

import java.util.Map;
import java.util.HashMap;
import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a string, find the length of the longest substring without repeating characters.

        Example:

            Input: "abcabcbb"
            Output: 3 

            Input: "bbbbb"
            Output: 1

            Input: "pwwkew"
            Output: 3

    Solution Description:

        Use a map to store indexes (last) of each traversed character. Keep incrementing the length until a duplicate character found which contributes to that length (check the index using the map). Keep tracking max length.

    Notes:
        - a simple int array can be used instead of map for ASCII (extended) symbols
        - the below solution should be rewritten to support UTF-16 surrogate characters


    Time  complexity: O(n)
    Space complexity: O(min(m, n)) where m - charset size
*/
public final class Solution {

    public int solve(String string) {
        Map<Character, Integer> indexByCharacter = new HashMap<>();
        char character;
        int index;
        int length = 0;
        int newLength = 0;
        int maxLength = 0;

        requireNonNull(string, "string is null");
        for (int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            if (indexByCharacter.containsKey(character)) {
                index = indexByCharacter.get(character);
                newLength = i - index - 1;
                if (newLength < length) {
                    maxLength = max(maxLength, length);
                    length = newLength;
                }
            }
            indexByCharacter.put(character, i);
            length++;
        }
        return max(maxLength, length);
    }

}
