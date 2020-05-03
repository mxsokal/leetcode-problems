import java.util.Map;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an arbitrary ransom note string and another string containing letters from all the magazines, write
        a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will
        return false.
        Each letter in the magazine string can only be used once in your ransom note.
        You may assume that both strings contain only lowercase letters.

        Examples:

            canConstruct("a", "b") -> false
            canConstruct("aa", "ab") -> false
            canConstruct("aa", "aab") -> true
        
    Time  complexity: O(n+m)
    Space complexity: O(1)
*/
public final class Solution {

    public boolean solve(String note, String letters) {
        Map<Character, Integer> countByLetter;
        int count;
        char character;
        boolean result = true;

        requireNonNull(note, "note is null");
        requireNonNull(letters, "letters is null");
        if (!note.isEmpty()) {
            countByLetter = getCountByLetter(letters);
            for (int i = 0; i < note.length(); i++) {
                character = note.charAt(i);
                count = countByLetter.merge(character, -1, (o, n) -> o - 1);
                if (count < 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private Map<Character, Integer> getCountByLetter(String letters) {
        Map<Character, Integer> result = new HashMap<>();
        char character;

        for (int i = 0; i < letters.length(); i++) {
            character = letters.charAt(i);
            result.merge(character, 1, (o, n) -> o + 1);
        }
        return result;
    }

}