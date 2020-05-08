import static java.util.Objects.requireNonNull;
/*
    Problem Description:

        You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
        Check if these points make a straight line in the XY plane.

        Examples:

            Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
            Output: true

            Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
            Output: false
        
    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public boolean solve(int[][] coordinates) {
        final double PRECISION = 0.1;
        Double k;
        double b;
        boolean result = false;

        // y1 = kx1 + b,  y2 = kx2 + b
        requireNonNull(coordinates, "coordinates is null");
        k = getK(coordinates);
        if (k != null) {
            result = true;
            b = getB(k, coordinates);
            // Check that the rest of the coordinates conform to k and b
            for (int i = 2; i < coordinates.length; i++) {
                if (Math.abs((coordinates[i][1] - (k * coordinates[i][0]) - b)) > PRECISION) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private Double getK(int[][] coordinates) {
        double dividend;
        double divisor;

        if ((coordinates.length <= 1) || (coordinates[0].length <= 1) || (coordinates[1].length <= 1)) {
            throw new IllegalArgumentException("not enough coordinates");
        }
        dividend = coordinates[1][1] - coordinates[0][1]; // y2-y1
        divisor = coordinates[1][0] - coordinates[0][0]; // x2-x1
        return (divisor != 0) ? (dividend / divisor) : null;
    }

    private double getB(double k, int[][] coordinates) {
        return coordinates[0][1] - k * coordinates[0][0];
    }

}