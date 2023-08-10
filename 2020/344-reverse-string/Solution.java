import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Write a function that reverses a string. The input string is given as an array of characters char[].
        Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
        extra memory.
        You may assume all the characters consist of printable ascii characters.

        Examples:

            Input: ["h","e","l","l","o"]
            Output: ["o","l","l","e","h"]

            Input: ["H","a","n","n","a","h"]
            Output: ["h","a","n","n","a","H"]
        
    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public void solve(char[] string) {
        char character;

        requireNonNull(string, "string is null");
        for (int i = 0; i < string.length / 2; i++) {
            character = string[string.length - i - 1];
            string[string.length - i - 1] = string[i];
            string[i] = character;
        }
    }

}