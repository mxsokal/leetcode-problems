import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;
import static java.util.Collections.emptyList;
/*
    Problem Description:

        There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
        expressed as a pair: [0,1]
        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
            The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
            how a graph is represented.
            You may assume that there are no duplicate edges in the input prerequisites.
            1 <= numCourses <= 10^5


        Examples:

            Input: numCourses = 2, prerequisites = [[1,0]]
            Output: true

            Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
            Output: false

    Time  complexity: O(n+m)
    Space complexity: O(n+m)
*/
public final class Solution {

    private static enum Mode {BLOCKED, UNBLOCKED}

    public boolean solve(int count, int[][] prerequisites) {
        Map<Integer, Mode> visitedNodes = new HashMap<>();
        Map<Integer, List<Integer>> graph;
        boolean result = true;

        requireNonNull(prerequisites, "prerequisites is null");
        if (count < 0) {
            throw new IllegalArgumentException("count value " + count + " is negative");
        }
        graph = getGraph(prerequisites);
        // Basically, the problem is to find out whether we can topologically sort the nodes or not.
        // So, we just need to find out whether the graph has cycles.
        for (int i = 0; i < count; i++) {
            if (!traverse(graph, visitedNodes,i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        for(int[] prerequisite : prerequisites) {
            if (!result.containsKey(prerequisite[0])) {
                result.put(prerequisite[0], new ArrayList<>());
            }
            result.get(prerequisite[0]).add(prerequisite[1]);
        }
        return result;
    }

    private boolean traverse(Map<Integer, List<Integer>> graph, Map<Integer, Mode> visitedNodes, int node) {
        boolean acyclic = true;

        visitedNodes.put(node, Mode.BLOCKED);
        for (Integer adjacentNode : graph.getOrDefault(node, emptyList())) {
            acyclic = (visitedNodes.containsKey(adjacentNode))
                ? (visitedNodes.get(adjacentNode) == Mode.UNBLOCKED)
                : traverse(graph, visitedNodes, adjacentNode);
            if (!acyclic) {
                break;
            }
        }
        visitedNodes.put(node, Mode.UNBLOCKED);
        return acyclic;
    }

}