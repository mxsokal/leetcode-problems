import java.util.Arrays;

/*

  The idea is to extend the array with indexed complements for each element, then utilize the merge sort
algorithm to find duplicates.

Time complexity: O(n*log(n))

*/

public final class MySolution {

    private static final int OVERFLOW_MARKER = -1;


    public int[] twoSum(int[] nums, int target) {
        return solve(nums, target);
    }


    public int[] solve(int[] numbers, int target) {
        int[][] complements;
        int[] result;

        complements = getComplements(numbers, target);
        result = solve(complements, numbers.length);
        return result;
    }

    private int[][] getComplements(int[] numbers, long target) {
        final int ELEMENT_SIZE = 2;
        int[][] result = new int[numbers.length + numbers.length][];
        long complement;
        int j;

        for (int i = 0; i < numbers.length; i++) {
            result[i] = new int[ELEMENT_SIZE];
            result[i][0] = numbers[i];
            result[i][1] = i;
            j = i + numbers.length;
            result[j] = new int[ELEMENT_SIZE];
            complement = target - numbers[i];
            if ((complement <= Integer.MAX_VALUE) && (complement >= Integer.MIN_VALUE)) {
                result[j][0] = (int) complement;
                result[j][1] = j;
            } else {
                result[j][0] = 0;
                result[j][1] = OVERFLOW_MARKER;
            }
        }
        return result;
    }

    private int[] solve(int[][] complements, int numberCount) {
        int[][][] splittedComplements;
        int[] result = null;

        if (complements.length > 1) {
            splittedComplements = splitInTwo(complements);
            result = solve(splittedComplements[0], numberCount);
            if (result == null) {
                result = solve(splittedComplements[1], numberCount);
                if (result == null) {
                    result = merge2(complements, splittedComplements[0], splittedComplements[1], numberCount);
                }
            }
        }
        return result;
    }

    private int[] merge2(int[][] resultArray, int[][] leftArray, int[][] rightArray, int numberCount) {
        int i = 0;
        int j = 0;
        int[] result = null;

        while (i < leftArray.length) {
            // take smaller or equal values from rightArray first
            while ((j < rightArray.length) && (rightArray[j][0] <= leftArray[i][0])) {
                result = checkAndAssing(resultArray, rightArray, i + j, j, numberCount);
                if (result != null) {
                    return result;
                }
                j++;
            }
            result = checkAndAssing(resultArray, leftArray, i + j, i, numberCount);
            if (result != null) {
                return result;
            }
            i++;
        }
        // dump the rest from rightArray
        while (j < rightArray.length) {
            result = checkAndAssing(resultArray, rightArray, i + j, j, numberCount);
            if (result != null) {
                return result;
            }
            j++;
        }
        return result;
    }

    private int[] checkAndAssing(int[][] destArray, int[][] srcArray, int i, int j, int numberCount) {
        int[] result = null;

        destArray[i] = srcArray[j];

        if ((i != 0) && (destArray[i - 1][0] == destArray[i][0])
          && (((destArray[i - 1][1] < numberCount) && (destArray[i][1] >= numberCount)) || ((destArray[i][1] < numberCount) && (destArray[i - 1][1] >= numberCount))) ) {
            result = new int[2];
            result[1] = (destArray[i - 1][1] < numberCount) ? destArray[i - 1][1] : destArray[i - 1][1] - numberCount;
            result[0] = (destArray[i][1] < numberCount) ? destArray[i][1] : destArray[i][1] - numberCount;
        }
        return result;
    }

    private int[][][] splitInTwo(int[][] complements) {
        final int DIVISOR = 2;
        int[][][] result = new int[DIVISOR][][];

        result[0] = new int[complements.length / DIVISOR][];
        result[1] = new int[complements.length - result[0].length][];
        System.arraycopy(complements, 0, result[0], 0, result[0].length);
        System.arraycopy(complements, result[0].length, result[1], 0, result[1].length);
        return result;
    }

    public static void main(String[] args) {
        MySolution solution = new MySolution();
        int[] numbers;
        int target;
        int[] result;

        numbers = Arrays.stream(args[0].split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        target = Integer.parseInt(args[1]);
        result = solution.solve(numbers, target);
        System.out.println(Arrays.toString(result));
    }

}
