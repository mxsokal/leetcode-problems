import java.util.Deque;
import java.util.ArrayDeque;

/*
    Problem Description:

        Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's
        price for the current day.
        The span of the stock's price today is defined as the maximum number of consecutive days (starting from today
        and going backwards) for which the price of the stock was less than or equal to today's price.
        For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock
        spans would be [1, 1, 1, 2, 1, 4, 6].
            Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
            There will be at most 10000 calls to StockSpanner.next per test case.
            There will be at most 150000 calls to StockSpanner.next across all test cases.
            The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.

        Examples:
            Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
            Output: [null,1,1,1,2,1,4,6]

    Time  complexity: O(n)
    Space complexity: O(n)
*/
public class StockSpanner {

    private final Deque<int[]> lastPriceCounts;

    public StockSpanner() {
        this.lastPriceCounts = new ArrayDeque<>();
    }

    public int next(int price) {
        int count = 1;

        while ((!lastPriceCounts.isEmpty()) && (lastPriceCounts.peek()[0] <= price)) {
            count += lastPriceCounts.pop()[1];
        }
        lastPriceCounts.push(new int[]{price, count});
        return count;
    }

}