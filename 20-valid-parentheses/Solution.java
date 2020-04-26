import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

/*
    Problem Description:

        Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
        An input string is valid if:
        Open brackets must be closed by the same type of brackets.
        Open brackets must be closed in the correct order.
        Note that an empty string is also considered valid.

        Examples:

            Input: "()"
            Output: true

            Input: "()[]{}"
            Output: true

            Input: "(]"
            Output: false

    Solution Description:

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    private static final Map<Character, Character> PARENTHESES = new HashMap<>();

    static {
        PARENTHESES.put('(', ')');
        PARENTHESES.put('{', '}');
        PARENTHESES.put('[', ']');
    }

    public boolean solve(String expression) {
        Deque<Character> openBrackets = new ArrayDeque<>();
        boolean result = true;
        char character;
        Character bracket;

        for (int i = 0; i < expression.length(); i++) {
            character = expression.charAt(i);
            if (PARENTHESES.containsKey(character)) {
                // open bracket
                openBrackets.push(character);
            } else if (PARENTHESES.containsValue(character)) {
                // close bracket
                bracket = PARENTHESES.get(openBrackets.poll());
                if ((bracket == null) || (bracket != character)) {
                    result = false;
                    break;
                }
            }
        }
        return (result && openBrackets.isEmpty());
    }

}