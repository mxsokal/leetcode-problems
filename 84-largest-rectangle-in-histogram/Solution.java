import java.util.Deque;
import java.util.ArrayDeque;
import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;

/*
    Problem Description:

        Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find
        the area of largest rectangle in the histogram.

        Examples:

            Input: [2,1,5,6,2,3]
            Output: 10

    Time  complexity: O(n*m*(4^w))
    Space complexity: O(n*m)

*/
public final class Solution {

    public int solve(int[] heights) {
        Deque<Integer> lowerBarIndexes = new ArrayDeque<>();
        int heightIndex;
        int startIndex;
        int result = 0;
        int maxArea;

        requireNonNull(heights, "heights is null");
        /*
            Accumulate bars while their heights are increasing. Once a lower bar is found, exclude all higher bars
            calculating the max size rectangle that fits inside the excluded area. All other rectangles aren't limited
            by the excluded bars and they will be checked later.
                  __
                 |**|
               __|**|
              |** **|__
            __|*****   |
           |   *****
        */
        for (int i = 0; i < heights.length; i++) {
            // while have higher previous bar
            while ((!lowerBarIndexes.isEmpty()) && (heights[lowerBarIndexes.peek()] > heights[i])) {
                // max rectangle height at
                heightIndex = lowerBarIndexes.pop();
                // max rectangle starts from the bar next to the previous lower bar
                startIndex = (lowerBarIndexes.isEmpty()) ? 0 : lowerBarIndexes.peek() + 1;
                maxArea = heights[heightIndex] * (i - startIndex);
                result = max(result, maxArea);
            }
            lowerBarIndexes.push(i);
        }
        // "exclude" the rest
        while (!lowerBarIndexes.isEmpty()) {
            heightIndex = lowerBarIndexes.pop();
            startIndex = (lowerBarIndexes.isEmpty()) ? 0 : lowerBarIndexes.peek() + 1;
            maxArea = heights[heightIndex] * (heights.length - startIndex);
            result = max(result, maxArea);
        }
        return result;
    }

}