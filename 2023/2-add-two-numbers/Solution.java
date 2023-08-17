import static java.util.Objects.requireNonNull;

class Solution {

    private static final int BASE = 10;

    static class ListNode {

        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }

    }

    // Time: O(max(M,N))
    // Space: O(1)
    static ListNode solve(ListNode numberA, ListNode numberB) {
        requireNonNull(numberA, "numberA is null");
        requireNonNull(numberB, "numberB is null");
        ListNode digitA = numberA;
        ListNode digitB = numberB;
        ListNode result = null;
        ListNode resultTail = null;
        int extra = 0;
        while ((digitA != null) || (digitB != null)) {
            int valueA = (digitA != null) ? digitA.val : 0;
            int valueB = (digitB != null) ? digitB.val : 0;
            int resultValue = valueA + valueB + extra;
            if (resultValue >= BASE) {
                resultValue = resultValue % BASE;
                extra = 1;
            } else {
                extra = 0;
            }
            if (result == null) {
                result = new ListNode(resultValue);
                resultTail = result;
            } else {
                resultTail.next = new ListNode(resultValue);
                resultTail = resultTail.next;
            };
            digitA = (digitA != null) ? digitA.next : null;
            digitB = (digitB != null) ? digitB.next : null;
        }
        if (extra != 0) {
            resultTail.next = new ListNode(extra);
            resultTail = resultTail.next;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("OK");
        ListNode numberA = create(9,9,9,9,9,9,9);
        print(numberA);
        ListNode numberB = create(9,9,9,9);
        print(numberB);
        ListNode result = solve(numberA, numberB);
        print(result);
    }

    static ListNode create(int... numbers) {
        ListNode result = new ListNode();
        for (int i = (numbers.length - 1); i >= 0; i--) {
            result.val = numbers[i];
            result = new ListNode(0, result);
        }
        return result.next;
    }

    static void print(ListNode numbers) {
        ListNode head = numbers;
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

}

