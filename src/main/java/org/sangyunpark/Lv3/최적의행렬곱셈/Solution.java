package org.sangyunpark.Lv3.최적의행렬곱셈;

public class Solution {
    // (1) AB / C, (2) BC / A - 어떤 값을 먼저 곱하느냐가 관건
    // 행렬의 곱셈을 하기 위해선 [a,b],[c,d] b와 c의 값이 동일해야 한다.
    // 어떤 순서로 할까? 순열? 200 P 200 은 너무 큰 값이 아닌가?
    // 왼쪽 부분 + 오른쪽 부분 + 현재 곱셈 = 최솟값

    private int[][] dp;
    private int[] p;

    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        int n = matrix_sizes.length;

        dp = new int[n + 1][n + 1]; // i번째 행렬부터 j번째 행렬까지 곱하는 최소 연산 횟수
        // 작은 구간의 결과가 큰 구간을 계산할 때 존재해야 한다.

        for(int i = 0; i <= n; i++) {
            dp[i][i] = 0;
        }

        p = new int[n + 1];

        p[0] = matrix_sizes[0][0];

        for(int i = 0; i < n; i++) {
            p[i + 1] = matrix_sizes[i][1];
        }

        for(int len = 2; len <= n; len++) { // 행렬 len개짜리 조합
            for(int i = 1; i <= n - len + 1; i++) { // 시작 지점
                int j = i + len - 1;

                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j]; // 두 결과를 서로 곱할때 사용되는 비용
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n];
    }
}
