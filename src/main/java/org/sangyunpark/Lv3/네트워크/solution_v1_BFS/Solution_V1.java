package org.sangyunpark.Lv3.네트워크.solution_v1_BFS;

import java.util.*;

public class Solution_V1 {
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                answer++;
                bfs(i, computers);
            }
        }

        return answer;
    }

    private void bfs(int cur, int[][] computers) {
        visited[cur] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(cur);

        while(!queue.isEmpty()) {
            int value = queue.poll();
            for(int i = 0; i < computers[value - 1].length; i++) {
                if(computers[value - 1][i] == 1 && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }
            }
        }
    }
}
