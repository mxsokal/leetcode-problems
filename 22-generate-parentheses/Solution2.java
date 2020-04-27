import java.util.List;
import java.util.ArrayList;

/*
    Let's say we simply adding "(" and ")" without any rules. We can add all possilbe sequences recursively:

    Base case:
        current_string.length = max_length
            result.add(current_string)

    Recursive case:

        add(current_string + "(")
        add(current_string + ")")

    Now, just add the parentheses rules to not produce invalid sequences on each recursion 

    Time  complexity: O(4^n / sqrt(n))   //TODO analyze
    Space complexity: O(4^n / sqrt(n))

*/
public final class Solution2 {

    public List<String> solve(int n) {
        List<String> result = new ArrayList<>();

        if (n < 0) {
            throw new IllegalArgumentException("n value " + n + " is negative");
        }
        return doSolve(result, "", 0, 0, n);
    }

    private List<String> doSolve(List<String> result, String parentheses, int openCount, int closeCount, int n) {
        if (parentheses.length() < (2 * n)) {
            if (openCount < n) { // we can always add one more "(" if it doesn't exceed the string length
                doSolve(result, parentheses + "(", openCount + 1, closeCount, n);
            }
            if (closeCount < openCount) { // we can add ")" only if it closes some "("
                doSolve(result, parentheses + ")", openCount, closeCount + 1, n);
            }
        } else {
            result.add(parentheses);
        }
        return result;
    }

}