package org.sangyunpark.Lv3.N으로표현;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private static Set<Integer>[] dp = new HashSet[9];

    public static void main(String[] args) {
        System.out.println(solution(5, 12));
    }

    public static int solution(int N, int number) {
        int answer = 0;

        for(int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }

        int concat = 0;
        for(int i = 1; i <= 8; i++) { // 5, 55, 555, 55555
            concat = concat * 10 + N;
            dp[i].add(concat);
        }

        for(int k = 1; k <= 8; k++) {
            for(int i = 1; i < k; i++) { // i = 1 ~ 7
                int j = k - i; // j = 7 ~ 1
                for(int a : dp[i]) {
                    for(int b : dp[j]) {
                        dp[k].add(a + b);
                        dp[k].add(a - b);
                        dp[k].add(a * b);
                        if(b != 0) dp[k].add(a / b);
                    }
                }
            }

            if(dp[k].contains(number)) return k;
        }

        return -1;
    }
}
