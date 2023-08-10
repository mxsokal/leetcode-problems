import java.util.List;
import java.util.ArrayList;

/*
    Problem Description:

        Given two binary search trees root1 and root2.
        Return a list containing all the integers from both trees sorted in ascending order.
        Each tree has at most 5000 nodes.
        Each node's value is between [-10^5, 10^5].

        Examples:

            Input: root1 = [2,1,4], root2 = [1,0,3]
            Output: [0,1,1,2,3,4]

    Time  complexity: O(n)
    Space complexity: O(n)
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

    public List<Integer> solve(TreeNode root1, TreeNode root2) {
        List<Integer> values1 = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();

        traverse(root1, values1);
        traverse(root2, values2);
        return merge(values1, values2);
    }

    private void traverse(TreeNode node, List<Integer> values) {
        if (node != null) {
            traverse(node.left, values);
            values.add(node.val);
            traverse(node.right, values);
        }
    }

    private List<Integer> merge(List<Integer> values1, List<Integer> values2) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < values1.size()) {
            while ((j < values2.size()) && (values2.get(j) <= values1.get(i))) {
                result.add(values2.get(j));
                j++;
            }
            result.add(values1.get(i));
            i++;
        }
        while (j < values2.size()) {
            result.add(values2.get(j));
            j++;
        }
        return result;
    }

}