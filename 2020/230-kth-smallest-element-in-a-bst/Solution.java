import java.util.Deque;
import java.util.ArrayDeque;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
        You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
        What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
        How would you optimize the kthSmallest routine?

        Examples:

            Input: root = [3,1,4,null,2], k = 1
                     3
                    / \
                   1   4
                    \
                     2
            Output: 1

            Input: root = [5,3,6,2,4,null,null,1], k = 3
                           5
                          / \
                         3   6
                        / \
                       2   4
                      /
                    1
            Output: 3
        
    Time  complexity: O(height + k)
    Space complexity: O(height)
*/
public final class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int solve(TreeNode head, int k) {
        Deque<TreeNode> nodes = new ArrayDeque<>();
        TreeNode node = head;
        int remainingCount = k;
        int result;

        requireNonNull(head, "head is null");
        if (k <= 0) {
            throw new IllegalArgumentException("k value " + k + " is not positive");
        }
        do {
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
            node = nodes.pop();
            result = node.val;
            remainingCount--;
            node = node.right;
        } while ((remainingCount > 0) && ((node != null) || (!nodes.isEmpty()))); 
        return (remainingCount == 0) ? result : -1;
    }

}