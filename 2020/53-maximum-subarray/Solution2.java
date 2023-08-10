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
        int result = 0;

        requireNonNull(array, "array is null");
        if (array.length > 0) {
            result = doSolve(array).sum;
        }
        return result;
    }

    private SubArraySum doSolve(int[] array) {
        SubArraySum result = new SubArraySum(0, 0, array[0]);
        SubArraySum subResult = new SubArraySum(0, 0, array[0]);

        // starting from the second element
        for (int i = 1; i < array.length; i++) {
            // assume a max subarray ends here, at i
            // then there are two possible cases:
            //     either it starts earlier, then it continues the max subarray in subResult, which ends at i - 1
            //     or it starts right here, at i
            // subresult always holds the i-1 max subarray
            subResult.endIndex = i;
            subResult.sum += array[i];
            if (subResult.sum < array[i]) {
                subResult.beginIndex = i;
                subResult.sum = array[i];
            }
            if (result.sum < subResult.sum) {
                result.setFrom(subResult);
            }
        }
        return result;
    }

}
