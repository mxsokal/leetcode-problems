import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class Solution {

    private static int[] solve(int[] numbers, int target) {
        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Target: " + target);
        Map<Integer, Integer> indexByRequiredSummand = new HashMap<>();
        int[] result = new int[0];
        for (int i = 0; i < numbers.length; i++) {
            Integer index = indexByRequiredSummand.get(numbers[i]);
            if (index != null) {
                result = new int[]{index, i};
                break;
            } else {
                int requiredSummand = target - numbers[i];
                indexByRequiredSummand.put(requiredSummand, i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] intArgs = Arrays.stream(args).
            mapToInt(Integer::parseInt).
            toArray();
        int[] result = new int[0];
        if (intArgs.length > 1) {
            int[] numbers = new int[intArgs.length - 1];
            System.arraycopy(intArgs, 0, numbers, 0, numbers.length);
            int target = intArgs[numbers.length];
            result = solve(numbers, target);
        }
        System.out.println("Result: " + Arrays.toString(result));
    }

}