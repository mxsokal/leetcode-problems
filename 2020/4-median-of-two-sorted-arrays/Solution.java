import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

/*
    Problem Description:

        There are two sorted arrays nums1 and nums2 of size m and n respectively.
        Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
        You may assume nums1 and nums2 cannot be both empty.

        Example 1:

            nums1 = [1, 3]
            nums2 = [2]

            The median is 2.0

        Example 2:

            nums1 = [1, 2]
            nums2 = [3, 4]

            The median is (2 + 3)/2 = 2.5


    Solution Description:

    Time  complexity: O(log(min(m,n)))
    Space complexity: O(1)
*/
public final class Solution {

    public double solve(int[] a, int[] b) {
        double median;

        requireNonNull(a, "a is null");
        requireNonNull(b, "b is null");
        if ((a.length == 0) && (b.length == 0)) {
            throw new IllegalArgumentException("a and b are empty");
        // If A or B is empty, simply calculate the median for the non-empty array.
        } else if (a.length == 0) {
            median = getMedian(b);
        } else if (b.length == 0) {
            median = getMedian(a);
        // Pass the smaller array first to execute faster (search is performed in the first array).
        } else if (a.length <= b.length) {
            median = solve(a, b, 0, a.length - 1);
        } else {
            median = solve(b, a, 0, b.length - 1);
        }
        return median;
    }

    // Calculates the median for a non-empty array.
    private double getMedian(int[] x) {
        int middleIndex = x.length / 2;

        return (x.length == 1) ? x[0] : getMedian(x[middleIndex - 1], x[middleIndex], x[middleIndex], x.length);
    }

    private double solve(int[] a, int[] b, int ai, int aj) {
        int totalLength;
        int aDivIndex;
        int bDivIndex;
        int leftMax;
        int rightMin;
        double median;

        totalLength = a.length + b.length;
        // Divide the current A window in half keeping the middle element (if any) in the left part.
        // The left part is always non-empty (since A is non-empty).
        // aDivIndex always points to the last element of the left part.
        aDivIndex = (aj - ai) / 2 + ai;
        // Considering A is divided by aDivIndex, calculate how B should be divided to keep half of the total elements
        // (A + B) and the middle element (if any) in the left parts of A and B.
        // bDivIndex is either -1 (empty left part) or an index in B pointing to the last element of the left part.
        // Alternatively: bDivIndex = totalLength - totalLength / 2 - (aDivIndex + 1) - 1;
        bDivIndex = (totalLength - 1) / 2 - (aDivIndex + 1);
        // Find the maximum element in the left part.
        leftMax = apply(Math::max, a, aDivIndex, b, bDivIndex);
        // Find the minimum element in the right part.
        rightMin = apply(Math::min, a, aDivIndex + 1, b, bDivIndex + 1);
        if (leftMax <= rightMin) {
            // The division is valid, just calc the median value and that's it.
            median = getMedian(leftMax, rightMin, leftMax, totalLength);
        } else if (ai==aj) {
            // A has just one element left in the window - can't divide further.
            // Let's adjust the division and calc the median.
            aDivIndex--;
            bDivIndex++;
            leftMax = apply(Math::max, a, aDivIndex, b, bDivIndex);
            rightMin = apply(Math::min, a, aDivIndex + 1, b, bDivIndex + 1);
            median = getMedian(leftMax, rightMin, leftMax, totalLength);
        } else {
            if (a[aDivIndex] >= rightMin) {
                // must decrease the value in A - dividing the left part
                return solve(a, b, ai, aDivIndex);
            } else {
                // can increase the value in A decreasing the value in B - dividing the right part
                return solve(a, b, aDivIndex + 1, aj);
            }
        }
        return median;
    }

    // If length is even, calculates the median as arithmetic mean of left and right, otherwise returns middle.
    private double getMedian(int left, int right, int middle, int length) {
        // cast to long is needed to prevent int overflow
        return isEven(length) ? ((long) left + right) / 2.0 : middle;
    }

    private int apply(IntBinaryOperator operator, int[] a, int i, int[] b, int j) {
        int value = 0;

        if ((i >= 0) && (i < a.length) && (j >= 0) && (j < b.length)) {
            value = operator.applyAsInt(a[i], b[j]);
        } else if ((i >= 0) && (i < a.length)) {
            value = a[i];
        } else {
            value = b[j];
        }
        return value;
    }

    private boolean isEven(int value) {
        return ((value % 2) == 0);
    }

}
