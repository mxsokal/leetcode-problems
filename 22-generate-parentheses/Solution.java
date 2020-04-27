import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/*
    Problem Description:

        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Examples:

        Input: n = 3
        Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]

    Solution Description:

        f(0) = {""}
        f(1) = {"()"}
        f(2) = {"()()", "(())"}
        f(3) = {"()()()", "()(())", "(())()", "(()())", "((()))"}

        Let's try to express f(n) in terms of f(n-1), f(n-2), ... f(0)

        n=i+j+1

        f(i)=A
        f(j)=B

        for each pair of subresults A[k] and B[m] we can obtain a result by adding n's parentheses in the valid places

        f(n)={
            ()A[k]B[m], // is already included as (A[k])B[m] in i=0,j=n-1 case
            A[k]B[m](), // is already included as A[k](B[m]) in i=n-1,j=0 case
            A[k]()B[m], // is already included as (A[k])B[m] in i=n-1,j=0 case
            (A[k]B[m]), // is already included as (A[k])B[m] in i=n-1,j=0 case
            A[k](B[m]), // is already included as (A[k])B[m] in i=i-1,j=j+1 case
            (A[k])B[m]
        }

        Basically, we can use either A[k](B[m]) or (A[k])B[m] to exclude duplicates.

    Time  complexity: O(4^n / sqrt(n))   //TODO analyze
    Space complexity: O(4^n / sqrt(n))
*/
public final class Solution {

    public List<String> solve(int n) {
        List<String> result = new ArrayList<>();

        if (n == 0) {
            result.add("");
        } else if (n > 0) {
            for (int i = 0; i < n; i++) {
                for (String s1 : solve(i)) {
                    for (String s2 : solve(n - i - 1)) {
                        result.add("(" + s1 + ")" + s2);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("n value " + n + " is negative");
        }
        return result;
    }

/*
    // version with cache:

    public List<String> solve(int n) {
        List<List<String>> results = new ArrayList<>();

        if (n < 0) {
            throw new IllegalArgumentException("n value " + n + " is negative");
        }
        for (int i = 0; i <= n; i++) {
            results.add(new ArrayList<>());
        }
        return doSolve(n, results);
    }

    private List<String> doSolve(int n, List<List<String>> results) {
        List<String> result = results.get(n);

        if (result.isEmpty()) {
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    for (String s1 : doSolve(i, results)) {
                        for (String s2 : doSolve(n - i - 1, results)) {
                            result.add("(" + s1 + ")" + s2);
                        }
                    }
                }
            } else {
                result.add("");
            }
        }
        return result;
    }


*/

}