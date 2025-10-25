package org.sangyunpark.Lv3.징검다리건너기;

public class Solution {
    // 최대 몇명이 징검다리를 건널 수 있는가?
    private int answer = 0;
    private int n;

    public int solution(int[] stones, int k) {
        int answer = 0;

        n = stones.length;

        if(stones.length == 1) {
            return stones[0];
        }

        int end = Integer.MIN_VALUE;

        for(int stone : stones) { // O(n) = 200,000
            end = Math.max(end, stone);
        }

        int start = 1;

        while(start <= end) { // O(nlog n) -> 560만
            int mid = (start + end) / 2;
            if(check(mid, k, stones)) { // 건널 수 있는 경우
                start = mid + 1;
                answer = mid;
            }else { // 건널 수 없는 경우 - 인원 수 감축하기
                end = mid - 1;
            }
        }
        // O(n) = 580만

        return answer;
    }

    private boolean check(int mid, int k, int[] stones) { // 해당 인원수가 징검다리를 건널 수 있는가?

        int jump = 0; // 뛰어 넘어야 할 돌의 수
        for(int i = 0; i < n; i++) { // O
            if(stones[i] < mid) { // 이미 밟을 수 없이 작은 경우
                jump++; // 뛰어넘어야 함
            }else { // 밟을 수 있는 경우
                jump = 0; // 밟을 수 있기 때문에 초기화
            }

            if(jump > k - 1) {
                return false;
            }
        }

        return true;
    }
}
