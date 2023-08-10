import java.util.Comparator;
import java.util.Arrays;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers
        (h, k), where h is the height of the person and k is the number of people in front of this person who have a
        height greater than or equal to h. Write an algorithm to reconstruct the queue.
            The number of people is less than 1,100.

        Examples:

            Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
            Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

    Time  complexity: O(n^2)    // can be improved
    Space complexity: O(n), including result
*/
public final class Solution {

    public int[][] solve(int[][] queue) {
        int[][] result;
        int[] element;
        int index;

        requireNonNull(queue, "queue is null");
        result = Arrays.copyOf(queue, queue.length);
        // sort [0] desc [1] asc
        Arrays.sort(result, Comparator.<int[]>comparingInt(e -> e[0]).reversed().thenComparingInt(e -> e[1]));
        // basically, insertion sort to put each element at element[i][1]
        for (int i = 0; i < result.length; i++) {
            element = result[i];
            index = i;
            while (index > element[1]) {
                result[index] = result[--index];
                result[index] = element;
            }
        }
        return result;
    }

}