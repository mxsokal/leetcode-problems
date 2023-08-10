import static java.lang.Math.abs;

/*
    Problem Description:

        Given a 32-bit signed integer, reverse digits of an integer.

        Note:
        Assume we are dealing with an environment which could only store integers within the 32-bit signedinteger range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

        Example:

            Input: 123
            Output: 321

            Input: -123
            Output: -321

            Input: 120
            Output: 21

    Solution Description:

    Time  complexity: O(log(x))
    Space complexity: O(1)
*/

public final class Solution {

    private static final int DEC_BASE = 10;
    private static final int MULT_THRESHOLD = Integer.MAX_VALUE / DEC_BASE;

    public int solve(int value) {
        int remaining = abs(value);
        int reversed = 0;
        int digit;

        while (remaining != 0) {
            digit = remaining % DEC_BASE;
            remaining /= DEC_BASE;
            if (reversed > MULT_THRESHOLD) {
                reversed = 0;
                break;
            }
            reversed = DEC_BASE * reversed + digit;
            if (reversed < 0) {
                reversed = 0;
                break;
            }
        }
        return (value >= 0) ? reversed : -reversed;
    }

}
