/*
    Problem Description:

        Given a positive integer num, write a function which returns True if num is a perfect square else False.
        Note: Do not use any built-in library function such as sqrt.

        Examples:

            Input: 16
            Output: true

            Input: 14
            Output: false
        
    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public boolean solve(int square) {
        if (square <= 0) {
            throw new IllegalArgumentException("square value " + square + " is non-positive");
        }
        return (square == 1) || (findSquaredNumber(square, 2, square / 2) > 0);
    }

    private int findSquaredNumber(int square, int minNumber, int maxNumber) {
        int result = -1;
        int currentNumber;
        long currentSquare;

        if (minNumber <= maxNumber) {
            currentNumber = minNumber + (maxNumber - minNumber) / 2;
            currentSquare = currentNumber * (long) currentNumber;
            if (currentSquare > square) {
                result = findSquaredNumber(square, minNumber, currentNumber - 1);
            } else if (currentSquare < square) {
                result = findSquaredNumber(square, currentNumber + 1, maxNumber);
            } else {
                result = currentNumber;
            }            
        }
        return result;
    }

/*

// less efficient: O(sqrt(n))

// idea:
// 1 = 1
// 4 = 1 + 3
// 9 = 1 + 3 + 5
// 16 = 1 + 3 + 5 + 7
// 25 = 1 + 3 + 5 + 7 + 9
// 36 = 1 + 3 + 5 + 7 + 9 + 11

public boolean isPerfectSquare(int num) {
     int i = 1;
     while (num > 0) {
         num -= i;
         i += 2;
     }
     return num == 0;
 }

 // Also, can be solved by Newton Method

*/

}