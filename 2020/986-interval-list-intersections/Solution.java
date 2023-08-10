import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

/*
    Problem Description:

        Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
        Return the intersection of these two interval lists.
        (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b. 
         The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented
         as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
            0 <= A.length < 1000
            0 <= B.length < 1000
            0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

        Examples:
            Input:  A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
            Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

    Time  complexity: O(m + n)
    Space complexity: O(m + n)
*/
public final class Solution {

    public int[][] solve(int[][] intervalsA, int[][] intervalsB) {
        List<int[]> result =  new ArrayList<>();
        int i = 0;
        int j = 0;
        int start;
        int end;

        requireNonNull(intervalsA, "intervalsA is null");
        requireNonNull(intervalsB, "intervalsB is null");
        while ((i < intervalsA.length) && (j < intervalsB.length)) {
            while ((j < intervalsB.length) && (intervalsA[i][0] > intervalsB[j][1])) {
                j++;
            }
            while ((j < intervalsB.length) && (((intervalsA[i][1] >= intervalsB[j][0])))) {
                start = max(intervalsA[i][0], intervalsB[j][0]);
                end = min(intervalsA[i][1], intervalsB[j][1]);
                result.add(new int[]{start, end});
                j++;
            }
            if ((!result.isEmpty()) && (result.get(result.size() - 1)[1] >= intervalsA[i][0])) {
                // If added at least one interval, then the previous interval in B may intersect with i + 1 in A.
                j--;
            }
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

}