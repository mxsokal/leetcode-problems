import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color
        are adjacent, with the colors in the order red, white and blue.
        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
        Note: You are not suppose to use the library's sort function for this problem.
            A rather straight forward solution is a two-pass algorithm using counting sort.
            First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's
            then 1's and followed by 2's.
            Could you come up with a one-pass algorithm using only constant space?

        Examples:

            Input: [2,0,2,1,1,0]
            Output: [0,0,1,1,2,2]
        
    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    private static final int RED_COLOR = 0;
    private static final int WHITE_COLOR = 1;
    private static final int BLUE_COLOR = 2;

    public void solve(int[] colors) {
        int color;
        int index = 0;
        int redIndex = -1;
        int blueIndex = colors.length;

        requireNonNull(colors, "colors is null");
        // keep blue at the end
        while (index < blueIndex) {
            color = colors[index];
            if (color == RED_COLOR) {
                redIndex++;
                swap(colors, index, redIndex);
                index++;
            } else if (color == WHITE_COLOR) {
                index++;
            } else if (color == BLUE_COLOR) {
                blueIndex--;
                swap(colors, index, blueIndex);
            } else {
                throw new IllegalArgumentException("color value " + color + " is invalid");
            }
        }
    }

    private void swap(int[] colors, int fromIndex, int toIndex) {
        int color;

        color = colors[toIndex];
        colors[toIndex] = colors[fromIndex];
        colors[fromIndex] = color;
    }

}