import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
        so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
            S will have length in range [1, 500].
            S will consist of lowercase English letters ('a' to 'z') only.


        Examples:

            Input: S = "ababcbacadefegdehijhklij"
            Output: [9,7,8]

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public List<Integer> solve(String string) {
        List<Integer> result = new ArrayList<>();
        int[] intervals;

        requireNonNull(string, "string is null");
        intervals = getIntervals(string);
        return getLengths(intervals);
    }

    private int[] getIntervals(String string) {
        final int LETTER_COUNT = 26;
        final int ASCII_OFFSET = 97;
        int[] firstPositionByLetter = new int[LETTER_COUNT];
        int[] result = new int[string.length()];
        int firstPosition;
        int letter;

        for (int i = 0; i < string.length(); i++) {
            letter = string.charAt(i) - ASCII_OFFSET;
            if ((letter < 0) || (letter >= LETTER_COUNT)) {
                throw new IllegalArgumentException("letter " + string.charAt(i) + " at position " + i + " is invalid");
            }
            firstPosition = firstPositionByLetter[letter];
            if (firstPosition == 0) {
                firstPosition = i + 1;
                firstPositionByLetter[letter] = firstPosition;
            } else {
                result[i] = i;
            }
            result[firstPosition - 1] = i;
        }
        return result;
    }

    private List<Integer> getLengths(int[] intervals) {
        List<Integer> result = new ArrayList<>();
        int firstIndex = 0;
        int lastIndex = 0;

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i] > lastIndex) {
                lastIndex = intervals[i];
            } else if (intervals[i] == lastIndex) {
                result.add(lastIndex - firstIndex + 1);
                lastIndex++;
                firstIndex = lastIndex;
            }
        }
        return result;
    }

}