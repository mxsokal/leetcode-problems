import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
            A straight forward solution using O(mn) space is probably a bad idea.
            A simple improvement uses O(m + n) space, but still not the best solution.
            Could you devise a constant space solution?

        Examples:

            Input: [
                      [1,1,1],
                      [1,0,1],
                      [1,1,1]
                    ]
            Output: [
                      [1,0,1],
                      [0,0,0],
                      [1,0,1]
                    ]

            Input: [
                      [0,1,2,0],
                      [3,4,5,2],
                      [1,3,1,5]
                    ]
            Output: [
                      [0,0,0,0],
                      [0,4,5,0],
                      [0,3,1,0]
                    ]
        
    Time  complexity: O(n*m)
    Space complexity: O(1)
*/
public final class Solution {

    public void solve(int[][] matrix) {
        int[] markerIndexes;

        requireNonNull(matrix, "matrix is null");
        markerIndexes = findFirstZero(matrix);
        if (markerIndexes != null) {
            initMarker(matrix, markerIndexes);
            markZeros(matrix, markerIndexes);
            zeroMarked(matrix, markerIndexes);
            zeroMarker(matrix, markerIndexes);
        }
    }

    private int[] findFirstZero(int[][] matrix) {
        int[] result = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    result = new int[]{i, j};
                    break;
                }
            }
        }
        return result;
    }

    private void initMarker(int[][] matrix, int[] markerIndexes) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][markerIndexes[1]] = (matrix[i][markerIndexes[1]] == 0) ? 1 : 0;
        }
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[markerIndexes[0]][j] = (matrix[markerIndexes[0]][j] == 0) ? 1 : 0;
        }
    }

    private void markZeros(int[][] matrix, int[] markerIndexes) {
        for (int i = 0; i < matrix.length; i++) {
            if (i == markerIndexes[0]) {
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == markerIndexes[1]) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    matrix[i][markerIndexes[1]] = 1;
                    matrix[markerIndexes[0]][j] = 1;
                }
            }
        }
    }

    private void zeroMarked(int[][] matrix, int[] markerIndexes) {
        for (int i = 0; i < matrix.length; i++) {
            if (i == markerIndexes[0]) {
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == markerIndexes[1]) {
                    continue;
                }
                if ((matrix[i][markerIndexes[1]] == 1) || (matrix[markerIndexes[0]][j] == 1)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void zeroMarker(int[][] matrix, int[] markerIndexes) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][markerIndexes[1]] = 0;
        }
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[markerIndexes[0]][j] = 0;
        }
    }

}