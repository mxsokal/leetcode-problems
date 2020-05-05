import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an unsorted integer array, find the smallest missing positive integer.
        Your algorithm should run in O(n) time and uses constant extra space.

        Examples:

            Input: [1,2,0]
            Output: 3

            Input: [3,4,-1,1]
            Output: 2

            Input: [7,8,9,11,12]
            Output: 1

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int[] numbers) {
        int result;
        int value;
        int tmp;
        int j;

        requireNonNull(numbers, "numbers is null");
        result = numbers.length + 1; // by default, if numbers has all the values [1...n], result=n+1
        // Arrange each value at the appropriate index: numbers[0] = 1, numbers[1] = 2, ..., numbers[n-1]=n
        // Zeros are treated as nulls
        // Basically, use numbers as a hashmap
        // Values greater than n and negative values are ignored and overwritten
        for (int i = 0; i < numbers.length; i++) {
            j = i;  // start overwriting (if required) with the current number 
            value = 0; // overwrite (if required) the current number with zero (i.e. "null")
            // Overwriting chain: if still in the range of the array and need overwrite - proceed
            while ((j >= 0) && (j < numbers.length) && (numbers[j] != (j + 1))) {
                tmp = numbers[j];
                numbers[j] = value; // overwrite with the appropriate value
                value = tmp; // check the next value
                j = value - 1; // should be at that index
            }
            // It's still O(n), because overwriting chain(s) cover numbers exactly once
        }
        // at this point all the values are at the appropriate indexes
        // check for the first gap
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                result = i + 1;
                break;
            }
        }
        return result;
    }

}