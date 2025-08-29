package org.sangyunpark.Lv3.숫자게임.solution_v1_sort;

import java.util.*;

public class Solution {
    public int solution(int[] A, int[] B) {

        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int j = B.length - 1;
        for(int i = A.length - 1; i >= 0; i--) {
            if(A[i] < B[j]) {
                j--;
                answer++;
            }
        }

        return answer;
    }
}
