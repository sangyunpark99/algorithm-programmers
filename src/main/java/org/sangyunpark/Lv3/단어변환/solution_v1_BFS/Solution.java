package org.sangyunpark.Lv3.단어변환.solution_v1_BFS;
import java.util.*;

public class Solution {
    private boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        boolean isMake = false;
        for(String word : words) {
            if(target.equals(word)) {
                isMake = true;
                break;
            }
        }

        if(!isMake) return 0;

        visited = new boolean[words.length];

        return bfs(begin, words, target);
    }

    private int bfs(String begin, String[] words, String target) {
        Node node = new Node(begin, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        int answer = 0;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            // 동일한지 비교 -> 제일 처음 나온게 최솟값
            if(cur.word.equals(target)) {
                answer = cur.value;
                break;
            }

            for(int i = 0; i < words.length; i++) {
                String next = words[i];
                if(!visited[i] && isConvert(cur.word, next)) {
                    visited[i] = true;
                    queue.offer(new Node(next, cur.value + 1));
                }
            }
        }

        return answer;
    }

    public static class Node {
        String word;
        int value;

        Node(String word, int value) {
            this.word = word;
            this.value = value;
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
