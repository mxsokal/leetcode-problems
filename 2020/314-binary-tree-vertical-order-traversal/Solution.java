import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

/*
    Problem Description:

        Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column
        by column).
        If two nodes are in the same row and column, the order should be from left to right.

    Examples:

        Input: [3,9,20,null,null,15,7]

                  3
                 /\
                /  \
                9  20
                   /\
                  /  \
                 15   7 

        Output: [
                  [9],
                  [3,15],
                  [20],
                  [7]
                ]

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

    public List<List<Integer>> solve(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> leftValues = new ArrayList<>();
        List<List<Integer>> rightValues = new ArrayList<>();

        if (root != null) {
            bfs(root, leftValues, rightValues);
        }
        Collections.reverse(leftValues);
        result.addAll(leftValues);
        result.addAll(rightValues);
        return result;
    }

    private void bfs(TreeNode root, List<List<Integer>> leftValues, List<List<Integer>> rightValues) {
        Map<TreeNode, Integer> positionByNode = new HashMap<>();
        Queue<TreeNode> nodes = new ArrayDeque<>();
        TreeNode node;
        int position;

        nodes.offer(root);
        positionByNode.put(root, 0);
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            position = positionByNode.get(node);
            addValue(node, position, leftValues, rightValues);
            if (node.left != null) {
                positionByNode.put(node.left, position - 1);
                nodes.offer(node.left);
            }
            if (node.right != null) {
                positionByNode.put(node.right, position + 1);
                nodes.offer(node.right);
            }
        }
    }

    private void addValue(TreeNode node, int position, List<List<Integer>> leftValues, List<List<Integer>> rightValues) {
        if (position >= 0) {
            ensureSize(rightValues, position);
            rightValues.get(position).add(node.val);
        } else {
            ensureSize(leftValues, -position - 1);
            leftValues.get(-position - 1).add(node.val);
        }
    }

    private void ensureSize(List<List<Integer>> values, int index) {
        while (values.size() <= index) {
            values.add(new ArrayList<>());
        }
    }

}