package org.sangyunpark.Lv3.외벽점검;

import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer = dist.length + 1;
        int len = weak.length;

        int[] doubleWeak = new int[2 * len];

        for(int i = 0; i < len; i++) {
            doubleWeak[i] = weak[i];
            doubleWeak[i + len] = weak[i] + n;
        }

        List<int[]> permu = getPermu(dist);

        for(int[] friend : permu) {
            for(int start = 0; start < len; start++) { // 시작 지점
                int cnt = 1;
                int coverage = doubleWeak[start] + friend[cnt - 1]; // 친구 idx
                boolean ok = true;
                for(int i = start; i < start + len; i++) { // 시작 지점 부터
                    if(coverage < doubleWeak[i]) {
                        if(cnt == friend.length) { // 친구를 전부 사용한 경우
                            ok = false;
                            break;
                        }
                        cnt++;
                        coverage = doubleWeak[i] + friend[cnt - 1]; // 친구 idx
                    }
                }

                if(ok) answer = Math.min(answer, cnt);
            }
        }

        return answer > dist.length ? -1 : answer;
    }

    private List<int[]> getPermu(int[] arr) {
        List<int[]> result = new ArrayList<>();
        permutation(arr, 0, result);
        return result;
    }

    private void permutation(int[] arr, int depth, List<int[]> result) {
        if(depth == arr.length) {
            result.add(arr.clone());
            return;
        }

        for(int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            permutation(arr, depth + 1, result);
            swap(arr, i, depth);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}