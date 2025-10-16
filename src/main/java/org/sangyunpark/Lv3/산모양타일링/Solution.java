package org.sangyunpark.Lv3.산모양타일링;

import java.util.*;

class Solution {

    private int[][] dp;
    private final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int answer = 0;

        // 0 : 오른쪽 끝이 마름모로 끝남, 1 : 오른쪽 끝이 마름모로 끝나지 않음
        dp = new int[n + 1][2];


        dp[0][0] = tops[0] == 1 ? 3 : 2;
        dp[0][1] = 1;

        for(int i = 1; i < n; i++) {
            int value = tops[i];

            if(value == 1) { // 현재 봉우리인 경우
                dp[i][0] = (dp[i - 1][0] * 3 + dp[i - 1][1] * 2) % MOD;
            } else { // 봉우리가 아닌 경우
                dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 1][1]) % MOD;
            }

            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        return (dp[n - 1][1] + dp[n - 1][0]) % MOD;
    }
}
