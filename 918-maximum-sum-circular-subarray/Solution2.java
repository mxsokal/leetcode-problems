import static java.util.Objects.requireNonNull;

public final class Solution2 {

    private static final class SubArraySum {

        int beginIndex;
        int endIndex;
        int sum;

        SubArraySum(int beginIndex, int endIndex, int sum) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }

        void setFrom(SubArraySum other) {
            this.beginIndex = other.beginIndex;
            this.endIndex = other.endIndex;
            this.sum = other.sum;
        }

    }

    public int solve(int[] array) {
        requireNonNull(array, "array is null");
        return (array.length > 0)
            ? ((array.length == 1) ? array[0] : doSolve(array).sum)
            : 0;
    }

    private SubArraySum doSolve(int[] array) {
        SubArraySum result = new SubArraySum(0, 0, array[0]);
        SubArraySum subResult = new SubArraySum(0, 0, array[0]);
        int index = 1;
        int[] beginIndexJumpMap;

        // Kadane's Algorithm, extended to cycle through array two times.
        // When cycling, subResult.endIndex may meet subResult.beginIndex. In that case, we need to shift subResult.beginIndex
        // to the position where the subarray [subResult.beginIndex, array.length - 1] has the maximum sum. This ensures
        // that our splitted subResult has the maximum sum among all *splitted* subarrays in that range. (we don't care
        // about non-splitted at that point - we've already checked them).
        // 
        // Let's precalculte all such shifts, O(n).
        beginIndexJumpMap = getMaxTailSumJumpMap(array); // index -> first_index_of_max_tail_subarray,>=index
        // start from the second element
        // while not meeting subResult.beginIndex or subResult.beginIndex is shifted successfully (beginIndex < length - 1)
        while ((index != subResult.beginIndex) || (jumpBeginIndex(subResult, array, beginIndexJumpMap))) {
            // regular Kadane's Algorithm logic
            subResult.endIndex = index;
            subResult.sum += array[index];
            if (subResult.sum < array[index]) {
                // Here we need to move subResult.beginIndex. We can move it no further than array.length - 1
                // otherwise, will be cycling infinitly
                if (index <= subResult.beginIndex) {
                    break;
                }
                subResult.beginIndex = index;
                subResult.sum = array[index];
            }
            if (result.sum < subResult.sum) {
                result.setFrom(subResult);
            }
            index = (index < array.length - 1) ? index + 1 : 0;
        }
        return result;
    }

    private boolean jumpBeginIndex(SubArraySum subArraySum, int[] array, int[] jumpMap) {
        boolean result = false;
        int newIndex;

        if (subArraySum.beginIndex < array.length - 1) {
            // if still have some indexes to jump
            result = true;
            // get the optimal index starting from beginIndex + 1
            newIndex = jumpMap[subArraySum.beginIndex + 1];
            // correct the sum
            for (int i = subArraySum.beginIndex; i < newIndex; i++) {
                subArraySum.sum -= array[i];
            }
            subArraySum.beginIndex = newIndex;
        }
        return result;
    }

    private int[] getMaxTailSumJumpMap(int[] array) {
        int[] result = new int[array.length];
        int endIndex = array.length - 1;
        int maxSum;
        int sum;

        sum = array[endIndex];
        maxSum = sum;
        result[endIndex] = endIndex;
        for (int i = endIndex - 1; i >= 0; i--) {
            sum += array[i];
            if (sum >= maxSum) {
                result[i] = i;
                maxSum = sum;
            } else {
                result[i] = result[i + 1];
            }
        }
        return result;
    }

}