package org.sangyunpark.Lv3.스티커모으기2복습;

public class Solution {

    private int[][] dp;

    public int solution(int sticker[]) {
        int n = sticker.length;

        if(n == 1) {
            return sticker[0];
        }

        // 선택, 선택 x
        dp = new int[sticker.length][2];

        // 1. 첫 스티커를 선택하는 경우 (0 ~ n - 2)
        dp[0][1] = sticker[0];
        for(int i = 1; i <= n - 2; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = sticker[i] + dp[i - 1][0]; // 이전
        }

        int answer = Math.max(dp[n - 2][0], dp[n - 2][1]);

        dp = new int[sticker.length][2];
        for(int i = 1; i <= n - 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = sticker[i] + dp[i - 1][0];
        }

        answer = Math.max(answer, Math.max(dp[n - 1][0], dp[n - 1][1]));

        return answer;
    }
}
