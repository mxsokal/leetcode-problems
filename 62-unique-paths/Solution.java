/*
    Problem Description:

        A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
        corner of the grid (marked 'Finish' in the diagram below).
        How many possible unique paths are there?        
            1 <= m, n <= 100
            It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.

        Examples:

            Input: m = 3, n = 2
            Output: 3

    CAN BE IMPROVED:
        - Formula-based solution
        - DP-based solution using one array

    Time  complexity: O(n*m)
    Space complexity: O(n*m)
*/
public final class Solution {

    public int solve(int m, int n) {
        if (m < 0) {
            throw new IllegalArgumentException("m value " + m + " is negative");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n value " + n + " is negative");
        }
        return getPathCount(1, 1, m, n, createMatrix(m ,n));
    }

    private int getPathCount(int i, int j, int m, int n, int[][] cache) {
        int result = 1;

        if ((i < m) || (j < n)) {
            result = cache[i - 1][j - 1];
            if (result == 0) {
                if (i < m) {
                    result = getPathCount(i + 1, j, m, n, cache);
                }
                if (j < n) {
                    result += getPathCount(i, j + 1, m, n, cache);
                }
                cache[i-1][j-1] = result;
            }
        }
        return result;
    }

    private int[][] createMatrix(int rowCount, int columnCount) {
        int[][] result = new int[rowCount][];

        for (int i = 0; i < rowCount; i++) {
            result[i] = new int[columnCount];
        }
        return result;
    }

}