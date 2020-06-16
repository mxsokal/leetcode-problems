import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
        You may assume that the intervals were initially sorted according to their start times.

        Examples:

            Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
            Output: [[1,5],[6,9]]

            Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
            Output: [[1,2],[3,10],[12,16]]

    Time  complexity: O(n)
    Space complexity: O(n), including result
*/
public final class Solution {

    public int[][] solve(int[][] intervals, int[] interval) {
        List<int[]> result = new ArrayList<>();
        int[] mergedInterval = new int[2];
        int index = 0;

        requireNonNull(intervals, "intervals is null");
        requireNonNull(interval, "interval is null");
        // add preceding
        while ((index < intervals.length) && (intervals[index][1] < interval[0])) {
            result.add(intervals[index]);
            index++;
        }
        if (index < intervals.length) {
            // determine start
            if ((interval[0] >= intervals[index][0]) && (interval[0] <= intervals[index][1])) {
                mergedInterval[0] = intervals[index][0];
            } else {
                mergedInterval[0] = interval[0];
            }
            // skip middle
            while ((index < intervals.length) && (intervals[index][1] < interval[1])) {
                index++;
            }
            // determine end
            if ((index < intervals.length)
              && (interval[1] >= intervals[index][0])
              && (interval[1] <= intervals[index][1])) {
                mergedInterval[1] = intervals[index][1];
                index++;
            } else {
                mergedInterval[1] = interval[1];
            }
            result.add(mergedInterval);
            // add following
            while (index < intervals.length) {
                result.add(intervals[index]);
                index++;
            }
        } else {
            result.add(interval);
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(java.util.Arrays.deepToString(s.solve(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8})));
    }

}