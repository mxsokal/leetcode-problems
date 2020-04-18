import static java.lang.Character.isDigit;

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