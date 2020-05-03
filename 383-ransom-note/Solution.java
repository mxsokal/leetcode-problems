import java.util.Map;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

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