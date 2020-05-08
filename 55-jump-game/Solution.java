import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of non-negative integers, you are initially positioned at the first index of the array.
        Each element in the array represents your maximum jump length at that position.
        Determine if you are able to reach the last index.
            1 <= nums.length <= 3 * 10^4
            0 <= nums[i][j] <= 10^5

        Examples:

            Input: [2,3,1,1,4]
            Output: true
            Note: Jump 1 step from index 0 to 1, then 3 steps to the last index.

            Input: [3,2,1,0,4]
            Output: false
            Note: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
                impossible to reach the last index.

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public boolean solve(int[] numbers) {
        boolean result = true;
        int maxJumpIndex = 0;

        requireNonNull(numbers, "numbers is null");
        for (int i = 0; i < numbers.length; i++) {
            if (i > maxJumpIndex) {
                result = false;
                break;
            }
            maxJumpIndex = max(maxJumpIndex, i + numbers[i]);
        }
        return result;
    }

}