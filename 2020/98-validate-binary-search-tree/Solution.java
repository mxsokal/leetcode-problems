/*
    Problem Description:

        Given a binary tree, determine if it is a valid binary search tree (BST).
        Assume a BST is defined as follows:
            The left subtree of a node contains only nodes with keys less than the node's key.
            The right subtree of a node contains only nodes with keys greater than the node's key.
            Both the left and right subtrees must also be binary search trees.

        Examples:

            Input: [2,1,3]
            Output: true

            Input: [5,1,4,null,null,3,6]
            Output: false

    Time  complexity: O(n)
    Space complexity: O(n)

*/
public final class Solution {

    public class TreeNode {

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

    public boolean solve(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long minValue, long maxValue) {
        return (node == null)
          || ((node.val > minValue)
              && (node.val < maxValue)
              && isValid(node.left, minValue, node.val)
              && isValid(node.right, node.val, maxValue));
    }

}