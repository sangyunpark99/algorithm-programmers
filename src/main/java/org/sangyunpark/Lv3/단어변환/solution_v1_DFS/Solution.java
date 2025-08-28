package org.sangyunpark.Lv3.단어변환.solution_v1_DFS;

public class Solution {
    private boolean[] visited;
    private int answer = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {

        // 만들 수 있는 단어인지 확인
        boolean isMake = false;
        for(String word : words) {
            if(target.equals(word)) {
                isMake = true;
                break;
            }
        }

        if(!isMake) return 0;

        visited = new boolean[words.length];
        dfs(begin, words, target, 0);

        return answer;
    }

    private void dfs(String cur, String[] words, String target, int value) {

        if(cur.equals(target)) { // 목표물을 찾은 경우
            answer = Math.min(answer, value);
            return;
        }

        for(int i = 0; i < words.length; i++) {
            String word = words[i];

            if(isConvert(cur, word) && !visited[i]) { // 변환 가능하고, 방문하지 않은 경우
                visited[i] = true; // 방문 처리
                dfs(word, words, target, value + 1); // 다음 단어로 이동
                visited[i] = false; // 원상 복구
            }
        }
    }

    private boolean isConvert(String cur, String next) { // 변환 가능 여부
        int diffCnt = 0;

        for(int i = 0; i < cur.length(); i++) {
            if(cur.charAt(i) != next.charAt(i)) {
                diffCnt++;
            }
        }

        return diffCnt == 1;
    }
}
