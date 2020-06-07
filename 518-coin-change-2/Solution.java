/*
    Problem Description:

        You are given coins of different denominations and a total amount of money. Write a function to compute the number
        of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
            0 <= amount <= 5000
            1 <= coin <= 5000
            the number of coins is less than 500
            the answer is guaranteed to fit into signed 32-bit integer

        Examples:

            Input: amount = 5, coins = [1, 2, 5]
            Output: 4

            Input: amount = 3, coins = [2]
            Output: 0
        
    Time  complexity: O(n*amount)
    Space complexity: O(n*amount), can be optimized to O(amount)
*/
public final class Solution {

    public int solve(int amount, int[] coins) {
        int[][] counts = new int[coins.length + 1][amount + 1];
        int coin;

        counts[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            coin = coins[i - 1];
            counts[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                // amount j combined using previous (i-1) coins and remainder amount (j-coin) combined using all i coins
                counts[i][j] = counts[i - 1][j] + ((j >= coin) ? counts[i][j - coin] : 0);
            }
        }
        return counts[coins.length][amount];
    }

}