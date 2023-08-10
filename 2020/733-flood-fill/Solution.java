import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        An image is represented by a 2-D array of integers, each integer representing the pixel value of the image
        (from 0 to 65535).
        Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel
        value newColor, "flood fill" the image.
        To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting
        pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also
        with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with
        the newColor.
        At the end, return the modified image.
            The length of image and image[0] will be in the range [1, 50].
            The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
            The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

        Examples:

            Input: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor = 2
            Output: [[2,2,2],[2,2,0],[2,0,1]]

    Time  complexity: O(n*m)
    Space complexity: O(n*m), including the result
*/
public final class Solution {

    public int[][] solve(int[][] image, int rowIndex, int columnIndex, int color) {
        int oldColor;
        int[][] result;

        requireNonNull(image, "image is null");
        if ((rowIndex < 0) || (rowIndex >= image.length)) {
            throw new IllegalArgumentException("rowIndex value " + rowIndex + " is invalid");
        }
        if ((columnIndex < 0) || (columnIndex >= image[rowIndex].length)) {
            throw new IllegalArgumentException("columnIndex value " + columnIndex + " is invalid");
        }
        oldColor = image[rowIndex][columnIndex];
        if (color != oldColor) {
            fill(image, rowIndex, columnIndex, oldColor, color);
        }
        return image;
    }

    private void fill(int[][] image, int rowIndex, int columnIndex, int oldColor, int newColor) {
        if (image[rowIndex][columnIndex] == oldColor) {
            image[rowIndex][columnIndex] = newColor;
            if (columnIndex != (image[rowIndex].length - 1)) {
                fill(image, rowIndex, columnIndex + 1, oldColor, newColor);
            }
            if (columnIndex != 0) {
                fill(image, rowIndex, columnIndex - 1, oldColor, newColor);
            }
            if (rowIndex != image.length - 1) {
                fill(image, rowIndex + 1, columnIndex, oldColor, newColor);
            }
            if (rowIndex != 0) {
                fill(image, rowIndex - 1, columnIndex, oldColor, newColor);
            }
        }
    }

}