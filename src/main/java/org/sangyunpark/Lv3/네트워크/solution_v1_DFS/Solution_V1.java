package org.sangyunpark.Lv3.네트워크.solution_v1_DFS;

import java.util.ArrayList;
import java.util.List;

public class Solution_V1 {
    private List<List<Integer>> graph = new ArrayList<>();
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        for(int i = 0; i <= n; i++) { // 1번부터
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < computers.length; i++) { // 노드 번호 탐색
            int[] link = computers[i];
            for(int j = 0; j < link.length; j++) { // 연결된 노드 번호 탐색
                if(link[j] == 1 && i + 1 != j + 1) { // 1인 경우 노드 연결 추가(자기 자신은 제외)
                    graph.get(i+1).add(j + 1);
                }
            }
        }

        visited = new boolean[n + 1];

        // dfs, bfs
        for(int i = 1; i <= n; i++) { // 노드를 탐색하면서
            if(!visited[i]) { // 방문하지 않은 경우
                answer++; // 네트워크 추가
                dfs(i); // dfs로 연결된 노드 제외
            }
        }

        return answer;
    }

    private void dfs(int cur) {
        visited[cur] = true; // 방문 처리
        for(int next : graph.get(cur)) { // 연결된 노드 조회
            if(!visited[next]) { // 방문하지 않은 경우
                dfs(next); // 해당 노드로 재 탐색
            }
        }
    }
}
