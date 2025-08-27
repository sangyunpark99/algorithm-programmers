package org.sangyunpark.Lv3.네트워크.solution_v2;

public class Solution_V2 {
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                answer++;
                dfs(i, computers);
            }
        }

        return answer;
    }

    private void dfs(int cur, int[][] computers) {
        visited[cur] = true;
        for(int i = 0; i < computers[cur - 1].length; i++) {
            if(computers[cur-1][i] == 1 && !visited[i + 1]) {
                dfs(i + 1, computers);
            }
        }
    }
}
