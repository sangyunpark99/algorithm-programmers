package org.sangyunpark.Lv3.등굣길;

public class Solution {
    // 각 칸에 올 수 있는 경우의 수를 누적해서 저장
    private final int MOD = 1_000_000_007;
    private int[][] map;
    private boolean[][] checkPuddle; // 웅덩이 인지 확인

    public int solution(int m, int n, int[][] puddles) {

        map = new int[n][m];
        checkPuddle = new boolean[n][m];

        for(int[] puddle: puddles) {
            checkPuddle[puddle[1] - 1][puddle[0] - 1] = true;
        }

        for(int j = 1; j < m; j++) {
            if(checkPuddle[0][j]) break; // 웅덩이를 만나면 경우의 수 x
            map[0][j] = 1;
        }

        for(int i = 1; i < n; i++) {
            if(checkPuddle[i][0]) break; // 웅덩이를 만나면 경우의 수 x
            map[i][0] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(checkPuddle[i][j]) continue; // 물 웅덩이는 패스
                map[i][j] = map[i-1][j] % MOD + map[i][j-1] % MOD;
            }
        }

        return map[n - 1][m - 1] % MOD;
    }
}
