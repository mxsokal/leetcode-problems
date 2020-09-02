import java.util.Map;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;
import static java.lang.Math.abs;

/*
    Problem Description:

        Given an array of integers, find out whether there are two distinct indices i and j in the array such that
        the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is
        at most k.

        Examples:

            Input: nums = [1,2,3,1], k = 3, t = 0
            Output: true

            Input: nums = [1,0,1,1], k = 1, t = 2
            Output: true

            Input: nums = [1,5,9,1,5,9], k = 2, t = 3
            Output: false

    Time  complexity: O(n)
    Space complexity: O(n+maxIndexDiff)
*/
public final class Solution {

    public boolean solve(int[] numbers, int maxIndexDiff, int maxValueDiff) {
        Map<Long, Long> buckets = new HashMap<>();
        boolean result = false;
        Long bucketId;
        long value;

        /*
            On each iteration check the values in the current index window: [i - maxIndexDiff, i].
            Distribute all the values in the current index window in buckets of size maxValueDiff + 1. This way, if two
            values come into the same bucket, then their absolute difference is less than maxValueDiff. For each new
            value added into the buckets, check the values in the adjacent buckets.
        */
        requireNonNull(numbers, "numbers is null");
        if ((maxIndexDiff > 0) && (maxValueDiff >= 0)) {
            for (int i = 0; i < numbers.length; i++) {
                value = numbers[i];
                bucketId = getBucketId(value, maxValueDiff);
                if ((buckets.containsKey(bucketId))
                  || (hasCloseValue(buckets, bucketId - 1, value, maxValueDiff))
                  || (hasCloseValue(buckets, bucketId + 1, value, maxValueDiff))) {
                    result = true;
                    break;
                } else {
                    buckets.put(bucketId, value);
                }
                if (i >= maxIndexDiff) {
                    value = numbers[i - maxIndexDiff];
                    bucketId = getBucketId(value, maxValueDiff);
                    buckets.remove(bucketId);
                }
            }            
        }
        return result;
    }

    private long getBucketId(long value, long maxValueDiff) {
        /*
            Example:
                maxValueDiff = 7

                bucketId:      -2      |    -1     |      0      |     1    |        2      |   3
                values:     ... -10 -9 -8 -7 ... -1 0 1 2 3 ... 7 8 9 ... 15 16 17 18 ... 23 24 ... 
        */
        return (value >= 0)
                ? value / (maxValueDiff + 1)
                : (value + 1) / (maxValueDiff + 1) - 1;
    }

    private boolean hasCloseValue(Map<Long, Long> buckets, Long bucketId, long value, int maxValueDiff) {
        boolean result = false;
        long bucketValue;

        if (buckets.containsKey(bucketId)) {
            bucketValue = buckets.get(bucketId);
            result = (abs(bucketValue - value) <= maxValueDiff);
        }
        return result;
    }

}