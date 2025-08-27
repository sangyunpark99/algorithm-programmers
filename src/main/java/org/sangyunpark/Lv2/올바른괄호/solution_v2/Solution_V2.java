package org.sangyunpark.Lv2.올바른괄호.solution_v2;

public class Solution_V2 {
    boolean solution(String s) {
        int answer = 0;

        for(char value : s.toCharArray()) {
            answer = value == ')' ? answer-1 : answer + 1;
            if(answer < 0) return false;
        }

        return answer == 0;
    }
}
