package org.sangyunpark.Lv3.기지국.solution_greedy;

import java.util.*;

public class Solution {
    private List<int[]> empty = new ArrayList<>();

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int end = 0;

        int boxSize = 2*w + 1;

        for(int i = 0; i < stations.length; i++) { // O(n)
            int elec = stations[i];
            int minElec = elec - w;
            int maxElec = elec + w;
            end = minElec - 1;
            int gap = end - start + 1;
            answer += gap / boxSize;
            if(gap % boxSize > 0) answer++;
            start = maxElec + 1;
        }

        if(start <= n) {
            int gap = n - start + 1;
            answer += gap / boxSize;
            if(gap % boxSize > 0) answer++;
        }

        return answer;
    }
}
