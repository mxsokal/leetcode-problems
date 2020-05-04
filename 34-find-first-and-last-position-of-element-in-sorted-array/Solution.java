import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given
        target value.
        Your algorithm's runtime complexity must be in the order of O(log n).
        If the target is not found in the array, return [-1, -1].

        Examples:

            Input: nums = [5,7,7,8,8,10], target = 8
            Output: [3,4]

            Input: nums = [5,7,7,8,8,10], target = 6
            Output: [-1,-1]

    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int[] solve(int[] numbers, int target) {
        int beginIndex = -1;
        int endIndex = -1;

        requireNonNull(numbers, "numbers is null");
        if (numbers.length > 0) {
            beginIndex = findFirstNotLess(numbers, 0, numbers.length - 1, target);
            if ((beginIndex >= 0) && (numbers[beginIndex] == target)) {
                endIndex = findFirstNotLess(numbers, 0, numbers.length - 1, target + 1) - 1;
                if (endIndex < 0) {
                    endIndex = numbers.length - 1;
                }
            } else {
                beginIndex = -1;
            }
        }
        return new int[]{beginIndex, endIndex};
    }

    private int findFirstNotLess(int[] numbers, int beginIndex, int endIndex, int target) {
        int result = -1;
        int divIndex;

        if (beginIndex < endIndex) {
            divIndex = (beginIndex + endIndex) / 2;
            if (target <= numbers[divIndex]) {
                result = findFirstNotLess(numbers, beginIndex, divIndex, target);
            } else {
                result = findFirstNotLess(numbers, divIndex + 1, endIndex, target);
            }
        } else if (numbers[beginIndex] >= target) {
            result = beginIndex;
        }
        return result;
    }

}