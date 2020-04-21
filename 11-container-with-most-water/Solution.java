import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class Solution {

    public int solve(int[] heights) {
        int result = 0;
        int leftIndex;
        int rightIndex;
        int volume;

        requireNonNull(heights, "heights is null");
        leftIndex = 0;
        rightIndex = heights.length - 1;
        // proceed from both sides towards each other
        while (leftIndex < rightIndex) {
            // calculate the current volume, which is always limited by the minimum height
            volume = min(heights[leftIndex], heights[rightIndex]) * (rightIndex - leftIndex);
            result = max(volume, result);
            // here we decide on how to proceed further: there is no point of changing the highest side,
            // because the next volume will still be limited by the current lowest side and we won't gain anything.
            if (heights[leftIndex] < heights[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        return result;
    }

}