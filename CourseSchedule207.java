
 There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++){
            List<Integer> list = map.getOrDefault(prerequisites[i][0], new ArrayList<Integer>());
            list.add(prerequisites[i][1]);
            map.put(prerequisites[i][0], list);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++){
            if (!dfs(map, i, visited)) return false;
        }
        return true;
    }
    private boolean dfs(Map<Integer, List<Integer>> map, int id, int[] visited){
        if (visited[id] == 2) return true;
        if (visited[id] == 1) return false;
        visited[id] = 1;
        List<Integer> list = map.get(id);
        if (list != null){
            for (int i = 0; i < list.size(); i++){
                if (!dfs(map, list.get(i), visited)){
                return false;
                }
            }
        }
        visited[id] = 2;
        return true;
    }
    
}
