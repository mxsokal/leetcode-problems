import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
        The digits are stored such that the most significant digit is at the head of the list, and each element
        in the array contain a single digit.
        You may assume the integer does not contain any leading zero, except the number 0 itself.

        Examples:

            Input: [1,2,3]
            Output: [1,2,4]

    Time  complexity: O(n)
    Space complexity: O(n), including result
*/
public final class Solution {

    public int[] solve(int[] digits) {
        final int BASE = 10;
        int[] result = new int[digits.length];
        int carry = 1;

        requireNonNull(digits, "digits is null");
        for (int i = digits.length - 1; i >= 0; i--) {
            if ((digits[i] < 0) || (digits[i] >= BASE)) {
                throw new IllegalArgumentException("digits[" + i + "] value " + digits[i] + " is invalid");
            }
            if (carry == 1) {
                result[i] = digits[i] + 1;
                if (result[i] >= BASE) {
                    result[i] = result[i] % BASE;
                } else {
                    carry = 0;
                }
            } else {
                result[i] = digits[i];
            }
        }
        if (carry == 1) {
            result = new int[digits.length + 1];
            result[0] = 1;
        }
        return result;
    }

}