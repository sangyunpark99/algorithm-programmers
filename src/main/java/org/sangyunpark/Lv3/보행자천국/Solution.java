package org.sangyunpark.Lv3.보행자천국;

public class Solution {
    int MOD = 20170805;

    private int[][][] cnt;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        cnt = new int[m][n][2]; // 0(오른쪽), 1(아래)

        for(int i = 0; i < n; i++) { // 오른쪽으로 이동
            if(cityMap[0][i] == 1) {
                break;
            }

            cnt[0][i][0] = 1;
        }

        for(int i = 0; i < m; i++)  {
            if(cityMap[i][0] == 1) {
                break;
            }else {
                cnt[i][0][1] = 1;
            }
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(cityMap[i][j] == 1) continue;

                if(cityMap[i-1][j] == 2) { // 위
                    cnt[i][j][1] = (cnt[i][j][1] + cnt[i - 1][j][1]) % MOD;
                } else {
                    cnt[i][j][1] = (cnt[i][j][1] + cnt[i - 1][j][0]) % MOD;
                    cnt[i][j][1] = (cnt[i][j][1] + cnt[i - 1][j][1]) % MOD;
                }

                if(cityMap[i][j - 1] == 2) { // 왼
                    cnt[i][j][0] = (cnt[i][j][0] + cnt[i][j - 1][0]) % MOD;
                } else {
                    cnt[i][j][0] = (cnt[i][j][0] + cnt[i][j - 1][0]) % MOD;
                    cnt[i][j][0] = (cnt[i][j][0] + cnt[i][j - 1][1]) % MOD;
                }
            }
        }

        return (cnt[m-1][n-1][0] % MOD + cnt[m-1][n-1][1] % MOD) % MOD;
    }
}
