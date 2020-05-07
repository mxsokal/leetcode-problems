import java.util.Arrays;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        You are given an n x n 2D matrix representing an image.
        Rotate the image by 90 degrees (clockwise).
        You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
        DO NOT allocate another 2D matrix and do the rotation.

        Examples:

            Input:
                    [
                      [1,2,3],
                      [4,5,6],
                      [7,8,9]
                    ],

            Output:
                    [
                      [7,4,1],
                      [8,5,2],
                      [9,6,3]
                    ]

            Input:
                [
                  [ 5, 1, 9,11],
                  [ 2, 4, 8,10],
                  [13, 3, 6, 7],
                  [15,14,12,16]
                ], 

            Output:
                [
                  [15,13, 2, 5],
                  [14, 3, 4, 1],
                  [12, 6, 8, 9],
                  [16, 7,10,11]
                ]

    Time  complexity: O(n^2)
    Space complexity: O(1)
*/
public final class Solution {

    public void solve(int[][] matrix) {
        requireNonNull(matrix, "matrix is null");
        rotate(matrix, 0);
    }

    private void rotate(int[][] matrix, int index) {
        int count;
        int value;
        int n = matrix.length;
        int oppositeIndex;

        count = n - (2 * index) - 1;
        oppositeIndex = n - index - 1;
        if (count > 0) {
            /*
                ____________
                |x x x x x x|
                |x A---->B x|
                |x ^ x x | x|
                |x | x x | x|
                |x D<--- C x|
                |x x x x x x|
                ~~~~~~~~~~~~~
            */
            // overwrite the square
            for (int i = 0; i < count; i++) { // from A-->B
                value = matrix[index][index + i];
                value = overwrite(matrix, value, index + i, oppositeIndex);
                value = overwrite(matrix, value, oppositeIndex, oppositeIndex - i);
                value = overwrite(matrix, value, oppositeIndex - i, index);
                overwrite(matrix, value, index, index + i);
            }
            rotate(matrix, index + 1);
        }
    }

    private int overwrite(int[][] matrix, int value, int rowIndex, int columnIndex) {
        int oldValue;

        oldValue = matrix[rowIndex][columnIndex];
        matrix[rowIndex][columnIndex] = value;
        return oldValue;        
    }


/*
    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[args.length][];

        for (int i = 0; i < args.length; i++) {
            matrix[i] = new int[args[i].length()];
            for (int j = 0; j < args[i].length(); j++) {
                matrix[i][j] = Integer.parseInt(args[i].charAt(j) + "");
            }
        }
        s.solve(matrix);

    }
*/
}