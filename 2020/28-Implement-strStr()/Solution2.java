import static java.util.Objects.requireNonNull;
import java.util.Arrays;


// TODO improve
// Rabin Karp
public final class Solution2 {

    private static final int BASE = 100; // to avoid collisions must be greater than max value of any character
    private static final int CHAR_VALUE_OFFSET = 32; // we don't support unprintable characters ASCII 0-31
    private static final long MODULUS = (long) Math.pow(2, 31) - 1;  // do mod for each hash to elminate overflow
                                                                // however, increases collisions
                                                                // better to pick a prime number

    public int solve(String string, String substring) {
        int result = -1;

        requireNonNull(string, "string is null");
        requireNonNull(substring, "substring is null");
        if (substring.isEmpty()) {
            result = 0;
        } else if (substring.length() <= string.length()) {
            result = solve(string.toCharArray(), substring.toCharArray());
        }
        return result;
    }

    private int solve(char[] string, char[] substring) {
        long substringHash;
        long hash = 0;
        int index = 0;
        int result = -1;

        substringHash = getSubstringHash(substring, 0, substring.length);
        while ((result < 0) && ((index + substring.length) <= string.length)) {
            hash = (index == 0) ? getSubstringHash(string, index, substring.length)
                                : getNextHash(hash, string[index - 1], string[index + substring.length - 1], substring.length);
            if ((hash == substringHash) && (Arrays.compare(substring, 0, substring.length - 1, string, index,  index + substring.length - 1) == 0)) {
                result = index;
            }
            index++;
        }
        return result;
    }

    private long getSubstringHash(char[] string, int index, int length) {
        long result = 0;

        for (int i = index; i < length; i++) {
            result = (result + ((long) Math.pow(BASE, i - index) * (string[i] - CHAR_VALUE_OFFSET))) % MODULUS;  //TODO Math.pow may overflow too
        }
        return result;
    }

    private long getNextHash(long hash, char prevFirstCharacter, char nextLastCharacter, int length) {
        return ( ((hash - (prevFirstCharacter - CHAR_VALUE_OFFSET)) / BASE)
                + ((long) Math.pow(BASE, length - 1) * (nextLastCharacter - CHAR_VALUE_OFFSET))) % MODULUS;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();

        System.out.println(s.solve(args[0], args[1]));
    }

}