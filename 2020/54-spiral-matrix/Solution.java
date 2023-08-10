import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

        Examples:

            Input: [
                     [ 1, 2, 3 ],
                     [ 4, 5, 6 ],
                     [ 7, 8, 9 ]
                    ]
            Output: [1,2,3,6,9,8,7,4,5]

            Input: [
                      [1, 2, 3, 4],
                      [5, 6, 7, 8],
                      [9,10,11,12]
                    ]
            Output: [1,2,3,4,8,12,11,10,9,5,6,7]

    Time  complexity: O(m*n)
    Space complexity: O(m*n), including result
*/
public final class Solution {

    public List<Integer> solve(int[][] matrix) {
        requireNonNull(matrix, "matrix is null");
        return (matrix.length > 0) ? addSpiralNumbers(new ArrayList<>(), matrix, 0) : Collections.emptyList();
    }

    private List<Integer> addSpiralNumbers(List<Integer> numbers, int[][] matrix, int index) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        if ((index <= ((rowCount - 1) / 2)) && (index <= ((columnCount - 1) / 2))) {
            // index points no further than the middle element (explicit or implicit) both horizontally and vertically
            // top row: left to right
            for (int i = index; i <= (columnCount - index - 1); i++) {
                numbers.add(matrix[index][i]);
            }
            // right column: top to bottom, excluding top and bottom row end elements
            for (int i = index + 1; i <= (rowCount - index - 2); i++) {
                numbers.add(matrix[i][columnCount - index - 1]);
            }
            // if bottom row is not the same as top
            if (index != (rowCount - index - 1)) {
                // bottom row: right to left
                for (int i = columnCount - index - 1; i >= index; i--) {
                    numbers.add(matrix[rowCount - index - 1][i]);
                }
            }
            // if left column is not the same as right
            if (index != (columnCount - index - 1)) {
                // left column: bottom to top, excluding top and bottom row end elements
                for (int i = rowCount - index - 2; i >= index + 1; i--) {
                    numbers.add(matrix[i][index]);
                }
            }
            // next layer
            addSpiralNumbers(numbers, matrix, index + 1);
        }
        return numbers;
    }

}