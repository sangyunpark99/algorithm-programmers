package org.sangyunpark.Lv3.최고의집합;

public class Solution {
    private int[] answer;

    // 균등하게 나눠주면 된다.
    public int[] solution(int n, int s) {

        if(s < n) return new int[]{-1}; // n이 s보다 크면 만들 수 없다.

        int value = s / n;
        int a = s % n;

        answer = new int[n];

        for(int i = 1; i <= n; i++) {
            if(a > 0) {
                answer[n - i] = value + 1;
                a--;
            }else {
                answer[n - i] = value;
            }
        }

        return answer;
    }
}
