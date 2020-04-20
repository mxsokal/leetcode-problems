import static java.lang.Character.isDigit;

/*
    Problem Description:

        Implement atoi which converts a string to an integer.
        The function first discards as many whitespace characters as necessary until the first non-whitespace character
        is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many
        numerical digits as possible, and interprets them as a numerical value.
        The string can contain additional characters after those that form the integral number, which are ignored and
        have no effect on the behavior of this function.
        If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence
        exists because either str is empty or it contains only whitespace characters, no conversion is performed.
        If no valid conversion could be performed, a zero value is returned.

        Note:
            Only the space character ' ' is considered as whitespace character.
            Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
            range: [−231,  231 − 1]. If the numerical value is out of the range of representable values,
            INT_MAX (231 − 1) or INT_MIN (−231) is returned.

        Example:

            Input: "42"
            Output: 42

            Input: "   -42"
            Output: -42

            Input: "4193 with words"
            Output: 4193

            Input: "words and 987"
            Output: 0

            Input: "-91283472332"
            Output: -2147483648

    Solution Description:

    Time  complexity: O(log(x))
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(String string) {
        char character;
        int result = 0;
        int index = 0;
        int sign = 0;

        index = getFirstNonSpaceIndex(string);
        if (index < string.length()) {
            sign = getExplicitSign(string, index);
            if (sign != 0) {
                index++;
            } else {
                sign = 1;
            }
            result = getInt(string, index, (sign > 0) ? Integer.MAX_VALUE : Math.abs(Integer.MIN_VALUE));
        }
        return sign * result;
    }

    private int getFirstNonSpaceIndex(String string) {
        final char SPACE_CHAR = ' ';
        int result = 0;

        while ((result < string.length()) && (string.charAt(result) == SPACE_CHAR)) {
            result++;
        }
        return result;
    }

    private int getExplicitSign(String string, int index) {
        final char PLUS_CHAR = '+';
        final char MINUS_CHAR = '-';
        char character;

        character = string.charAt(index);
        return switch (character) {
            case PLUS_CHAR -> 1;
            case MINUS_CHAR -> -1;
            default -> 0;
        };
    }

    private int getInt(String string, int startIndex, int overflowValue) {
        final int DEC_BASE = 10;
        final int MULT_THRESHOLD = Integer.MAX_VALUE / DEC_BASE;
        final int ZERO_ASCII_CODE = 48;
        char character;
        int result = 0;
        int index = startIndex;

        while ((index < string.length()) && (isDigit(character = string.charAt(index)))) {
            // if either next multiplication gives overflow or previous addition overflowed
            if ((result > MULT_THRESHOLD) || (result < 0)) {
                result = -1;
                break;
            }
            result = DEC_BASE * result + character - ZERO_ASCII_CODE;
            index++;
        }
        return (result >= 0) ? result : overflowValue;
    }

}