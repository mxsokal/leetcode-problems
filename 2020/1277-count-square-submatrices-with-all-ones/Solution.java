import static java.lang.Math.min;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

        Examples:

            Input: matrix =
                    [
                        [0,1,1,1],
                        [1,1,1,1],
                        [0,1,1,1]
                    ]
            Output: 15
            Explanation: 
                    There are 10 squares of side 1.
                    There are 4 squares of side 2.
                    There is  1 square of side 3.
                    Total number of squares = 10 + 4 + 1 = 15.

    Time  complexity: O(m*n)
    Space complexity: O(m*n), can be reduced to (O(min(m,n)))
*/
public final class Solution {

    public int solve(int[][] matrix) {
        int[][] counts;
        int result = 0;

        requireNonNull(matrix, "matrix is null");
        counts = new int[matrix.length][matrix[0].length];
        /*
            Each cell having 1 is the right bottom corner of at least one square (1x1). Obviously, it also may be 
            the right bottom corner of other bigger squares. The total number of such squares equals to the size of
            the biggest one.

            1x1: 1
            _____
            |***|
            -----

            2x2: 1x1 + 2x2 = 2
            _________
            |   |   |
            |---|---|
            |   |***|
            ---------

            Let's store that "number of squares == size of the biggest square" for each cell. Then for each subsequent
            cell we can calculate the "number of squares == size of the biggest square" from its neighbors:

            _________
            | a | b |
            |---|---|
            | c | X |
            ---------

            X = min(a,b,c) + 1

            Our square cannot be bigger than c + 1 or b + 1. Also, when c == b there may be a 1x1 hole in the upper left
            corner so our square should also be limited by a + 1.

        */ 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if ((i > 0) && (j > 0)) {
                        // bigger than 1x1
                        counts[i][j] = min(counts[i - 1][j - 1], min(counts[i - 1][j], counts[i][j - 1])) + 1;
                    } else {
                        counts[i][j] = 1;
                    }
                }
                result += counts[i][j];
            }
        }
        return result;
    }

}