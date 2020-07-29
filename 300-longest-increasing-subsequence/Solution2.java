import static java.util.Objects.requireNonNull;
import static java.lang.Math.max;

// DP, O(n^2)
public final class Solution2 {

    public int solve(int[] numbers) {
        int result = 0;
        int[] sizes;

        requireNonNull(numbers, "numbers is null");
        if (numbers.length > 0) {
            result = 1;
            sizes = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                sizes[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if ((numbers[j] < numbers[i]) && (sizes[i] < (sizes[j] + 1))) {
                        sizes[i] = sizes[j] + 1;
                    }
                }
                result = max(result, sizes[i]);
            }
        }
        return result;
    }

}