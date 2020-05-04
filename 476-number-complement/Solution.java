/*
    Problem Description:

        Given a positive integer, output its complement number. The complement strategy is to flip the bits of its
        binary representation.
        The given integer is guaranteed to fit within the range of a 32-bit signed integer.
        You could assume no leading zero bit in the integer’s binary representation.

        Examples:

            Input: 5
            Output: 2

            Input: 1
            Output: 0
        
    Time  complexity: O(1)
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number is negative");
        }
        return (number == 0) ? 1 : doSolve(number);
    }

    private int doSolve(int number) {
        int value = number;
        int result = number;
        int mask = 1;

        while (value != 0) {
            result ^= mask;
            value >>= 1;
            mask <<= 1;
        }
        return result;
    }

}