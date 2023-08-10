/*
    Problem Description:

        The count-and-say sequence is the sequence of integers with the first five terms as following:
            1.     1
            2.     11
            3.     21
            4.     1211
            5.     111221
        1 is read off as "one 1" or 11.
        11 is read off as "two 1s" or 21.
        21 is read off as "one 2, then one 1" or 1211.
        Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so
        recursively, in other words from the previous member read off the digits, counting the number of digits in groups
        of the same digit.
        Note: Each term of the sequence of integers will be represented as a string.

    Time  complexity: O(2^n), under assumption that each step doubles the sequence
    Space complexity: O(2^(n-1)) ?
*/

public final class Solution {

    private static final int DIGIT_ASCII_OFFSET = 48;

    public String solve(int number) {
        StringBuilder builder = new StringBuilder();
        String sequence;
        String result = "";
        int count = 1;
        int value = -1;
        int previousValue = -1;

        if (number > 1) {
            sequence = solve(number - 1);
            for (int i = 0; i < sequence.length(); i++) {
                value = sequence.charAt(i) - DIGIT_ASCII_OFFSET;
                if (i > 0) {
                    if (previousValue == value) {
                        count++;
                    } else {
                        builder.append(count).append(previousValue);
                        count = 1;
                    }
                }
                previousValue = value;
            }
            result = builder.append(count).append(value).toString();
        } else if (number == 1) {
            result = "1";
        } else {
            throw new IllegalArgumentException("number value " + number + " is non-positive");
        }
        return result;
    }

}