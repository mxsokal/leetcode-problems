import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a set of distinct integers, nums, return all possible subsets (the power set).
        The solution set must not contain duplicate subsets.

        Examples:

            Input: nums = [1,2,3]
            Output: [
                      [3],
                      [1],
                      [2],
                      [1,2,3],
                      [1,3],
                      [2,3],
                      [1,2],
                      []
                    ]

    Time  complexity: O(n*(2^n))
    Space complexity: O(n*(2^n))

        A set of n elements has 2^n subsets. It's similar to a sequence of n bits where each bit corresponds to one
        element of the set indicating whether it's included into a subset or not. The total number of variants is 2^n.

        Assuming there are n recursion levels and i distinct elements on the i'th level, the total number of elements on
        each level (the number of elements in all the subsets on that level) is i*(2^(i-1)). Following the above analogy,
        each distinct element is included (the bit is set) in 2^(i-1) different subsets.
        The total number of elements is sum[i:0,n](i*(2^(i-1)))

            C*n*(2^(n-1)) >= n*(2^(n-1)) + (n-1)*(2^(n-2)) + ... + 1
            C >= 1 + ((n-1)*(2^(n-2))) / (n*(2^(n-1))) + ... + 1 / (n*(2^(n-1))  =>  sum[i:0,n](i*(2^(i-1))) = O(n*(2^n))

*/
public final class Solution {

    public List<List<Integer>> solve(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();

        requireNonNull(numbers, "numbers is null");
        addSubsets(result, numbers, 0);
        return result;
    }

    private void addSubsets(List<List<Integer>> result, int[] numbers, int beginIndex) {
        List<Integer> subset;
        int count;

        if (beginIndex < numbers.length) {
            addSubsets(result, numbers, beginIndex + 1);
            count = result.size();
            for (int i = 0; i < count; i++) {
                subset = new ArrayList<>(result.get(i));
                subset.add(numbers[beginIndex]);
                result.add(subset);
            }
        } else {
            result.add(new ArrayList<>());
        }
    }

}