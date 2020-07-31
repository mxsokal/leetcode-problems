import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

        Examples:

            Input: matrix =
                    1 0 1 0 0
                    1 0 1 1 1
                    1 1 1 1 1
                    1 0 0 1 0
            Output: 4

    Time  complexity: O(m*n)
    Space complexity: O(m*n), can be reduced to (O(min(m,n)))
*/
public final class Solution {

    public int solve(char[][] matrix) {
        int result = 0;
        int[][] sizes;

        /*
            Let's store that "size of the biggest square" for each cell. Then for each subsequent
            cell we can calculate the "size of the biggest square" from its neighbors:

            _________
            | a | b |
            |---|---|
            | c | X |
            ---------

            X = min(a,b,c) + 1

            Our square cannot be bigger than c + 1 or b + 1. Also, when c == b there may be a 1x1 hole in the upper left
            corner so our square should also be limited by a + 1.

        */ 
        requireNonNull(matrix, "matrix is null");
        sizes  = new int[matrix.length][(matrix.length > 0) ? matrix[0].length : 0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    if ((i > 0) && (j > 0)) {
                        sizes[i][j] = min(sizes[i - 1][j - 1], min(sizes[i - 1][j], sizes[i][j - 1])) + 1;
                    } else {
                        sizes[i][j] = 1;
                    }
                    result = max(result, sizes[i][j]);
                }
            }
        }
        return result * result;
    }

}