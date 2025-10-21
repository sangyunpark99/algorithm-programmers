package org.sangyunpark.Lv3.모두0으로만들기;

import java.util.*;

class Solution {

    private List<List<Integer>> graph = new ArrayList<>();
    private long answer = 0;
    private long[] w;

    public long solution(int[] a, int[][] edges) {

        boolean sumZero = false;
        long sum = 0L; // int형보다 커질 위험 존재
        for(int value : a) {
            sum += value;
        }
        sumZero = sum == 0;

        if(!sumZero) {
            return -1;
        }

        w = new long[a.length];
        for(int i = 0; i < a.length; i++) {
            w[i] = a[i];
        }

        // 최소 횟수 찾기
        for(int i = 0; i < a.length; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(0, new boolean[a.length]);

        return answer;
    }

    private void dfs(int curNumber, boolean[] visited) {

        visited[curNumber] = true;

        for(int i = 0; i < graph.get(curNumber).size(); i++) {
            int nextNumber = graph.get(curNumber).get(i);
            if(visited[nextNumber]) {
                continue;
            }
            dfs(nextNumber, visited);
            long gap = Math.abs(w[nextNumber]);
            w[curNumber] += w[nextNumber];
            answer += gap;
        }
    }
}
