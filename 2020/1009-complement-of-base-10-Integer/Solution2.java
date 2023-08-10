public final class Solution2 {

    public int solve(int number) {
        int mask = number;

        // fill all the bits after the highest with 1
        // so the mask has all bits = 1 starting from highest in number
        mask |= (mask >> 1);  // highest and next to it are definately one after this step
        mask |= (mask >> 2);  // x2 ones
        mask |= (mask >> 4);
        mask |= (mask >> 8);
        mask |= (mask >> 16);
        return number ^ mask; // xor
    }

}