import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        You are given a sorted array consisting of only integers where every element appears exactly twice, except
        for one element which appears exactly once. Find this single element that appears only once.
        Your solution should run in O(log n) time and O(1) space.

        Examples:

            Input: [1,1,2,3,3,4,4,8,8]
            Output: 2

            Input: [3,3,7,7,10,11,11]
            Output: 10
        
    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int[] numbers) {
        requireNonNull(numbers, "numbers is null");
        if (numbers.length == 0) {
            throw new IllegalArgumentException("numbers is empty");
        }
        return solve(numbers, 0, numbers.length - 1);
    }

    private int solve(int[] numbers, int beginIndex, int endIndex) {
        int result = numbers[beginIndex];
        int divIndex;

        if (beginIndex != endIndex) {
            divIndex = beginIndex + (endIndex - beginIndex) / 2;
            if (numbers[divIndex] == numbers[divIndex + 1]) {
                // don't split middle pair
                divIndex--;
            }
            // proceed with even-length subarray
            result = ((divIndex - beginIndex + 1) % 2 == 0)
                ? solve(numbers, divIndex + 1, endIndex)
                : solve(numbers, beginIndex, divIndex);
        }
        return result;
    }

}