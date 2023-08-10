/*
    Problem Description:

        You are climbing a stair case. It takes n steps to reach to the top.
        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
        Given n will be a positive integer.

        Examples:

            Input: 2
            Output: 2

            Input: 3
            Output: 3

    CAN BE IMPROVED TO O(log(n)), it's a fibonacci sequence

    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public int solve(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count value " + count + " is negative");
        }
        return solve(count, new int[count]);
    }

    private int solve(int count, int[] cache) {
        int result = 0;

        if (count == 0) {
            result = 1;
        } else if (count > 0) {
            result = cache[count - 1];
            if (result == 0) {
                result = solve(count - 1, cache);
                result += solve(count - 2, cache);
                cache[count - 1] = result;
            }
        }
        return result;
    }

}