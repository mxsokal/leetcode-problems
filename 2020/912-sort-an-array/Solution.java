import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of integers nums, sort the array in ascending order.

        Examples:
            Input: nums = [5,2,3,1]
            Output: [1,2,3,5]

    Time  complexity: O(n*log(n))
    Space complexity: O(n)
*/
public final class Solution {

    private static final int[] EMPTY_ARRAY = new int[0];

    public int[] solve(int[] numbers) {
        requireNonNull(numbers, "numbers is null");
        return sort(numbers, 0, numbers.length - 1);
    }

    private int[] sort(int[] numbers, int beginIndex, int endIndex) {
        int[] result = EMPTY_ARRAY;
        int[] leftNumbers;
        int[] rightNumbers;
        int divIndex;

        if (beginIndex < endIndex) {
            divIndex = beginIndex + ((endIndex - beginIndex) / 2);
            leftNumbers = sort(numbers, beginIndex, divIndex);
            rightNumbers = sort(numbers, divIndex + 1, endIndex);
            result = merge(leftNumbers, rightNumbers);
        } else if (beginIndex == endIndex) {
            result = new int[]{numbers[beginIndex]};
        }
        return result;
    }

    private int[] merge(int[] leftNumbers, int[] rightNumbers) {
        int[] result = new int[leftNumbers.length + rightNumbers.length];
        int i = 0;
        int j = 0;

        while (i < leftNumbers.length) {
            // take smaller or equal values from rightNumbers first
            while ((j < rightNumbers.length) && (rightNumbers[j] <= leftNumbers[i])) {
                result[i + j] = rightNumbers[j];
                j++;
            }
            result[i + j] = leftNumbers[i];
            i++;
        }
        // dump the rest from rightNumbers
        while (j < rightNumbers.length) {
            result[i + j] = rightNumbers[j];
            j++;
        }
        return result;
    }

}