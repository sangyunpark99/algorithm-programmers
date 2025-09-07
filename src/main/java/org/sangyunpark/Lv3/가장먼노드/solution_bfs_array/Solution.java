package org.sangyunpark.Lv3.가장먼노드.solution_bfs_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

  private int[] dist;
  private int maxDepth = Integer.MIN_VALUE;
  private List<List<Integer>> graph = new ArrayList<>();

  public int solution(int n, int[][] edge) {
    int answer = 0;

    dist = new int[n + 1];

    Arrays.fill(dist, -1);

    for(int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < edge.length; i++) {
      int[] value = edge[i];
      int first = value[0];
      int second = value[1];
      graph.get(first).add(second);
      graph.get(second).add(first);
    }

    bfs();

    for(int i = 0; i < dist.length; i++) {
      if(dist[i] == maxDepth) {
        answer++;
      }
    }

    return answer;
  }

  private void bfs() { // O(V + E) = 70,000
    dist[1] = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(1);

    while(!queue.isEmpty()) {
      int number = queue.poll();
      maxDepth = Math.max(maxDepth, dist[number]);
      for(int i = 0; i < graph.get(number).size(); i++) {
        int next = graph.get(number).get(i);
        if(dist[next] == -1) {
          dist[next] = dist[number] + 1;
          queue.offer(next);
        }
      }
    }
  }
}
