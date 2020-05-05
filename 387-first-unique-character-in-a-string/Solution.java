import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
        You may assume the string contain only lowercase letters.

        Examples:

            Input: "leetcode"
            Output: 0

            Input: "loveleetcode"
            Output: 2
        
    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(String string) {
        Map<Character, Integer> firstIndexByCharacter = new HashMap<>();
        Set<Integer> uniqueCharacterIndexes = new LinkedHashSet<>();
        char character;
        int index;

        requireNonNull(string, "string is null");
        for (int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            index = firstIndexByCharacter.merge(character, i, (o, n) -> o);
            if (index == i) {
                uniqueCharacterIndexes.add(index);
            } else {
                uniqueCharacterIndexes.remove(index);
            }
        }
        return (uniqueCharacterIndexes.size() > 0) ? uniqueCharacterIndexes.iterator().next() : -1;
    }

}