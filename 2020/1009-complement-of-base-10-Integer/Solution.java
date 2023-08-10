/*
    Problem Description:

        Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary,
        11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary
        representation.
        The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0
        to a 1.  For example, the complement of "101" in binary is "010" in binary.
        For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
        0 <= N < 10^9

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