package net.mxs.leetcode.problem.twosum;

import java.util.Arrays;
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
                complement = getComplement(numbers[i], target);
                indexByComplement.put(complement, i);
            }
        }
        return result;
    }

    private Integer getComplement(int number, int target) {
        long complement;

        complement = target - number;
        if ((complement < Integer.MIN_VALUE) || (complement > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("number value " + number + " and target value "
                                                    + target + " produce integer overflow");
        }
        return Integer.valueOf((int) complement);
    }


    //-----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Solution solution = new Solution();
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
