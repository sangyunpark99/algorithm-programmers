package org.sangyunpark.Lv3.등대;

import java.util.*;

class Solution {

    private List<List<Integer>> graph = new ArrayList<>();
    private int[][] dp;

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < lighthouse.length; i++) {
            int[] house = lighthouse[i];
            int start = house[0];
            int end = house[1];

            // 양방향 그래프
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        dp = new int[n + 1][2];
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, visited);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int cur, boolean[] visited) {

        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for(int i = 0; i < graph.get(cur).size(); i++) { // 리프면 이 단계 생략
            int next = graph.get(cur).get(i);
            if(!visited[next]) {
                visited[next] = true; // 방문 처리
                dfs(next, visited);
                // 리프 노드 까지 도착
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]); // 자식 노드가 켜지거나 꺼져도 된다.
            }
        }
    }
}