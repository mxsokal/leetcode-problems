import static java.util.Objects.requireNonNull;

// Boyer-Moore Voting Algorithm
// Time  complexity: O(n)
// Space complexity: O(1)
public final class Solution2 {

    public int solve(int[] numbers) {
        int result = -1;
        int count = 0;

        requireNonNull(numbers, "numbers is null");
        // Let's assume that an element pairs off with another element which has a different value.
        // Obviously, there is no way to pair off the elements which have the majority value.
        // So, once we've paired off some elements (count == 0), the majority value is still the majority of the remainder
        // and we can discard the suffix and continue to search.
        for (int i = 0; i < numbers.length; i++) {
            if (count == 0) {
                result = numbers[i];
                count = 1;
            } else {
                count = (result == numbers[i]) ? count + 1 : count - 1;
            }
        }
        // check that it is really the majority element
        count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (result == numbers[i]) {
                count++;
            }
        }
        return (count > (numbers.length / 2)) ? result : -1;
    }

}