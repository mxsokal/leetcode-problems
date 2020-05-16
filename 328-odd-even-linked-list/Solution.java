/*
    Problem Description:

        Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
        talking about the node number and not the value in the nodes.
        You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
            The relative order inside both the even and odd groups should remain as it was in the input.
            The first node is considered odd, the second node even and so on ...

        Examples:

            Input: 1->2->3->4->5->NULL
            Output: 1->3->5->2->4->NULL

            Input: 2->1->3->5->6->4->7->NULL
            Output: 2->3->6->7->1->5->4->NULL
        
    Time  complexity: O(n)
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

    public ListNode solve(ListNode head) {
        ListNode node = head;
        ListNode evenNode = new ListNode(0);
        ListNode oddNode = new ListNode(0);
        ListNode evenHead = evenNode;
        boolean even = false;

        while (node != null) {
            if (even) {
                evenNode.next = node;
                evenNode = node;
            } else {
                oddNode.next = node;
                oddNode = node;
            }
            node = node.next;
            even = !even;
        }
        oddNode.next = evenHead.next;
        evenNode.next = null;
        return head;
    }

}