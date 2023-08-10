import static java.util.Objects.requireNonNull;
import static java.lang.Math.min;

/*
    Problem Description:

        Write a function to find the longest common prefix string amongst an array of strings.
        If there is no common prefix, return an empty string "".

        Examples:

            Input: ["flower","flow","flight"]
            Output: "fl"

            Input: ["dog","racecar","car"]
            Output: ""
            Explanation: There is no common prefix among the input strings.

    Solution Description:

    Time  complexity: O(m*n), m - minimum string length, n - array length
    Space complexity: O(1)
*/
public final class Solution {

    public String solve(String[] strings) {
        String result = "";
        int length = 0;
        boolean matched = true;

        requireNonNull(strings, "strings is null");
        if (strings.length > 0) {
            result = strings[0];
            for (int i = 0; i < result.length(); i++) {
                for (String string: strings) {
                    if ((string.length()) <= i || (string.charAt(i) != result.charAt(i))) {
                        matched = false;
                        break;
                    }
                }
                if (matched) {
                    length++;
                } else {
                    break;
                }
            }
            result = result.substring(0, length);
        }
        return result;
    }

/*

    // O(S), s - total number of characters in all strings
    public String solve1(String[] strings) {
        String result = "";
        String string;
        int endIndex = 0;

        requireNonNull(strings, "strings is null");
        if (strings.length > 0) {
            result = strings[0];
            endIndex = result.length() - 1;
            for (int i = 1; i < strings.length; i++) {
                string = strings[i];
                endIndex = min(endIndex, string.length() - 1);
                for (int j = 0; j <= endIndex; j++) {
                    if (string.charAt(j) != result.charAt(j)) {
                        endIndex = j - 1;
                        break;
                    }
                }
            }
            result = result.substring(0, endIndex + 1);
        }
        return result;
    }
*/
}