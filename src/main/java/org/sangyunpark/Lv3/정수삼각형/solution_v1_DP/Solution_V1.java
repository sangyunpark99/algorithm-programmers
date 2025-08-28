package org.sangyunpark.Lv3.정수삼각형.solution_v1_DP;

public class Solution_V1 {
    public int solution(int[][] triangle) {
        int answer = 0;
        // 단계별로 반복하면서, 왼쪽 or 오른쪽 중 더한 합이 최대인 숫자로 갱신

        for(int i = 1; i < triangle.length; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                if( j == 0) { // 맨 왼쪽은 바로 위의 값 더하기
                    triangle[i][0] += triangle[i-1][0];
                } else if (i == j) { // 맨 오른쪽은 바로 위 한칸 왼쪽 값 더하기
                    triangle[i][j] += triangle[i-1][j-1];
                } else { // 왼쪽 or 오른쪽의 더 큰수 더하기
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
            }
        }

        for(int value : triangle[triangle.length - 1]) { // O(맨 마지막 줄)
            answer = Math.max(answer, value);
        }

        // 시간 복잡도 O(n^2) => 125,250?

        return answer;
    }
}
