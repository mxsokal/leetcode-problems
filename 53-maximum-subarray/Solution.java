import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.max;
import static java.util.Objects.requireNonNull;

public final class Solution {

    private static final class SubArraySum implements Comparable<SubArraySum> {

        int beginIndex;
        int endIndex;
        int sum;

        SubArraySum(int beginIndex, int endIndex, int sum) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }

        public int compareTo(SubArraySum o) {
            return (this.sum == o.sum) ? 0 :
                        ((this.sum > o.sum) ? 1 : -1);
        }

    }

    public int solve(int[] array) {
        int result = 0;

        requireNonNull(array, "array is null");
        if (array.length > 0) {
            result = solve(array, 0, array.length - 1).sum;
        }
        return result;
    }

    private SubArraySum solve(int[] array, int beginIndex, int endIndex) {
        List<SubArraySum> subResults = new ArrayList<>();
        int centerIndex;

        if (beginIndex < endIndex) {
            // beginIndex + ((endIndex - beginIndex) / 2);
            centerIndex = (endIndex + beginIndex) / 2;
            subResults.add(solve(array, beginIndex, centerIndex));
            subResults.add(solve(array, centerIndex + 1, endIndex));
            // crossing center
            subResults.add(findMaxSumSubArray(array, centerIndex, beginIndex, endIndex));
        } else if (beginIndex == endIndex) {
            subResults.add(new SubArraySum(beginIndex, endIndex, array[beginIndex]));
        }
        return max(subResults);
    }

    // finds the subarray which crosses crossIndex and has the maximum sum of its elements
    private SubArraySum findMaxSumSubArray(int[] array, int crossIndex, int minIndex, int maxIndex) {
        SubArraySum leftPart;
        SubArraySum rightPart;

        leftPart = findMaxSumSubArray(array, crossIndex, minIndex);
        rightPart = findMaxSumSubArray(array, crossIndex + 1, maxIndex);
        return new SubArraySum(leftPart.beginIndex, rightPart.endIndex, leftPart.sum + rightPart.sum);
    }

    // finds the subarray which has the maximum sum of its elements and starts at edgeIndex and ends at or before limitIndex,
    // or starts at or after limitIndex and ends at edgeIndex
    private SubArraySum findMaxSumSubArray(int[] array, int edgeIndex, int limitIndex) {
        SubArraySum result = new SubArraySum(edgeIndex, edgeIndex, array[edgeIndex]);
        int iterator = (edgeIndex <= limitIndex) ? 1 : -1;
        int sum = result.sum;

        for (int i = edgeIndex + iterator; i != (limitIndex + iterator); i = i + iterator) {
            sum += array[i];
            if (result.sum <= sum) {
                result.sum = sum;
                if (edgeIndex <= limitIndex) {
                    result.endIndex = i;
                } else {
                    result.beginIndex = i;
                }
            }
        }
        return result;
    }

}
