/*
    Problem Description:

        Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
        the nodes of the first two lists.

        Examples:

            Input: 1->2->4, 1->3->4
            Output: 1->1->2->3->4->4

    Solution Description:

    Time  complexity: O(n+m)
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

    public ListNode solve(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        ListNode node = new ListNode(0);
        ListNode preResult = node;

        while ((nodeA != null) && (nodeB != null)) {
            if (nodeA.val < nodeB.val) {
                node.next = nodeA;
                nodeA = nodeA.next;
            } else {
                node.next = nodeB;
                nodeB = nodeB.next;
            }
            node = node.next;
        }
        node.next = (nodeA != null) ? nodeA : nodeB;
        return preResult.next;
    }

}