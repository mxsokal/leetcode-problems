import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
        (Here, the distance between two points on a plane is the Euclidean distance.)
        You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
            1 <= K <= points.length <= 10000
            -10000 < points[i][0] < 10000
            -10000 < points[i][1] < 10000

        Examples:

            Input: points = [[1,3],[-2,2]], K = 1
            Output: [[-2,2]]

            Input: points = [[3,3],[5,-1],[-2,4]], K = 2
            Output: [[3,3],[-2,4]]

    Time Complexity: O(n^2), Ω(n) on average
    Space Complexity: O(1)
*/
public final class Solution {

    public int[][] solve(int[][] points, int count) {
        // based on quicksort
        select(points, 0, points.length - 1, count);
        return Arrays.copyOf(points, count);
    }

    private void select(int[][] points, int beginIndex, int endIndex, int count) {
        int pivotIndex;
        int divIndex;

        if (beginIndex < endIndex) {
            pivotIndex = getPivotIndex(beginIndex, endIndex);
            divIndex = partition(points, beginIndex, endIndex, pivotIndex);
            // since we don't need full sort - just one recursion call
            if (divIndex > count) {
                select(points, beginIndex, divIndex - 1, count);
            } else if (divIndex < count - 1) {
                select(points, divIndex + 1, endIndex, count);
            }
        }
    }

    private int getPivotIndex(int beginIndex, int endIndex) {
        return ThreadLocalRandom.current().nextInt(beginIndex, endIndex + 1);
    }

    private int partition(int[][] points, int beginIndex, int endIndex, int pivotIndex) {
        int divIndex = beginIndex;
        int[] pivot;
        int[] point;

        if (pivotIndex != beginIndex) {
            swap(points, pivotIndex, beginIndex);
        }
        pivot = points[beginIndex];
        for (int i = beginIndex + 1; i <= endIndex; i++) {
            point = points[i];
            if (closerOrSameDistance(point, pivot)) {
                divIndex++;
                swap(points, divIndex, i);
            }
        }
        swap(points, beginIndex, divIndex);
        return divIndex;
    }

    private void swap(int[][] points, int fromIndex, int toIndex) {
        int[] point;

        if (fromIndex != toIndex) {
            point = points[toIndex];
            points[toIndex] = points[fromIndex];
            points[fromIndex] = point;
        }
    }

    private boolean closerOrSameDistance(int[] pointA, int[] pointB) {
        return Math.sqrt(pointA[0] * pointA[0] + pointA[1] * pointA[1]) <= Math.sqrt(pointB[0] * pointB[0] + pointB[1] * pointB[1]);
    }

}