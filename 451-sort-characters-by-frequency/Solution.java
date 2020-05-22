import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a string, sort it in decreasing order based on the frequency of characters.

        Examples:

            Input: "tree"
            Output: "eert"

            Input: "cccaaa"
            Output: "cccaaa"

            Input: "Aabb"
            Output: "bbAa"

    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public String solve(String string) {
        requireNonNull(string, "string is null");
        return solve(string.toCharArray());
    }

    private String solve(char[] string) {
        Map<Character, Integer> countByCharacter;
        List<List<Character>> charactersByCount;

        countByCharacter = countCharacters(string);
        charactersByCount = toCharactersByCount(countByCharacter);
        return buildString(charactersByCount);
    }

    private Map<Character, Integer> countCharacters(char[] string) {
        Map<Character, Integer> result = new HashMap<>();

        for (char character : string) {
            result.merge(character, 1, (o, n) -> o + n);
        }
        return result;
    }

    private List<List<Character>> toCharactersByCount(Map<Character, Integer> countByCharacter) {
        List<List<Character>> result = new ArrayList<>();
        char character;
        int count;

        for (Map.Entry<Character, Integer> characterCount : countByCharacter.entrySet()) {
            character = characterCount.getKey();
            count = characterCount.getValue();
            while (result.size() < count) {
                result.add(new ArrayList<>());
            }
            result.get(count - 1).add(character);
        }
        return result;
    }

    private String buildString(List<List<Character>> charactersByCount) {
        StringBuilder builder = new StringBuilder();
        List<Character> characters;

        for (int i = charactersByCount.size() - 1; i >= 0; i--) {
            characters = charactersByCount.get(i);
            for (Character character : characters) {
                for (int  j = 0; j <= i; j++) {
                    builder.append(character);
                }
            }
        }
        return builder.toString();
    }

}