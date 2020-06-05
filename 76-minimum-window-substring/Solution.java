import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
        complexity O(n).
            If there is no such window in S that covers all characters in T, return the empty string "".
            If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

        Examples:

            Input: S = "ADOBECODEBANC", T = "ABC"
            Output: "BANC"
        
    Time  complexity: O(n+m)
    Space complexity: O(n+m)
*/
public final class Solution {

    public String solve(String source, String target) {
        List<Entry<Character, Integer>> sourceCharacters;
        Map<Character, Integer> targetCountByCharacter;
        int[] indexes;

        requireNonNull(source, "source is null");
        requireNonNull(target, "target is null");
        targetCountByCharacter = countCharacters(target);
        // just performance improvement, can use non-filtered source instead
        sourceCharacters = filter(source, targetCountByCharacter.keySet());
        indexes = findMinSubstring(sourceCharacters, targetCountByCharacter);
        return source.substring(indexes[0], indexes[1] + 1);
    }

    private Map<Character, Integer> countCharacters(String string) {
        Map<Character, Integer> result = new HashMap<>();
        char character;

        for (int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            result.merge(character, 1, (o, n) -> o + 1);
        }
        return result;
    }

    private List<Entry<Character, Integer>> filter(String string, Set<Character> characters) {
        List<Entry<Character, Integer>> result = new ArrayList<>();
        char character;

        for (int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            if (characters.contains(character)) {
                result.add(new SimpleEntry<>(character, i));
            }
        }
        return result;
    }

    private int[] findMinSubstring(List<Entry<Character, Integer>> source, Map<Character, Integer> target) {
        Map<Character, Integer> countByCharacter = new HashMap<>();
        Character character;
        int windowPosition = 0;
        int beginIndex = 0;
        int endIndex = -1;
        int matchCount = 0;
        int count;
        int index;

        for (int i = 0; i < source.size(); i++) {
            character = source.get(i).getKey();
            index = source.get(i).getValue();
            count = countByCharacter.merge(character, 1, (o, n) -> o + 1);
            if (count == target.get(character)) {
                matchCount++;
            }
            while (matchCount == target.size()) {
                if ((endIndex == -1) || ((endIndex - beginIndex) >= (index - source.get(windowPosition).getValue()))) {
                    beginIndex = source.get(windowPosition).getValue();
                    endIndex = index;
                }
                character = source.get(windowPosition).getKey();
                count = countByCharacter.merge(character, 0, (o, n) -> o - 1);
                if (count == target.get(character) - 1) {
                    matchCount--;
                }
                windowPosition++;
            }
        }
        return new int[]{beginIndex, endIndex};
    }

}