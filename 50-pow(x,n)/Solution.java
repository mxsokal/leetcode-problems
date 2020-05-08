/*
    Problem Description:

        Implement pow(x, n), which calculates x raised to the power n (x^n).        
        -100.0 < x < 100.0
        n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]


        Examples:

            Input: 2.00000, 10
            Output: 1024.00000

            Input: 2.10000, 3
            Output: 9.26100

            Input: 2.00000, -2
            Output: 0.25000
        
    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public double solve(double x, int n) {
        int power = (n != Integer.MIN_VALUE) ? Math.abs(n) : Integer.MAX_VALUE; // work in positive space
        double result = 1.;
        double value = x;

        /*
            Consider the bit representation of N:
                N = 2^0*b0 + 2^1*b1 + 2^2*b2 + ... + 2^31*b31,  where bi - i-th bit.
            Consequently:
                x^N = x^(2^0*b0 + 2^1*b1 + 2^2*b2 + ... + 2^31*b31)
                    = x^(2^0*b0) * x^(2^1*b1) * x^(2^2*b2) + ... + x^(2^31*b31)
            In this representation, each subsequent component equals to the squared previous component if all bits bi==1:
                 x^(2^1) = (x^(2^0))^2                  // (x^n)^2 == x^2n
                 x^(2^2) = (x^(2^1))^2
            The idea is to subsequently calculate xi components as described above, pick the ones which are included in
            our representation of input N and multiply them.
        */
        while (power > 0) {
            if ((power & 1) == 1) { // If the current bit is set, include its component
                result *= value;
            }
            value *= value; // next component
            power >>= 1;  // next bit
        }
        if (n == Integer.MIN_VALUE) { // need to one extra mult for MIN_VALUE since abs(MAX_VALUE) < abs(MIN_VALUE)
            result *= x;
        }
        return (n >= 0) ? result : (1 / result);
    }

}