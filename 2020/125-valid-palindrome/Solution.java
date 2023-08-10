import static java.lang.Character.toLowerCase;
import static java.lang.Character.isLetterOrDigit;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
        Note: For the purpose of this problem, we define empty string as valid palindrome.

        Examples:

            Input: "A man, a plan, a canal: Panama"
            Output: true

            Input: "race a car"
            Output: false

    Time  complexity: O(n)
    Space complexity: O(1)

*/
public final class Solution {

    public boolean solve(String string) {
        boolean result = true;
        char rightChar;
        char leftChar;
        int i = 0;
        int j;
 
        requireNonNull(string, "string is null");
        j = string.length() - 1;
        while (i < j) {
            leftChar = toLowerCase(string.charAt(i));
            rightChar = toLowerCase(string.charAt(j));
            if (!isLetterOrDigit(leftChar)) {
                i++;
                continue;
            }
            if (!isLetterOrDigit(rightChar)) {
                j--;
                continue;
            }
            if (leftChar != rightChar) {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;
    }

}