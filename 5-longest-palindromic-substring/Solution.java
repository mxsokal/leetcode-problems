public final class Solution {

    public String solve(String string) {
        int[] indexes = new int[]{0, string.length() - 1};

        if (string.length() > 1) {
            indexes = solve(string.toCharArray());
        }
        return string.substring(indexes[0], indexes[1] + 1);
    }

    private int[] solve(char[] chars) {
        int[] result = new int[]{0, 0}; // first character by default
        int[] indexes;

        for (int i = 0; i < chars.length; i++) {
            indexes = findLongestPalindrome(chars, i);
            if ((indexes[1] - indexes[0]) > (result[1] - result[0])) {
                result = indexes;
            }
        }
        return result;
    }

    private int[] findLongestPalindrome(char[] chars, int centerIndex) {
        int[] result = new int[]{centerIndex, centerIndex}; // middle character by default
        boolean even = true;
        boolean odd = true;

        // expand around center [j ... centerIndex ... k]
        for (int j = centerIndex - 1, k = centerIndex + 1; (j >= 0) && (k <= chars.length); j--, k++) {
            even = (even) && (chars[j] == chars[k - 1]);
            odd = (odd) && (k < chars.length) && (chars[j] == chars[k]);
            if (odd) {
                result[0] = j;
                result[1] = k;
            } else if (even) {
                result[0] = j;
                result[1] = k - 1;
            } else {
                break;
            }
        }
        return result;
    }

}
