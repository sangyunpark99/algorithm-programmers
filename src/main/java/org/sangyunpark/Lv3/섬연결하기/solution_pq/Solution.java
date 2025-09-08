package org.sangyunpark.Lv3.섬연결하기.solution_pq;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
  private List<List<int[]>> graph = new ArrayList<>();
  private boolean[] visited;

  public int solution(int n, int[][] costs) {

    for(int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < costs.length; i++) {
      int[] cost = costs[i];
      int from = cost[0];
      int to = cost[1];
      int weight = cost[2];
      graph.get(from).add(new int[]{to, weight});
      graph.get(to).add(new int[]{from, weight});
    }

    visited = new boolean[n];
    return find();
  }

  private int find() {

    int value = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);
    pq.offer(new int[]{0,0});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curNumber = cur[0];
      int curWeight = cur[1];

      if(visited[curNumber]) continue;
      visited[curNumber] = true;
      value += curWeight;

      for(int i = 0; i < graph.get(curNumber).size(); i++) {
        int[] next = graph.get(curNumber).get(i);
        int nextNumber = next[0];
        int nextWeight = next[1];
        pq.offer(new int[]{nextNumber, nextWeight});
      }
    }

    return value;
  }
}
