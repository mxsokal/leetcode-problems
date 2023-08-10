/*
    Problem Description:

        Given the head of a linked list, return the list after sorting it in ascending order.
        Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
            The number of nodes in the list is in the range [0, 5 * 10^4].
            -10^5 <= Node.val <= 10^5

        Examples:

            Input: head = [4,2,1,3]
            Output: [1,2,3,4]

    Time  complexity: O(n*log(n))
    Space complexity: O(1), O(log(n)) including recursion stack
*/
//-----------------------------------------------LEETCODE CODE----------------------------------------------------------
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
}
//----------------------------------------------------------------------------------------------------------------------
public final class Solution {

    // Merge Sort
    public ListNode solve(ListNode head) {
        ListNode result = head;
        ListNode middle;
        ListNode left;
        ListNode right;

        if ((head != null) && (head.next != null)) {
            middle = truncHalf(head);
            left = solve(head);
            right = solve(middle);
            result = merge(left, right);
        }
        return result;
    }

    private ListNode truncHalf(ListNode head) {
        ListNode preMiddle = new ListNode();
        ListNode tail = head;
        ListNode result;

        preMiddle.next = head;
        while ((tail != null) && (tail.next != null)) {
            preMiddle = preMiddle.next;
            tail = tail.next.next;
        }
        result = preMiddle.next;
        preMiddle.next = null;
        return result;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode tail = new ListNode();
        ListNode preResult = tail;
        ListNode leftTail = left;
        ListNode rightTail = right;

        while (leftTail != null) {
            while ((rightTail != null) && (rightTail.val <= leftTail.val)) {
                tail.next = rightTail;
                tail = rightTail;
                rightTail = rightTail.next;
            }
            tail.next = leftTail;
            tail = leftTail;
            leftTail = leftTail.next;
        }
        while (rightTail != null) {
            tail.next = rightTail;
            tail = rightTail;
            rightTail = rightTail.next;
        }
        tail.next = null;
        return preResult.next;
    }

}