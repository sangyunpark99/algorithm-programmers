package org.sangyunpark.Lv3.등산코스정하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    // 가중치가 전부 다르다.

    public static class Node {
        int v, w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        List<List<Node>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] p: paths) {
            graph.get(p[0]).add(new Node(p[1], p[2]));
            graph.get(p[1]).add(new Node(p[0], p[2]));
        }

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];
        for(int a : gates) isGate[a] = true;
        for(int b : summits) isSummit[b] = true;

        int INF = Integer.MAX_VALUE;
        int[] intensity = new int[n + 1]; // 해당 노드 번호까지의 intensity
        Arrays.fill(intensity, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for(int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new int[]{0, gate});
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curIntensity = cur[0];
            int num = cur[1];

            if(curIntensity > intensity[num]) continue;
            if(isSummit[num]) continue;

            for(Node next : graph.get(num)) {
                int v = next.v;
                int w = next.w;
                if(isGate[v]) continue;
                int nextIntensity = Math.max(curIntensity, w);
                if(nextIntensity < intensity[v]) { // 이동해야 하는 시간 중 가장 긴 시간
                    intensity[v] = nextIntensity;
                    pq.offer(new int[]{nextIntensity, v});
                }
            }
        }

        Arrays.sort(summits); // 높이가

        int a = 0;
        int b = Integer.MAX_VALUE;

        for(int i = 0; i < summits.length; i++) { // 제일 작은 값
            int summit = summits[i];
            if(b > intensity[summit]) {
                a = summit;
                b = intensity[summit];
            }
        }

        return new int[]{a,b};
    }
}
