import static java.util.Objects.requireNonNull;

/*
    Problem Description:


        You are given two non-empty linked lists representing two non-negative integers.
        The digits are stored in reverse order and each of their nodes contain a single digit.
        Add the two numbers and return it as a linked list.

        You may assume the two numbers do not contain any leading zero, except the number 0 itself.

        Example:

            Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
            Output: 7 -> 0 -> 8
            Explanation: 342 + 465 = 807.


    Solution Description: just add numbers the same way as adding on paper (carrying the overflow)

    Time  complexity: O(max(m.n))
    Space complexity: O(max(m.n))
*/
public final class Solution {

    //--------------------------------------------LEETCODE-------------------------------------------------------------
    public static class ListNode {

        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

    }
    //-----------------------------------------------------------------------------------------------------------------

    private static final int MIN_DIGIT = 0;
    private static final int MAX_DIGIT = 9;
    private static final int BASE = 10;

    public ListNode solve(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode resultHead = result;
        ListNode head1 = list1;
        ListNode head2 = list2;
        int sum = 0;

        requireNonNull(list1, "list1 is null");
        requireNonNull(list2, "list2 is null");
        do {
            sum = sumDigits(head1, head2, sum);
            resultHead = appendNode(resultHead, sum);
            head1 = (head1 != null) ? head1.next : null;
            head2 = (head2 != null) ? head2.next : null;
        } while ((head1 != null) || (head2 != null));
        if (sum >= BASE) {
            resultHead = appendNode(resultHead, 1);
        }
        return (result.next != null) ? result.next : result;
    }

    private ListNode appendNode(ListNode head, int digitSum) {
        int digit;

        digit = digitSum % BASE;
        head.next = new ListNode(digit);
        return head.next;
    }

    private int sumDigits(ListNode node1, ListNode node2, int prevSum) {
        int digit1;
        int digit2;
        int carry;

        digit1 = getDigit(node1);
        digit2 = getDigit(node2);
        carry = (prevSum < BASE) ? 0 : 1;
        return digit1 + digit2 + carry;
    }

    private int getDigit(ListNode node) {
        int digit = 0;

        if (node != null) {
            digit = node.val;
            if ((digit < MIN_DIGIT) || (digit > MAX_DIGIT)) {
                throw new IllegalArgumentException("list value " + digit + " is out of range");
            }
        }
        return digit;
    }

}
