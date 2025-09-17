package org.sangyunpark.Lv3.표편집;

import java.util.*;

class Solution {

    private int[] prev;
    private int[] next;
    private boolean[] isRemoved;
    private Stack<Integer> stack = new Stack<>();

    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();

        prev = new int[n];
        next = new int[n];
        isRemoved = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                prev[i] = -1;
                next[i] = i + 1;
                continue;
            }

            if(i == n - 1) {
                prev[i] = i - 1;
                next[i] = -1;
                continue;
            }

            prev[i] = i - 1;
            next[i] = i + 1;
        }

        int curIdx = k;

        for(int i = 0; i < cmd.length; i++) {
            String a = cmd[i];
            String[] splitA = a.split(" ");
            String order = splitA[0];

            if("U".equals(order)) {
                int num = Integer.parseInt(splitA[1]);
                while(num > 0 && prev[curIdx] != -1) {
                    curIdx = prev[curIdx];
                    num--;
                }
            }

            if("D".equals(order)) {
                int num = Integer.parseInt(splitA[1]);
                while(num > 0 && next[curIdx] != -1) {
                    curIdx = next[curIdx];
                    num--;
                }
            }

            if("C".equals(order)) {
                isRemoved[curIdx] = true;
                stack.push(curIdx);

                int pr = prev[curIdx];
                int nt = next[curIdx];

                if(pr != -1) next[pr] = nt; // 이전 값이 음수인지 체크
                if(nt != -1) prev[nt] = pr; // 다음 값이 음수인지 체크

                if(nt == -1) { // 맨 아래인 경우
                    curIdx = pr; // 이전 idx로 이동
                    continue;
                }

                curIdx = nt; // 다음 idx로 이동
            }

            if("Z".equals(order)) {
                if(!stack.isEmpty()) {
                    int num = stack.pop();
                    isRemoved[num] = false;
                    int pr = prev[num];
                    int nt = next[num];
                    if(pr!=-1)next[pr] = num;
                    if(nt!=-1)prev[nt] = num;
                }

            }
        }

        for(int i = 0; i < n; i++) {
            if(isRemoved[i]) {
                sb.append("X");
                continue;
            }
            sb.append("O");
        }

        return sb.toString();
    }
}
