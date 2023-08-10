import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a collection of distinct integers, return all possible permutations.

        Examples:

            Input: [1,2,3]
            Output: [
                      [1,2,3],
                      [1,3,2],
                      [2,1,3],
                      [2,3,1],
                      [3,1,2],
                      [3,2,1]
                    ]

    Time  complexity: O(n*n!)

    T(n) = n * (T(n) + c1)
        and
    T(0) = c2 * n

    T(n) = n*T(n-1) + c1*n
        = n*((n-1)*T(n-2) + c1*(n-1)) + c1*n
        = n*(n-1)*T(n-2) + c1*n*(n-1) + c1*n
        = n*(n-1)*(n-2)*T(n-3) + c1*n*(n-1)*(n-2) + c1*n*(n-1) + c1*n
        = c2*n*n! + c1(n!/(n-1)! + n!/(n-2)! + ... + n!)
        = c2*n*n! + c1*n!*(1/(n-1)! + 1/(n-2)! + ... + 1)
        = O(n*n!)

    Space complexity: O(n*n!), including result
*/
public final class Solution {

    public List<List<Integer>> solve(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();

        requireNonNull(numbers, "numbers is null");
        generatePremutations(result, toList(numbers), 0);
        return result;
    }

    private List<Integer> toList(int[] numbers) {
        return Arrays.stream(numbers)
            .boxed()
            .collect(Collectors.toList());
    }

    private void generatePremutations(List<List<Integer>> result, List<Integer> numbers, int startIndex) {
        if (startIndex == (numbers.size() - 1)) {
            // numbers contains a premutation
            result.add(new ArrayList<>(numbers));
        }
        for (int i = startIndex; i < numbers.size(); i++) {
            // make i-th number first in the current premutation part
            Collections.swap(numbers, startIndex, i);
            // generate premutations
            generatePremutations(result, numbers, startIndex + 1);
            // revert back
            Collections.swap(numbers, startIndex, i);
        }
    }

}