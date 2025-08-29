package org.sangyunpark.Lv3.숫자게임.solution_v1_PriorityQueue;

import java.util.*;

public class Solution {

    private PriorityQueue<Integer> apq = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> bpq = new PriorityQueue<>(Collections.reverseOrder());

    public int solution(int[] A, int[] B) {
        int answer = 0;

        for(int i = 0; i < A.length; i++) { // O(nlogn)
            apq.offer(A[i]);
            bpq.offer(B[i]);
        }

        while(!apq.isEmpty()) {

            int avalue = apq.poll();
            int bvalue = bpq.poll();

            if(avalue < bvalue) {
                answer++;
            } else { // 값을 다시 넣어 주어야 그 다음 큰 친구와 비교 가능
                bpq.offer(bvalue);
            }
        }

        return answer;
    }
}
