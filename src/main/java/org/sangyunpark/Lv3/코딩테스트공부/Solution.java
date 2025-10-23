package org.sangyunpark.Lv3.코딩테스트공부;

import java.util.Arrays;

public class Solution {

    private int targetAlp = Integer.MIN_VALUE;
    private int targetCop = Integer.MIN_VALUE;
    private int cnt = Integer.MAX_VALUE;
    private int[][] dp;
    private int[][] Problems;

    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        Problems = new int[problems.length][problems[0].length];

        for(int i = 0; i < problems.length; i++) {
            Problems[i] = problems[i].clone();
        }

        for(int[] problem: problems) {
            targetAlp = Math.max(targetAlp, problem[0]);
            targetCop = Math.max(targetCop, problem[1]);
        }

        if(targetAlp <= alp && targetCop <= cop) {
            return 0;
        }

        dp = new int[targetAlp + 1][targetCop + 1];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }

        dfs(alp, cop, 0);

        return dp[targetAlp][targetCop];
    }

    private void dfs(int algo, int cop, int time) {

        algo = Math.min(targetAlp, algo);
        cop = Math.min(targetCop, cop);

        if(dp[algo][cop] <= time) { // 가지치기
            return;
        }

        dp[algo][cop] = time;


        if(algo == targetAlp && cop == targetCop) {
            return;
        }

        // 1. 알고력 늘리기
        dfs(algo + 1, cop, time + 1);
        // 2. 코딩력 늘리기
        dfs(algo, cop + 1, time + 1);
        // 3. 문제 풀기 - 풀 수 있는 문제
        for(int[] problem: Problems) {
            if(algo >= problem[0] && cop >= problem[1])  { // 풀 수 있는 경우
                dfs(algo + problem[2], cop + problem[3], time + problem[4]); // 문제 풀기
            }
        }
    }
}
