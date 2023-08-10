import static java.util.Objects.requireNonNull;

public final class Solution {

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

        int length() {
            return endIndex - beginIndex + 1;
        }

    }

    private static interface IntBiPredicate {

        boolean test(int valueA, int valueB);

    }

    public int solve(int[] array) {
        int result = 0;

        requireNonNull(array, "array is null");
        if (array.length > 0) {
            result = doSolve(array).sum;
        }
        return result;
    }

    private SubArraySum doSolve(int[] array) {
        SubArraySum maxNonSplittedSum;
        SubArraySum maxSplittedSum;

        // Basically, there are two possible cases: either the max subarray is not splitted and represented by one interval,
        // or it is splitted and represented by two intervals.
        // Let's find a max subarray for each of the above cases and pick the maximum one.
        maxNonSplittedSum = getExtremumSubArraySum(array, (a, b) -> a < b); // just regular Kadane's Algorithm, O(n)
        maxSplittedSum = getMaxSplittedSubArraySum(array); // can be null, if all splitted have smaller sum than non-splitted
        return ((maxSplittedSum != null) && (maxSplittedSum.sum > maxNonSplittedSum.sum)) ? maxSplittedSum : maxNonSplittedSum;
    }

    private SubArraySum getExtremumSubArraySum(int[] array, IntBiPredicate predicate) {
        SubArraySum result = new SubArraySum(0, 0, array[0]);
        SubArraySum subResult = new SubArraySum(0, 0, array[0]);

        // Kadane's Algorithm, improved to find either max or min subarray depending on predicate
        // starting from the second element
        for (int i = 1; i < array.length; i++) {
            // assume a max/min subarray ends here, at i
            // then there are two possible cases:
            //     either it starts earlier, then it continues the max/min subarray in subResult, which ends at i - 1
            //     or it starts right here, at i
            // subresult always holds the i-1 max/min subarray
            subResult.endIndex = i;
            subResult.sum += array[i];
            if (predicate.test(subResult.sum, array[i])) {
                subResult.beginIndex = i;
                subResult.sum = array[i];
            }
            if (predicate.test(result.sum, subResult.sum)) {
                result.setFrom(subResult);
            }
        }
        return result;
    }

    private SubArraySum getMaxSplittedSubArraySum(int[] array) {
        SubArraySum result = null;
        SubArraySum minSubArraySum;
        int totalSum;

        // The logic is based on the following assumption:
        //
        //   [*****|----|************]
        //
        //      "---" - min subarray
        //      "***" - max subarray
        // Basically: max_subarray_sum = total_sum - min_subarray_sum

        // find min subarray, same way as for max
        minSubArraySum = getExtremumSubArraySum(array, (a, b) -> a > b); // just regular Kadane's Algorithm, O(n)
        if ((minSubArraySum.beginIndex != 0) && (minSubArraySum.endIndex != (array.length - 1))) {
            // if it actually splits array into two
            totalSum = getTotalSum(array);
            result = new SubArraySum(minSubArraySum.endIndex + 1, minSubArraySum.beginIndex - 1, totalSum - minSubArraySum.sum);
        }
        return result;
    }

    private int getTotalSum(int[] array) {
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }

}