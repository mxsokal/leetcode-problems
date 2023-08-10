import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.Queue;

/*
    Problem Description:

        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

        Examples:

            Input: "23"
            Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

    Solution Description:

    Time  complexity: O(3^n * 4^m) where n - the number of digits mapping to 3 chars, m - to 4
    Space complexity: O(3^n * 4^m)
*/
public final class Solution {

    private static final int BASE = 10;
    private static final int[][] LETTERS = new int[][]{
        {},
        {},
        {'a','b','c'},
        {'d','e','f'},
        {'g','h','i'},
        {'j','k','l'},
        {'m','n','o'},
        {'p','q','r','s'},
        {'t','u','v'},
        {'w','x','y','z'}
    };

    public List<String> solve(String digits) {
        LinkedList<String> result = new LinkedList<>(List.of(""));
        String element;
        int digit;
        char letter;
        int count;

        requireNonNull(digits, "digits is null");
        for (int i = 0; i < digits.length(); i++) {
            digit = Character.digit(digits.charAt(i), BASE);
            count = result.size();
            for (int j = 0; j < count; j++) {
                element = result.poll();
                for (int k = 0; k < LETTERS[digit].length; k++) {
                    letter = (char) LETTERS[digit][k];
                    result.offer(element + letter);
                }
            }
        }
        return (digits.isEmpty()) ? Collections.emptyList() : result;
    }

/*
    Simpler:

    public List<String> solve1(String digits) {
        List<String> result = new ArrayList<>(List.of(""));
        List<String> subResult;
        char character;
        int digit;
        char letter;

        requireNonNull(digits, "digits is null");
        for (int i = 0; i < digits.length(); i++) {
            digit = Character.digit(digits.charAt(i), BASE);
            subResult = new ArrayList<>();
            for (int j = 0; j < LETTERS[digit].length; j++) {
                letter = (char) LETTERS[digit][j];
                for (String element : result) {
                    subResult.add(element + letter);
                }
            }
            result = subResult;
        }
        return (digits.isEmpty()) ? Collections.emptyList() : result;
    }
*/
}