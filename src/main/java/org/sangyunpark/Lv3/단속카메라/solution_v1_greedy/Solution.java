package org.sangyunpark.Lv3.단속카메라.solution_v1_greedy;

import java.util.*;

public class Solution {
    public int solution(int[][] routes) {
        int answer = 1; // 맨 처음 카메라 설치

        // 1. 2차원 배열 정렬(시작점 기준으로)
        // O(nlogn)
        Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));

        int endPoint = routes[0][1];

        for(int i = 1; i < routes.length; i++) {
            int nextStartPoint = routes[i][0];
            int nextEndPoint = routes[i][1];
            if(endPoint < nextStartPoint) {
                endPoint = nextEndPoint;
                answer++;
            }
        }

        return answer;
    }
}
