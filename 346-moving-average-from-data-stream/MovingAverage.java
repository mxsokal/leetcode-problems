/*
    Problem Description:

        Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

        Examples:

            MovingAverage m = new MovingAverage(3);
            m.next(1) = 1
            m.next(10) = (1 + 10) / 2
            m.next(3) = (1 + 10 + 3) / 3
            m.next(5) = (10 + 3 + 5) / 3
        
    Time  complexity: O(1)
    Space complexity: O(N)
*/
public final class MovingAverage {

    private final int[] values;
    private int sum;
    private int currentIndex;
    private boolean full;

    public MovingAverage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size value " + size + " is invalid");
        }
        this.values = new int[size];
        this.sum = 0;
        this.currentIndex = -1;
        this.full = false;
    }

    public double next(int value) {
        currentIndex++;
        if (currentIndex == values.length) {
            currentIndex = 0;
            full = true;
        }
        sum -= values[currentIndex];
        sum += value;
        values[currentIndex] = value;
        return ((double) sum) / ((full) ? values.length : (currentIndex + 1));
    }

}