import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

        Symbol       Value
            I             1
            V             5
            X             10
            L             50
            C             100
            D             500
            M             1000

        For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII,
        which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
        Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
        IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
        The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
        is used:

        - I can be placed before V (5) and X (10) to make 4 and 9. 
        - X can be placed before L (50) and C (100) to make 40 and 90. 
        - C can be placed before D (500) and M (1000) to make 400 and 900.

        Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

        Example:

            Input: "III"
            Output: 3

            Input: "IV"
            Output: 4

            Input: "LVIII"
            Output: 58

            Input: "MCMXCIV"
            Output: 1994

    Solution Description:

    Time  complexity: O(x)
    Space complexity: O(1)
*/
public final class Solution {

    private static final int[] VALUES = new int[100];

    static {
        VALUES['I'] = 1;    // 73
        VALUES['V'] = 5;    // 86
        VALUES['X'] = 10;   // 88
        VALUES['L'] = 50;   // 76
        VALUES['C'] = 100;  // 67
        VALUES['D'] = 500;  // 68
        VALUES['M'] = 1000; // 77
    }

    public int solve(String string) {
        final int MAX_DUPLICATE_DIGIT_COUNT = 3;
        int digitDuplicateCount = 0;
        int value;
        int previousValue = Integer.MAX_VALUE;
        int result = 0;

        requireNonNull(string, "string is null");
        for (int i = 0; i < string.length(); i++) {
            value = getValue(string, i);
            if (value < previousValue) {
                // previous digit is from greater group of digits (even if it was combined, its value is still greater)
                digitDuplicateCount = 1;
            } else if (value == previousValue) {
                // digit is repeated
                if (digitDuplicateCount < MAX_DUPLICATE_DIGIT_COUNT) {
                    digitDuplicateCount++;
                } else {
                    throw new IllegalArgumentException("too many duplicate digits " + string.charAt(i));
                }
            } else {
                // digit is from greater group of digits than previous digit - must be combined with it
                // can be combined only if previous digit is not duplicated
                if ((digitDuplicateCount == 1) && (isCombinable(value, previousValue))) {
                    // restore the result and combine
                    result -= previousValue;
                    value = (value - previousValue);
                    // cannot be any duplicates after that
                    digitDuplicateCount = MAX_DUPLICATE_DIGIT_COUNT;
                } else {
                    throw new IllegalArgumentException("digit " + string.charAt(i) + " is too big for its position");
                }
            }
            result += value;
            previousValue = value;
        }
        return result;
    }

    private int getValue(String string, int index) {
        char character;
        int value;

        character = string.charAt(index);
        value = (character < VALUES.length) ? VALUES[character] : 0;
        if (value == 0) {
            throw new IllegalArgumentException("digit " + character + " is not supported");
        }
        return value;
    }

    // returns true if the value can be combined with the previous value: IV, IX, XL, XC, CD, CM
    private boolean isCombinable(int value, int previousValue) {
        return ((previousValue == 1)   && ((value == 5)   || (value == 10)))
            || ((previousValue == 10)  && ((value == 50)  || (value == 100)))
            || ((previousValue == 100) && ((value == 500) || (value == 1000)));
    }

}