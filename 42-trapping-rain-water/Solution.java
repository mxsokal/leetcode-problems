import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;

public final class Solution {

    public int solve(int[] heights) {
        int leftIndex;
        int rightIndex;
        int leftMaxHeight = 0;
        int rightMaxHeight = 0;
        int volume = 0;

        requireNonNull(heights, "heights is null");
        leftIndex = 0;
        rightIndex = heights.length - 1;
        // proceed from both sides towards each other
        while (leftIndex < rightIndex) {
            leftMaxHeight = max(leftMaxHeight, heights[leftIndex]);
            rightMaxHeight = max(rightMaxHeight, heights[rightIndex]);
            if (leftMaxHeight > rightMaxHeight) {
                // if the left side is higher, the water cannot leak from the left side
                // and we can safely add the volume above the current right height which doesn't leak from the right side
                volume += rightMaxHeight - heights[rightIndex];
                rightIndex--;
            } else {
                volume += leftMaxHeight - heights[leftIndex];
                leftIndex++;
            }
        }
        return volume;
    }

}