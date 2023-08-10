import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;


/*
    Problem Description:

        Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
        Each person may dislike some other people, and they should not go into the same group. 
        Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
        Return true if and only if it is possible to split everyone into two groups in this way.

        Examples:

            Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
            Output: true

            Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
            Output: false

            Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
            Output: false

    Time  complexity: O(n + m)
    Space complexity: O(n + m)
*/
public final class Solution {

    private static enum Group {RED, BLUE}

    public boolean solve(int count, int[][] excludes) {
        Map<Integer, Group> groupByPerson = new HashMap<>(); // i.e. visited nodes
        Map<Integer, Set<Integer>> excludeGraph;
        boolean result = true;
        int person = 0;

        requireNonNull(excludes, "excludes is null");
        if (count < 0) {
            throw new IllegalArgumentException("count value " + count + " is negative");
        }
        excludeGraph = getGraph(excludes);
        // Assign a group to each person until a conflict is found, if any.
        while ((person < count) && (result)) {
            if (!groupByPerson.containsKey(person)) {
                // DFS
                result = traverse(excludeGraph, person, groupByPerson, Group.RED);
            }
            person++;
        }
        return result;
    }

    private Map<Integer, Set<Integer>> getGraph(int[][] edges) {
        Map<Integer, Set<Integer>> result = new HashMap<>();

        for (int[] edge : edges) {
            if ((edge == null) || (edge.length != 2)) {
                throw new IllegalArgumentException("invalid edge");
            }
            addEdge(result, edge[0], edge[1]);
            addEdge(result, edge[1], edge[0]);
        }
        return result;
    }

    private void addEdge(Map<Integer, Set<Integer>> graph, int beginNode, int endNode) {
        if (!graph.containsKey(beginNode)) {
            graph.put(beginNode, new HashSet<>());
        }
        graph.get(beginNode).add(endNode);
    }

    private boolean traverse(Map<Integer, Set<Integer>> graph, Integer node, Map<Integer, Group> groupByPerson, Group group) {
        Group nextGroup = (group == Group.RED) ? Group.BLUE : Group.RED;
        boolean result = true;

        groupByPerson.put(node, group);
        if (graph.containsKey(node)) {
            for (Integer adjacentNode : graph.get(node)) {
                if (!groupByPerson.containsKey(adjacentNode)) {
                    traverse(graph, adjacentNode, groupByPerson, nextGroup);
                } else if (groupByPerson.get(adjacentNode) != nextGroup) {
                    // conflict
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}