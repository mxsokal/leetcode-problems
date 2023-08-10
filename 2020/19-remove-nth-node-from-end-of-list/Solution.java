import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a linked list, remove the n-th node from the end of list and return its head.

        Examples:

            Input: [1,2,3,4,5] , 2
            Output: [1,2,3,5]

    Solution Description:

    Time  complexity: O(N)
    Space complexity: O(1)
*/
public final class Solution {

    public static final class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode solve(ListNode head, int n) {
        ListNode preTargetNode = head;
        ListNode node = head;
        int count = 0;

        checkArgs(head, n);
        if (n > 0) {
            while (node != null) {
                count++;
                if ((count > n) && (node.next != null)) {
                    preTargetNode = preTargetNode.next;
                }
                node = node.next;
            }
            if (n < count) {
                node = head;
                preTargetNode.next = preTargetNode.next.next;
            } else if (n == count) {
                node = head.next;
            } else {
                throw new IllegalArgumentException("n value " + n + " is less than list size " + count);
            }
        }
        return node;
    }

    private void checkArgs(ListNode head, int n) {
        requireNonNull(head, "head is null");
        if (n < 0) {
            throw new IllegalArgumentException("n value " + n + " is negative");
        }
    }

}