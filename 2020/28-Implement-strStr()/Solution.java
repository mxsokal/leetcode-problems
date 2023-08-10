import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Implement strStr().
        Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
        What should we return when needle is an empty string? This is a great question to ask during an interview.
        For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's
        strstr() and Java's indexOf().

        Examples:

            Input: haystack = "hello", needle = "ll"
            Output: 2

            Input: haystack = "aaaaa", needle = "bba"
            Output: -1

    Solution Description:

    Time  complexity: O((N - L) * L), N - string length, L - substring length
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(String string, String substring) {
        int result = -1;

        requireNonNull(string, "string is null");
        requireNonNull(substring, "substring is null");
        if (substring.isEmpty()) {
            result = 0;
        } else if (substring.length() <= string.length()) {
            result = solve(string.toCharArray(), substring.toCharArray());
        }
        return result;
    }

    private int solve(char[] string, char[] substring) {
        int index = 0;
        int maxIndex = string.length - substring.length;
        int result = -1;

        do {
            index = findCharacter(string, substring[0], index, maxIndex);
            if (index >= 0) {
                if (matches(string, substring, index)) {
                    result = index;
                } else {
                    index++;
                }
            }
        } while ((index <= maxIndex) && (index >= 0) && (result < 0));
        return result;
    }

    private int findCharacter(char[] string, char character, int beginIndex, int endIndex) {
        int index = beginIndex;
        int result = -1;

        while ((index <= endIndex) && (result < 0)) {
            if (string[index] == character) {
                result = index;
            }
            index++;
        }
        return result;
    }

    private boolean matches(char[] string, char[] substring, int beginIndex) {
        boolean result = true;
        int index = 0;

        while ((index < substring.length) && (result)) {
            if (substring[index] != string[beginIndex + index]) {
                result = false;
            }
            index++;
        }
        return result;
    }

}