import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
        Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
        We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
        Return true if and only if the nodes corresponding to the values x and y are cousins.        
        The number of nodes in the tree will be between 2 and 100.
        Each node has a unique integer value from 1 to 100.

        Examples:

            Input: root = [1,2,3,4], x = 4, y = 3
            Output: false

            Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
            Output: true

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    public static final class TreeNode {

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

    public boolean solve(TreeNode rootNode, int x, int y) {
        Entry<Integer, TreeNode> levelNodePair;
        TreeNode node;
        int level;
        int value;
        boolean result = false;

        requireNonNull(rootNode, "rootNode is null");
        levelNodePair = findParentNode(rootNode, 1, Integer.MAX_VALUE, x, y);
        if (levelNodePair != null) {
            node = levelNodePair.getValue();
            level = levelNodePair.getKey();
            value = ((node.left != null) && (node.left.val == y)) || ((node.right != null) && (node.right.val == y)) ? x : y;
            levelNodePair = findParentNode(rootNode, 1, level, value);
            result = (levelNodePair != null) && (levelNodePair.getKey() == level) && (levelNodePair.getValue().val != node.val);
        }
        return result;
    }

    private Entry<Integer, TreeNode> findParentNode(TreeNode rootNode, int rootLevel, int maxLevel, int... values) {
        Entry<Integer, TreeNode> result = null;

        if (rootNode != null) {
            if ((hasValue(rootNode.left, values)) || (hasValue(rootNode.right, values))) {
                result = new SimpleEntry<>(rootLevel, rootNode);
            } else if (maxLevel > rootLevel) {
                result = findParentNode(rootNode.left, rootLevel + 1, maxLevel, values);
                if (result == null) {
                    result = findParentNode(rootNode.right, rootLevel + 1, maxLevel, values);
                }
            }
        }
        return result;
    }

    private boolean hasValue(TreeNode node, int[] values) {
        boolean result = false;

        if (node != null) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == node.val) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}