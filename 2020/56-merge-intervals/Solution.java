import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a collection of intervals, merge all overlapping intervals.

        Examples:

            Input: [[1,3],[2,6],[8,10],[15,18]]
            Output: [[1,6],[8,10],[15,18]]

            Input: [[1,4],[4,5]]
            Output: [[1,5]]

    Time  complexity: O(n*log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int[][] solve(int[][] intervals) {
        int[] interval = null;
        List<int[]> result = new ArrayList<>();

        requireNonNull(intervals, "intervals is null");
        if (intervals.length > 0) {
            Arrays.sort(intervals, (e1, e2) -> (e1[0] == e2[0]) ? 0 : (e1[0] < e2[0] ? -1 : 1));
            interval = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (interval[1] < intervals[i][0]) {
                    result.add(interval);
                    interval = intervals[i];
                } else if (interval[1] < intervals[i][1]) {
                    interval[1] = intervals[i][1];
                }
            }
            result.add(interval);
        }
        return result.toArray(new int[0][0]);
    }

}