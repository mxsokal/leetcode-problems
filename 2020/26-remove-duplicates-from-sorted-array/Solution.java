import static java.util.Objects.requireNonNull;


/*
    Problem Description:

        Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return
        the new length.
        Do not allocate extra space for another array, you must do this by modifying the input array in-place with
        O(1) extra memory.

        Examples:

            Input: [1,1,2]
            Output: 2

            Input: [0,0,1,1,1,2,2,3,3,4]
            Output: 5

    Solution Description:

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int[] numbers) {
        int index = 0;

        requireNonNull(numbers, "numbers is null");
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[index] != numbers[i]) {
                index++;
                numbers[index] = numbers[i];
            }
        }
        return (numbers.length == 0) ? 0 : index + 1;
    }

}