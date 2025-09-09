package org.sangyunpark.Lv3.디스크컨트롤러.solution_PQ_Queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

  public int solution(int[][] jobs) {
    int answer = 0;
    int totalTT = 0;

    Arrays.sort(jobs, (a,b) -> { // 시작 시간으로 정렬
      return a[0] - b[0];
    });

    PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
      return a.playTime - b.playTime;
    });

    Queue<Task> waitQueue = new LinkedList<>(); // 대기 큐

    for(int i = 0; i < jobs.length; i++) { // O(nlogn)
      int[] job = jobs[i];
      waitQueue.offer(new Task(job[0],job[1]));
    }

    int time = 0;
    while(!pq.isEmpty() || !waitQueue.isEmpty()) { // O(n)

      while(!waitQueue.isEmpty()) { // 대기 큐
        if(time >= waitQueue.peek().requestTime) {
          pq.offer(waitQueue.poll());
          continue;
        }

        break;
      }

      if(pq.isEmpty()) {
        time = waitQueue.peek().requestTime;
        continue;
      }

      Task cur = pq.poll();
      time = time + cur.playTime;
      totalTT += time - cur.requestTime;
    }

    return totalTT / jobs.length;
  }

  public static class Task {

    int playTime;
    int requestTime;

    public Task(int requestTime, int playTime) {
      this.playTime = playTime;
      this.requestTime = requestTime;
    }
  }
}
