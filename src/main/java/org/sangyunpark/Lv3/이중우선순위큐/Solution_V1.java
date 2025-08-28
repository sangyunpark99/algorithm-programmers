package org.sangyunpark.Lv3.이중우선순위큐;

import java.util.*;

public class Solution_V1 {
    public int[] solution(String[] operations) {
        int[] answer = {};

        PriorityQueue<Integer> minpq = new PriorityQueue<>();
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());

        for(String operation: operations) { // O(1,000,000)
            String[] value = operation.split(" "); // 문자열 분리
            int second = Integer.parseInt(value[1]); // 두번째 숫자 분리

            if("I".equals(value[0])) { // I인 경우
                minpq.offer(second); // 최소 우선순위 큐에 값 추가
                maxpq.offer(second); // 최대 우선순위 큐에 값 추가
            } else {
                if(second == 1) { // 최댓값
                    minpq.remove(maxpq.poll()); // 최소 우선 순위 큐, 최대 우선 순위 큐에서 값 제거
                } else if(second == -1) { // 최솟값
                    maxpq.remove(minpq.poll()); // 최대 우선 순위 큐에서 값 제거
                }
            }
        }

        if(minpq.isEmpty() && maxpq.isEmpty()) { // 둘 다 비어 있는 경우
            return new int[]{0,0};
        }

        return new int[]{maxpq.poll(),minpq.poll()}; // 그렇지 않은 경우
    }
}
