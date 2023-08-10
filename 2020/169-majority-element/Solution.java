import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of size n, find the majority element. The majority element is the element that appears more than
        ⌊ n/2 ⌋ times.
        You may assume that the array is non-empty and the majority element always exist in the array.

        Examples:

            Input: [3,2,3]
            Output: 3

            Input: [2,2,1,1,1,2,2]
            Output: 2
        
    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public int solve(int[] numbers) {
        Map<Integer, Integer> countByNumber;
        Entry<Integer, Integer> maxNumberCount;

        requireNonNull(numbers, "numbers is null");
        countByNumber = getCountByNumber(numbers);
        maxNumberCount = getMaxNumberCount(countByNumber);
        return ((maxNumberCount != null) && (maxNumberCount.getValue() > (numbers.length / 2)))
                ? maxNumberCount.getKey()
                : -1;
    }

    private Map<Integer, Integer> getCountByNumber(int[] numbers) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            result.merge(numbers[i], 1, (o, n) -> o + n);
        }
        return result;
    }

    private Entry<Integer, Integer> getMaxNumberCount(Map<Integer, Integer> countByNumber) {
        Entry<Integer, Integer> result = null;

        for (Entry<Integer, Integer> numberCount : countByNumber.entrySet()) {
            if ((result == null) || (numberCount.getValue() > result.getValue())) {
                result = numberCount;
            }
        }        
        return result;
    }

}