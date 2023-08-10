import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a non-negative integer num represented as a string, remove k digits from the number so that the new number
        is the smallest possible.
            The length of num is less than 10002 and will be ≥ k.
            The given num does not contain any leading zero.

        Examples:

            Input: num = "1432219", k = 3
            Output: "1219"

            Input: num = "10200", k = 1
            Output: "200"

            Input: num = "10", k = 2
            Output: "0"

    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public String solve(String number, int count) {
        requireNonNull(number, "number is null");
        if (count < 0) {
            throw new IllegalArgumentException("count value " + count + " is negative");
        } else if (count > number.length()) {
            throw new IllegalArgumentException("count value " + count + " is greater than number of digits "
                + number.length());
        }
        return new String(removeMinimizing(number.toCharArray(), count));
    }

    private char[] removeMinimizing(char[] digits, int count) {
        char[] result = new char[digits.length];
        int remainingCount = count;
        int length = 0;

        // Approach: constantly minimize higher order digits in result
        for (int i = 0; i < digits.length; i++) {
            result[length] = digits[i];
            // Now let's examine the next digit, if any. If it is less than the last digit in the result, then the last
            // digit is removed from the result. The process is repeated until the next digit is greater or equal to
            // the last digit in the result, or there are no more digits in the result, or we've removed the number of
            // digits requested.
            // This process ensures that higher order digits are as minimal as possible, which is the key in obtaining
            // the smallest number. There is no point in removing lower order digits first, before trying to minimize
            // higher order ones.
            // Basically, the process keeps higher order digits in the ascending order.
            while ((remainingCount > 0) && (length >= 0)
              && (i < (digits.length - 1)) && (result[length] > digits[i + 1])) {
                length--;
                remainingCount--;
            }
            length++;
        }
        // At this point we may still have remainingCount > 0. In that case, our result digits are all sorted in
        // the ascending order. To obtain the smallest number, we can just truncate the biggest digits (lower ones).
        // Also, we may have leading zeros and some garbage in the end of the array. Let's trim this.
        return trim(result, length - remainingCount);
    }

    private char[] trim(char[] digits, int length) {
        char[] result = null;
        int startIndex = 0;

        // rewind leading zeros
        while ((startIndex < length) && (digits[startIndex] == '0')) {
            startIndex++;
        }
        if (startIndex < length) {
            // if still have something - copy to result
            result = new char[length - startIndex];
            System.arraycopy(digits, startIndex, result, 0, result.length);
        }
        return (result == null) ? new char[]{'0'} : result;
    }

}