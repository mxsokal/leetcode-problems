import java.util.Arrays;
import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class Solution {

    private static final class DimensionSubArray {

        int[][] array;
        int subArrayIndex;
        int dimension;

        DimensionSubArray(int[][] array) {
            this.array = array;
        }

        int get(int index) {
            return (dimension == 0) ? array[subArrayIndex][index] : array[index][subArrayIndex];
        }

        void set(int index, int value) {
            if (dimension == 0) {
                array[subArrayIndex][index] = value;
            } else {
                array[index][subArrayIndex] = value;
            }
        }

        int length() {
            return (dimension == 0) ? array[subArrayIndex].length : array.length;
        }

    }

    public int solve(int[][] heights) {
        DimensionSubArray heightsDimension = new DimensionSubArray(heights);
        DimensionSubArray maxTrapLevels = new DimensionSubArray(new int[heights.length][]);
        int volume = 0;

        for (int i = 0; i < heights.length; i++) {
            heightsDimension.subArrayIndex = i;
            maxTrapLevels.subArrayIndex = i;
            maxTrapLevels.array[i] = new int[heights[0].length];
            solve(heightsDimension, maxTrapLevels);
        }

/*        heightsDimension.dimension = 1;
        maxTrapLevels.dimension = 1;
        for (int i = 0; i < heights[0].length; i++) {
            heightsDimension.subArrayIndex = i;
            maxTrapLevels.subArrayIndex = i;
            volume += solve(heightsDimension, maxTrapLevels);
        }
*/
        int[][] levels = maxTrapLevels.array;

        for (int i = 0; i < levels.length; i++) {
            System.out.println(Arrays.toString(levels[i]));
        }


        return volume;
    }

    public int solve(DimensionSubArray heights, DimensionSubArray waterLevels) {
        int leftIndex;
        int rightIndex;
        int leftMaxHeight = 0;
        int rightMaxHeight = 0;
        int volume = 0;

        leftIndex = 0;
        rightIndex = heights.length() - 1;
        while (leftIndex < rightIndex) {
            leftMaxHeight = max(leftMaxHeight, heights.get(leftIndex));
            rightMaxHeight = max(rightMaxHeight, heights.get(rightIndex));
            if (leftMaxHeight > rightMaxHeight) {
                if (waterLevels.get(rightIndex) == 0) {
                    waterLevels.set(rightIndex, rightMaxHeight - heights.get(rightIndex));
                } else {
                    waterLevels.set(rightIndex, min(rightMaxHeight, waterLevels.get(rightIndex)));
                    volume += waterLevels.get(rightIndex) - heights.get(rightIndex);
                }
                //volume += rightMaxHeight - heights.get(rightIndex);
                rightIndex--;
            } else {
                if (waterLevels.get(leftIndex) == 0) {
                    waterLevels.set(leftIndex, leftMaxHeight - heights.get(leftIndex));
                } else {
                    waterLevels.set(leftIndex, min(leftMaxHeight, waterLevels.get(leftIndex)));
                    volume += waterLevels.get(leftIndex) - heights.get(leftIndex);
                }
                //volume += leftMaxHeight - heights.get(leftIndex);
                leftIndex++;
            }
        }
        return volume;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(
        s.solve(new int[][]{

{12,13,1,12},
{13,4,13,12},
{13,8,10,12},
{12,13,12,12},
{13,13,13,13}


        }));
    }

}