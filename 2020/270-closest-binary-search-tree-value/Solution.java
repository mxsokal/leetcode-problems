import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
            Given target value is a floating point.
            You are guaranteed to have only one unique value in the BST that is closest to the target.

    Examples:

            Input: root = [4,2,5,1,3], target = 3.714286
            Output: 4
        
    Time  complexity: O(height)
    Space complexity: O(1)
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

    public int solve(TreeNode root, double target) {

        requireNonNull(root, "root is null");
        return search(root, target).val;
    }

    private TreeNode search(TreeNode node, double target) {
        TreeNode subResult = null;

        if ((Double.compare(target, node.val) < 0) && (node.left != null)) {
            subResult = search(node.left, target);
        } else if ((Double.compare(target, node.val) > 0) && (node.right != null)) {
            subResult = search(node.right, target);
        }
        return (subResult == null)
                ? node
                : ((Double.compare(Math.abs(node.val - target), Math.abs(subResult.val - target)) < 0) ? node : subResult);
    }

}