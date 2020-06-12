import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
            The number of elements initialized in nums1 and nums2 are m and n respectively.
            You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
            elements from nums2.

        Examples:

            Input: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6],       n = 3
            Output: [1,2,2,3,5,6]

    Time  complexity: O(n+m)
    Space complexity: O(1)

*/
public final class Solution {

    public void solve(int[] numbersA, int aLength, int[] numbersB, int bLength) {
        int aIndex = aLength - 1;
        int bIndex = bLength - 1;
        int resultIndex = numbersA.length - 1;

        requireNonNull(numbersA, "numbersA is null");
        requireNonNull(aLength >= 0, "aLength is negative");
        requireNonNull(numbersB, "numbersB is null");
        requireNonNull(bLength >= 0, "bLength is negative");
        requireNonNull((numbersA.length + numbersB.length) == aLength, "lengths are inconsistent");
        while (aIndex >= 0) {
            while ((bIndex >= 0) && (numbersA[aIndex] <= numbersB[bIndex])) {
                numbersA[resultIndex--] = numbersB[bIndex--];
            }
            numbersA[resultIndex--] = numbersA[aIndex--];
        }
        while (bIndex >= 0) {
                numbersA[resultIndex--] = numbersB[bIndex--];
        }
    }

}