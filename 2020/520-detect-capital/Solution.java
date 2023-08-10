import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a word, you need to judge whether the usage of capitals in it is right or not.
        We define the usage of capitals in a word to be right when one of the following cases holds:
            All letters in this word are capitals, like "USA".
            All letters in this word are not capitals, like "leetcode".
            Only the first letter in this word is capital, like "Google".
        Otherwise, we define that this word doesn't use capitals in a right way. 
        The input will be a non-empty word consisting of uppercase and lowercase latin letters.

        Examples:

            Input: "USA"
            Output: True

            Input: "FlaG"
            Output: False
        
    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public boolean solve(String word) {
        char character;
        boolean upper;
        boolean allUpper = true;
        boolean result = true;

        requireNonNull(word, "word is null");
        for (int i = 0; i < word.length(); i++) {
            character = word.charAt(i);
            upper = Character.isUpperCase(character);
            if (((i != 0) && (upper) && (!allUpper)) || ((i > 1) && (!upper) && (allUpper))) {
                result = false;
                break;
            }
            allUpper = allUpper && upper;
        }
        return result;
    }

}