import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

/*
    Problem Description:

        Given a binary tree, return the vertical order traversal of its nodes values.
        For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and
        (X+1, Y-1).
        Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we
        report the values of the nodes in order from top to bottom (decreasing Y coordinates).
        If two nodes have the same position, then the value of the node that is reported first is the value that is
        smaller.
        Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.        
            The tree will have between 1 and 1000 nodes.
            Each node's value will be between 0 and 1000.


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

    Time  complexity: O(n*log(n))
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

     private static final class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public List<List<Integer>> solve(TreeNode root) {
        List<List<Integer>> result;
        List<List<TreeNode>> leftNodes = new ArrayList<>();
        List<List<TreeNode>> rightNodes = new ArrayList<>();
        Map<TreeNode, Point> pointByNode = new HashMap<>();

        if (root != null) {
            bfs(root, leftNodes, rightNodes, pointByNode);
        }
        Collections.reverse(leftNodes);
        // map nodes to values
        result = Stream.concat(leftNodes.stream(), rightNodes.stream())
            .map(l -> l.stream()
                        // sort by y, then by value
                        .sorted(Comparator.<TreeNode>comparingInt(n -> pointByNode.get(n).y)
                                        .reversed()
                                        .thenComparing(Comparator.comparingInt(n -> n.val)))
                        .map(e -> e.val)
                        .collect(toList()))
            .collect(toList());
        return result;
    }

    private void bfs(TreeNode root, List<List<TreeNode>> leftNodes, List<List<TreeNode>> rightNodes,
                     Map<TreeNode, Point> pointByNode) {
        Queue<TreeNode> nodes = new ArrayDeque<>();
        TreeNode node;
        Point point;

        nodes.offer(root);
        pointByNode.put(root, new Point(0, 0));
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            point = pointByNode.get(node);
            addNode(node, point, leftNodes, rightNodes);
            if (node.left != null) {
                pointByNode.put(node.left, new Point(point.x - 1, point.y - 1));
                nodes.offer(node.left);
            }
            if (node.right != null) {
                pointByNode.put(node.right, new Point(point.x + 1, point.y - 1));
                nodes.offer(node.right);
            }
        }
    }

    private void addNode(TreeNode node, Point point, List<List<TreeNode>> leftNodes, List<List<TreeNode>> rightNodes) {
        if (point.x >= 0) {
            ensureSize(rightNodes, point.x);
            rightNodes.get(point.x).add(node);
        } else {
            ensureSize(leftNodes, -point.x - 1);
            leftNodes.get(-point.x - 1).add(node);
        }
    }

    private void ensureSize(List<List<TreeNode>> nodes, int index) {
        while (nodes.size() <= index) {
            nodes.add(new ArrayList<>());
        }
    }

}