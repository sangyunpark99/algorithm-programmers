package org.sangyunpark.Lv3.단어변환.solution_v2_BFS;

import java.util.*;

public class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean isMake = false;
        for(String word : words) {
            if(target.equals(word)) {
                isMake = true;
                break;
            }
        }

        if(!isMake) return 0;

        return bfs(begin, words, target);
    }

    private int bfs(String begin, String[] words, String target) {
        HashMap<String,Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        map.put(begin, 0);
        queue.offer(begin);

        int answer = 0;

        while(!queue.isEmpty()) {
            String cur = queue.poll();

            // 동일한지 비교 -> 제일 처음 나온게 최솟값
            if(cur.equals(target)) {
                answer = map.get(cur);
                break;
            }

            for(int i = 0; i < words.length; i++) {
                String next = words[i];
                if(!map.containsKey(next) && isConvert(cur, next)) {
                    map.put(next, map.get(cur) + 1);
                    queue.offer(next);
                }
            }
        }

        return answer;
    }

    private boolean isConvert(String cur, String next) { // 변환 가능 여부
        int diffCnt = 0;

        for(int i = 0; i < cur.length(); i++) {
            if(cur.charAt(i) != next.charAt(i)) {
                diffCnt++;
                if(diffCnt > 1) return false;
            }
        }

        return diffCnt == 1;
    }
}
