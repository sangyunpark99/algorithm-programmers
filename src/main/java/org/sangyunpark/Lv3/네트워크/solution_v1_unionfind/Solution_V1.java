package org.sangyunpark.Lv3.네트워크.solution_v1_unionfind;

import java.util.*;

public class Solution_V1 {
    private int[] parent;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 연결된 노드를 자신의 부모로 지정
        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if(computers[i][j] == 1) {
                    merge(i,j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(find(i)); // 직계 부모가 아닌, 루트 부모의 값을 set에 저장해서 네트워크 갯수를 세야한다.
        }

        System.out.println(Arrays.toString(parent));

        return set.size();
    }

    private int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 진짜 루트 부모 찾기
    }

    private void merge(int a, int b) {
        int pa = find(a); // a 부모 찾기
        int pb = find(b); // b 부모 찾기

        if(pa == pb) return; // 이미 하나의 묶음이 되어 있는 경우 return

        // 더 작은 수를 부모로 묶기
        if(pa < pb) {
            parent[pb] = pa;
        }else {
            parent[pa] = pb;
        }
    }
}
