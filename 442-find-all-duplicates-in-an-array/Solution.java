import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
        Find all the elements that appear twice in this array.
        Could you do it without extra space and in O(n) runtime?

        Examples:

            Input: [4,3,2,7,8,2,3,1]
            Output: [2,3]

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public List<Integer> solve(int[] numbers) {
        List<Integer> result = new ArrayList<>();
        int j;
        int next;
        int prev;

        requireNonNull(numbers, "numbers is null");
        for (int i = 0; i < numbers.length; i++) {
            if ((numbers[i] > 0) && (numbers[i] != (i + 1))) {
                j = i;
                prev = -1;
                while (j >= 0) {
                    next = numbers[j] - 1;
                    numbers[j] = prev + 1;
                    j = next;
                    prev = j;
                    if ((j >= 0) && (numbers[j] == (j + 1))) {
                        result.add(j + 1);
                        break;
                    }
                }
            }
        }
        return result;
    }

}