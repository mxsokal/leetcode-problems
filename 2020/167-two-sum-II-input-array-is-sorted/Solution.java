import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to
        a specific target number.
        The function twoSum should return indices of the two numbers such that they add up to the target, where index1
        must be less than index2.

        Note:

        Your returned answers (both index1 and index2) are not zero-based.
        You may assume that each input would have exactly one solution and you may not use the same element twice.

        Examples:

            Input: numbers = [2,7,11,15], target = 9
            Output: [1,2]
            Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public int[] solve(int[] numbers, int target) {
        int[] result = null;
        int beginIndex = 0;
        int endIndex = numbers.length - 1;
        int sum;

        requireNonNull(numbers, "numbers is null");
        while (beginIndex < endIndex) {
            sum = Math.addExact(numbers[beginIndex], numbers[endIndex]);
            if (target == sum) {
                result = new int[]{beginIndex + 1, endIndex + 1};
                break;
            } else if (sum > target) {
                // We can exclude numbers[endIndex] from further search, because each element after beginIndex is
                // greater than (or equal to) numbers[beginIndex], and the sum of numbers[endIndex] and any of those
                // elements can only become greater.
                endIndex--;
            } else {
                // We can exclude numbers[beginIndex] from further search, because each element before endIndex is
                // smaller than (or equal to) numbers[endIndex], and the sum of numbers[beginIndex] and any of those
                // elements can only become less.
                beginIndex++;
            }
        }
        return result;
    }

}