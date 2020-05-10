import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly
        the town judge.
        If the town judge exists, then:
            - The town judge trusts nobody.
            - Everybody (except for the town judge) trusts the town judge.
            - There is exactly one person that satisfies properties 1 and 2.
        You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts
        the person labelled b.
        If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.        
            1 <= N <= 1000
            trust.length <= 10000
            trust[i] are all different
            trust[i][0] != trust[i][1]
            1 <= trust[i][0], trust[i][1] <= N

        Examples:

            Input: N = 2, trust = [[1,2]]
            Output: 2

            Input: N = 3, trust = [[1,3],[2,3]]
            Output: 3

            Input: N = 3, trust = [[1,3],[2,3],[3,1]]
            Output: -1

            Input: N = 3, trust = [[1,2],[2,3]]
            Output: -1

            Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
            Output: 3

    Time  complexity: O(E), E-trustPair.length
    Space complexity: O(N)
*/
public final class Solution {

    public int solve(int count, int[][] trustPairs) {
        int result = -1;

        requireNonNull(trustPairs, "trustPairs is null");
        if (count < 0) {
            throw new IllegalArgumentException("count value " + count + " is negative");
        }
        return (trustPairs.length >= (count - 1)) ? doSolve(count, trustPairs) : -1;
    }

    private int doSolve(int count, int[][] trustPairs) {
        int result = -1;
        int[] trustBalanceByPerson;

        trustBalanceByPerson = getTrustBalanceByPerson(count, trustPairs);
        return getJudge(trustBalanceByPerson);
    }

    private int[] getTrustBalanceByPerson(int count, int[][] trustPairs) {
        int[] result = new int[count];

        for (int i = 0; i < trustPairs.length; i++) {
            if ((trustPairs == null)
                || (trustPairs[i].length != 2)
                || (trustPairs[i][0] < 1)
                || (trustPairs[i][0] > count)
                || (trustPairs[i][1] < 1)
                || (trustPairs[i][1] > count)) {
                throw new IllegalArgumentException("trustPairs element " + i + " is invalid");
            }
            result[trustPairs[i][1] - 1]++;
            result[trustPairs[i][0] - 1]--; 
        }
        return result;
    }

    private int getJudge(int[] trustBalanceByPerson) {
        int result = -1;

        for (int i = 0; i < trustBalanceByPerson.length; i++) {
            if (trustBalanceByPerson[i] == trustBalanceByPerson.length - 1) {
                result = i + 1;
                break;
            }
        }
        return result;
    }

}