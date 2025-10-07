package org.sangyunpark.Lv3.스타수열;

import java.util.*;

class Solution {

    private HashMap<Integer, Integer> preq = new HashMap<>();

    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;

        if(n < 2) return 0;

        for(int i = 0; i < n; i++) {
            int value = a[i];
            preq.put(value, preq.getOrDefault(value, 0) + 1);
        }

        List<Integer> keys = new ArrayList<>(preq.keySet());

        for(int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);

            if(preq.get(key) * 2 < answer) continue; // 효율성 고려

            int idx = 0;
            int result = 0;

            while(idx < n - 1) {
                int first = a[idx];
                int second = a[idx + 1];

                if((first == key || second == key) && first != second) {
                    idx += 2;
                    result++;
                    continue;
                }

                idx++;
            }

            answer = Math.max(answer, result * 2);
        }

        return answer;
    }
}
