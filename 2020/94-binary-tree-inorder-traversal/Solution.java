import java.util.List;
import java.util.ArrayList;


/*
    Problem Description:

        Given a binary tree, return the inorder traversal of its nodes' values.
        Recursive solution is trivial, could you do it iteratively?

        Examples:

            Input: [1,null,2,3]
                   1
                    \
                     2
                    /
                   3
            Output: [1,3,2]

    Time  complexity: O(n)
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

    public List<Integer> solve(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode predecessor;
        TreeNode node;

        /*
            Use the Morris Method:
                1) if a node doesn't have a left child - add it to the result
                2) otherwise, rewire its predecessor to point to that node and proceed to the left child
                3) when the node is visited again (through its predecessor), restore the predecessor right link and 
                   add the node to the result

        */
        node = root;
        while (node != null) {
            if (node.left == null) {
                result.add(node.val);
                node = node.right;
            } else {
                predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    // first encounter - just rewire predecessor to point to node
                    predecessor.right = node;
                    node = node.left;
                } else {
                    // second encounter - add to result and restore predecessor
                    predecessor.right = null;
                    result.add(node.val);
                    node = node.right;
                }
            }
        }
        return result;
    }

}