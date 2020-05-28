/*
    Problem Description:

        Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of
        1's in their binary representation and return them as an array.
            It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear 
            time O(n) /possibly in a single pass?
            Space complexity should be O(n).
            Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
            other language.

        Examples:

            Input: 2
            Output: [0,1,1]

            Input: 5
            Output: [0,1,1,2,1,2]
        
    Time  complexity: O(n)
    Space complexity: O(n), including result
*/
public final class Solution {

    public int[] solve(int number) {
        int[] result;

        if (number < 0) {
            throw new IllegalArgumentException("number value " + number + " is not positive");
        }
        result = new int[number + 1];
        for (int i = 1; i <= number; i++) {
            result[i] = result[i / 2] + (i % 2);
        }
        return result;
    }

}