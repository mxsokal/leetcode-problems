import java.util.Set;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
        Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
        The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
        so "a" is considered a different type of stone from "A".

        Examples:
            Input: J = "aA", S = "aAAbbbb"
            Output: 3

    Time  complexity: O(m + n)
    Space complexity: O(m)
*/
public final class Solution {

    public int solve(String jewels, String stones) {
        Set<Character> jewelSet;
        int count = 0;

        requireNonNull(jewels, "jewels is null");
        requireNonNull(stones, "stones is null");
        jewelSet = parseJewels(jewels);
        for (int i = 0; i < stones.length(); i++) {
            if (jewelSet.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    // can use ASCII int[] instead
    private Set<Character> parseJewels(String jewels) {
        Set<Character> result = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) {
            result.add(jewels.charAt(i));
        }
        return result;
    }

}