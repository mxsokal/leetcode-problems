import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
        (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
        You are given a target value to search. If found in the array return its index, otherwise return -1.
        You may assume no duplicate exists in the array.
        Your algorithm's runtime complexity must be in the order of O(log n).

        Examples:

            Input: nums = [4,5,6,7,0,1,2], target = 0
            Output: 4

            Input: nums = [4,5,6,7,0,1,2], target = 3
            Output: -1

    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int[] numbers, int target) {
        requireNonNull(numbers, "numbers is null");
        return (numbers.length > 0) ? find(numbers, 0, numbers.length - 1, target) : -1;
    }

    private int find(int[] numbers, int beginIndex, int endIndex, int target) {
        int result = -1;
        int divIndex;

        if (beginIndex < endIndex) {
            divIndex = (beginIndex + endIndex) / 2;
            if (((numbers[beginIndex] <= numbers[divIndex])
                    && ((target >= numbers[beginIndex]) && (target <= numbers[divIndex])))
                || ((numbers[divIndex + 1] <= numbers[endIndex]) 
                    && ((target < numbers[divIndex + 1]) || (target > numbers[endIndex])))) {
                // if either [beginIndex, divIndex] is sorted and target is numbers[beginIndex, divIndex]
                // or [divIndex+1, endIndex] is sorted and target is NOT in numbers[divIndex + 1, endIndex]
                result = find(numbers, beginIndex, divIndex, target);
            } else {
                result = find(numbers, divIndex + 1, endIndex, target);
            }
        } else if (numbers[beginIndex] == target) {
            result = beginIndex;
        }
        return result;
    }

}