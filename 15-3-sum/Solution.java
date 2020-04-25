import static java.util.Objects.requireNonNull;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*
    Problem Description:

        Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
        triplets in the array which gives the sum of zero.
        Note:
        The solution set must not contain duplicate triplets.

        Examples:

            Input: [-1, 0, 1, 2, -1, -4],
            Output: [[-1, 0, 1], [-1, -1, 2]]

    Time  complexity: O(n^2)
    Space complexity: O(log(n)) to O(n) depending on the sorting
*/
public final class Solution {

    public List<List<Integer>> solve(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        int number;
        int previousNumber = 0;

        requireNonNull(numbers, "numbers is null");
        // numbers[i] + numbers[j] + numbers[k] = 0  <=>  numbers[i] = -(numbers[j] + numbers[k])
        // where i != j != k
        // For each element numbers[i] let's find all numbers[j] and numbers[k] pairs.
        // This will give us all the result triplets, but there may be duplicates which we will need to exclude.
        // 1. Once we found numbers[j] and numbers[k] pairs for numbers[i], we should exclude numbers[i] from further
        //    search to prevent duplicates - numbers[i] is included in all possilbe triplets at that point.
        //    Although we can't exclude numbers[k] or numbers[j] yet, since they might pair differently producing other
        //    triplets for other elements.
        // 2. Once we found numbers[j] and numbers[k] pairs for numbers[i], we should exclude not only numbers[i] from
        //    further search, but all the elements which have the same value as well. They will not produce any new
        //    triplets.
        // 3. It's enough to check only numbers[i] <= 0. Positive numbers[i] won't give us any triplets considering the
        //    above observations.
        Arrays.sort(numbers); // O(n*log(n))
        for (int i = 0; (i < numbers.length) && (numbers[i] <= 0); i++) {
            number = numbers[i];
            // skip duplicate elements
            if ((i == 0) || (number != previousNumber)) {
                result.addAll(getTriplets(numbers, i));
            }
            previousNumber = number;
        }
        return result;
    }

    // Returns all triplets which include numbers[index] in the subarray [index, length-1]
    private List<List<Integer>> getTriplets(int[] numbers, int index) {
        List<List<Integer>> result = new ArrayList<>();
        int target = -numbers[index];
        int beginIndex = index + 1;
        int endIndex = numbers.length - 1;
        int sum;

        while (beginIndex < endIndex) {
            if ((beginIndex > index + 1) && (numbers[beginIndex - 1] == numbers[beginIndex])) {
                // if previous number was the same - skip it to exclude duplicates
                beginIndex++;
            } else if ((endIndex < numbers.length - 1) && (numbers[endIndex + 1] == numbers[endIndex])) {
                // if previous number was the same - skip it to exclude duplicates
                endIndex--;
            } else {
                sum = Math.addExact(numbers[beginIndex], numbers[endIndex]);
                if (target == sum) {
                    result.add(List.of(-target, numbers[beginIndex], numbers[endIndex]));
                    endIndex--;
                    beginIndex++;
                } else if (sum > target) {
                    // We can exclude numbers[endIndex] from further search, because each element after beginIndex is
                    // greater than (or equal to) numbers[beginIndex], and the sum of numbers[endIndex] and any of those
                    // elements can only become greater.
                    endIndex--;
                } else {
                    // We can exclude numbers[beginIndex] from further search, because each element before endIndex is
                    // smaller than (or equal to) numbers[endIndex], and the sum of numbers[beginIndex] and any of those
                    // elements can only become less.
                    beginIndex++;
                }
            }
        }
        return result;
    }

}