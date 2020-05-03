/*
    Problem Description:

        Given two integers dividend and divisor, divide two integers without using multiplication, division and mod
        operator. Return the quotient after dividing dividend by divisor.
        The integer division should truncate toward zero, which means losing its fractional part. For example,
        truncate(8.345) = 8 and truncate(-2.7335) = -2.
        Both dividend and divisor will be 32-bit signed integers.
        The divisor will never be 0.
        Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
        [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 2^31 − 1 when the division
        result overflows.

        Examples:

            Input: dividend = 10, divisor = 3
            Output: 3

            Input: dividend = 7, divisor = -3
            Output: -2

    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int dividend, int divisor) {
        int result;

        if ((dividend == Integer.MIN_VALUE) && (divisor == -1)) {
            // special case for the only 32-bit overflow possible for division
            result = Integer.MAX_VALUE;
        } else if (divisor != 0) {
            // It's much more simpler to operate with a dividend and divisor of the same sign.
            // Positive numbers cannot be used for that, because 32-bit int has more negative numbers than positive.
            // result should also be negative for the same reason
            result = doSolve((dividend > 0) ? -dividend : dividend, (divisor > 0) ? -divisor : divisor);
            if (((dividend > 0) && (divisor > 0)) || ((dividend < 0) && (divisor < 0))) {
                result = -result;
            }
        } else {
            throw new ArithmeticException("divisor is zero");
        }
        return result;
    }

    private int doSolve(int dividend, int divisor) {
        int remainder = dividend;
        int[] scaledDivisor;  // [0] - value; [1] - scale
        int result = 0;

        if (dividend <= divisor) {
            // scale divisor by power of 2 up to the value that still fits dividend:
            // |value| <= |dividend|,   |value| == |scale * divisor| == 2^x * |divisor|
            // alternatively, if bitwise operators are not allowed, the result can be implemented as two separate lists
            // containing all values and scales up to the maximum ones
            // both value and scale are negative to support the -2147483648 / 1 case where value==scale==-2147483648
            scaledDivisor = getScaledDivisor(dividend, divisor);
            while (remainder <= divisor) {
                // if current scaled divisor fits into current remainder
                if (remainder <= scaledDivisor[0]) {
                    // subtract that portion
                    remainder -= scaledDivisor[0];
                    result += scaledDivisor[1];
                }
                // calc next scaled divisor, which is two times less (/2)
                scaledDivisor[0] >>= 1;
                scaledDivisor[1] >>= 1;
            }
        }
        return result;
    }

    private int[] getScaledDivisor(int dividend, int divisor) {
        final int MIN_VALUE = Integer.MIN_VALUE / 2; // doubling value less than this produces overflow
        int[] result = new int[2];
        int value = divisor;
        int scale = -1;  // 2^0

        while (dividend <= value) {
            result[0] = value;
            result[1] = scale;
            if (value < MIN_VALUE) {
                break;
            }
            value += value; // x2
            scale += scale; // x2
        }
        return result;
    }

}