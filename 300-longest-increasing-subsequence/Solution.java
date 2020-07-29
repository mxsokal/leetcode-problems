import java.util.Arrays;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an unsorted array of integers, find the length of longest increasing subsequence.
        There may be more than one LIS combination, it is only necessary for you to return the length.

    Examples:

        Input: [10,9,2,5,3,7,101,18]
        Output: 

    Time  complexity: O(n*log(n))
    Space complexity: O(n)
*/
public final class Solution {

    public int solve(int[] numbers) {
        int[] minLastValueBySize;
        int length = 0;
        int index;

        requireNonNull(numbers, "numbers is null");
        // minLastValueBySize[i] == min value among the last values of subsequences having i+1 length
        minLastValueBySize = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            // minLastValueBySize is always sorted at [0, length-1], because (by induction):
            //  - when we update an element, we always decrease its value, but not less than the value of the preceding
            //    element;
            //  - we "insert" new elements only at the end.
            // Alternatively, if minLastValueBySize[i] = A, minLastValueBySize[i+k] = B,
            // A cannot be greater than B, because this would mean that part of the B sequence which is not included in
            // A (comes later in numbers, but still have elements smaller than A) would overwrite A.
            index = Arrays.binarySearch(minLastValueBySize, 0, length, numbers[i]);
            // if not found:  index = (-(insertion point) - 1)
            if (index < 0) {
                // insertion/update point
                index = -(index + 1);
            }
            minLastValueBySize[index] = numbers[i];
            if (index == length) {
                length++;
            }
        }
        // To be able to return the actual sequence, we need:
        //  - store indexes instead of values in minLastValueBySize
        //  - maintain additional array, where A[i] == index of the previous element (in numbers) of the subsequence ending at i (minLastValueBySize[index-1])
        //  - restore the longest subsequence from minLastValueBySize[length-1] following its chain in A
        return length;
    }

}