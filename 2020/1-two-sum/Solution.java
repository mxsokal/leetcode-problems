import java.util.Map;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:


        Given an array of integers, return indices of the two numbers such that they add up to a specific target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.

        Example: 

        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].


    Solution Description: just use a hash table to store complements + original indexes

    Pseudo Code:

        for $n : $numbers
            if ($map has $n)
                return [$map[$n], $i]
            else
                $map[$target - $n] = $i

    Time  complexity: O(n)
    Space complexity: O(n)
*/
public final class Solution {

    public int[] solve(int[] numbers, int target) {
        Map<Integer, Integer> indexByComplement = new HashMap<>();
        int[] result = new int[0];
        Integer complement;

        requireNonNull(numbers, "numbers is null");
        for (int i = 0; i < numbers.length; i++) {
            // access by key ~O(1)
            if (indexByComplement.containsKey(numbers[i])) {
                result = new int[]{indexByComplement.get(numbers[i]), i};
                break;
            } else {
                complement = Math.addExact(target, -numbers[i]);
                indexByComplement.put(complement, i);
            }
        }
        return result;
    }

}