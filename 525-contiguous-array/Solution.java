import java.util.Arrays;
import static java.lang.Math.max;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1. 

        Examples:

            Input: [0,1]
            Output: 2

            Input: [0,1,0]
            Output: 2
        
    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public int solve(int[] numbers) {
        int[] indexByCount;
        int count = 0;
        int length = 0;
        int result = 0;

        requireNonNull(numbers, "numbers is null");
        /*
            0: count = count - 1
            1: count = count + 1

            Two indexes have the same count value <=> the subarray between those indexes have the same number of zeros
            and ones (count == 0).
        */
        indexByCount = new int[2 * numbers.length + 1]; // to store first index for each count value [-numbers.length; numbers.length]
        Arrays.fill(indexByCount, -1);
        for (int i = 0; i < numbers.length; i++) {
            count = (numbers[i] == 0) ? count - 1 : count + 1;
            if (count == 0) {
                // just pick the max subarray so far
                length = i  + 1;
            } else if (indexByCount[numbers.length + count] >= 0) {
                // already seen that count - calc length
                length = i - indexByCount[numbers.length + count];
            } else {
                // first time see that count - store its index
                indexByCount[numbers.length + count] = i;
            }
            result = max(length, result);
        }
        return result;
    }

}