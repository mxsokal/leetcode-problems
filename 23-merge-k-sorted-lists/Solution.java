import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

        Examples:

            Input: [1->4->5, 1->3->4, 2->6]
            Output: 1->1->2->3->4->4->5->6

    Solution Description:

    Time  complexity: O(N*log(k)), N - total number of elements, k - number of arrays
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

    public ListNode solve(ListNode[] lists) {
        requireNonNull(lists, "lists is null");
        return solve(lists, 0, lists.length - 1);
    }

    private ListNode solve(ListNode[] lists, int beginIndex, int endIndex) {
        ListNode result = null;
        ListNode leftList;
        ListNode rightList;
        int divIndex;

        if (beginIndex < endIndex) {
            divIndex = (endIndex + beginIndex) / 2;
            leftList = solve(lists, beginIndex, divIndex);
            rightList = solve(lists, divIndex + 1, endIndex);
            result = merge(leftList, rightList);
        } else if (beginIndex == endIndex) {
            result = lists[beginIndex];
        }
        return result;
    }

    private ListNode merge(ListNode listA, ListNode listB) {
        ListNode nodeA = listA;
        ListNode nodeB = listB;
        ListNode node = new ListNode(-1);
        ListNode result = node;

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
        return result.next;
    }

}