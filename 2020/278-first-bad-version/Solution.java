/*
    Problem Description:

        You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest
        version of your product fails the quality check. Since each version is developed based on the previous version,
        all the versions after a bad version are also bad.
        Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all
        the following ones to be bad.
        You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function
        to find the first bad version. You should minimize the number of calls to the API.
        
    Time  complexity: O(log(n))
    Space complexity: O(1)
*/
public final class Solution {

    public int solve(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n value " + n + " is not positive");
        }
        return solve(1, n);
    }    

    public int solve(int minVersion, int maxVersion) {
        int result = -1;
        int divVersion;

        if (maxVersion > minVersion) {
            divVersion = minVersion + (maxVersion - minVersion) / 2;
            result = isBadVersion(divVersion) ? solve(minVersion, divVersion) : solve(divVersion + 1, maxVersion);
        } else if (isBadVersion(minVersion)) {
            result = minVersion;
        }
        return result;
    }

    private boolean isBadVersion(int version) {
        return version >= 1702766719;
    }

}