import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
        the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in
        binary, which is 13.
        For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
        Return the sum of these numbers.
            The number of nodes in the tree is between 1 and 1000.
            node.val is 0 or 1.
            The answer will not exceed 2^31 - 1.

        Examples:

            Input: [1,0,1,0,1,0,1]
            Output: 4 + 5 + 6 + 7 = 22
        
    Time  complexity: O(N)
    Space complexity: O(height)
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

    public int solve(TreeNode root) {
        requireNonNull(root, "root is null");
        return traverse(root, 0, 0);
    }

    // DFS
    private int traverse(TreeNode node, int value, int sum) {
        int result = sum;

        if (node != null) {
            value <<= 1;
            value += node.val;
            result = traverse(node.left, value, result);
            result = traverse(node.right, value, result);
            if ((node.left == null) && (node.right == null)) {
                result += value;
            }
            value >>= 1;
        }
        return result;
    }

}