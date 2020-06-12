import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        A message containing letters from A-Z is being encoded to numbers using the following mapping:
            'A' -> 1
            'B' -> 2
            ...
            'Z' -> 26
        Given a non-empty string containing only digits, determine the total number of ways to decode it.

        Examples:

            Input: "12"
            Output: 2

            Input: "226"
            Output: 3

    Time  complexity: O(n)
    Space complexity: O(n) // can improve to O(1) by storing only 2 counts

*/
public final class Solution {

    private static final int BASE = 10;

    public int solve(String string) {
        char[] digits;
        int[] counts;
        int count;
        int digit;

        requireNonNull(string, "string is null");
        digits = string.toCharArray();
        counts = new int[digits.length + 1];
        counts[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            if (!Character.isDigit(digits[i])) {
                throw new IllegalArgumentException("invalid digit " + digits[i] + " at "  + i);
            }
            digit = Character.digit(digits[i], BASE);
            if (i > 0) {
                count = (digit != 0) ? counts[i] : 0;
                if ((digits[i - 1] == '1') || ((digits[i - 1] == '2') && (digit < 7))) {
                    count += counts[i - 1];
                }
                counts[i + 1] = count;
            } else if (digit != 0) {
                counts[i + 1] = 1;
            }
        }
        return counts[digits.length];
    }

}