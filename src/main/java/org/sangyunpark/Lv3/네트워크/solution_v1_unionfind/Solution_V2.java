package org.sangyunpark.Lv3.네트워크.solution_v1_unionfind;

public class Solution_V2 {
    private int[] parent;

    public int solution(int n, int[][] computers) {

        int answer = 0;
        parent = new int[n];

        for(int i = 0; i < n; i++) { // 부모 초기화
            parent[i] = i;
        }

        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if(computers[i][j] == 1 && i != j) { // 하나의 유니온으로 묶기
                    union(i, j);
                }
            }
        }

        boolean[] check = new boolean[n];
        for(int i = 0; i < n; i++) { // 루트 부모가 저장이 안될 수도 있다.
            int parent = find(i);
            if(!check[parent]) {
                answer++;
                check[parent] = true;
            }
        }

        return answer;
    }

    private int find(int cur) {
        if(parent[cur] == cur) return cur;
        return parent[cur] = find(parent[cur]);
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return;

        if(pa < pb) {
            parent[pb] = pa;
        }else {
            parent[pa] = pb;
        }
    }
}
