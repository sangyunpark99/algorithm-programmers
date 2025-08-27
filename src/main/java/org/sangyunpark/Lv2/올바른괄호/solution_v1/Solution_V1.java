package org.sangyunpark.Lv2.올바른괄호.solution_v1;

import java.util.*;

public class Solution_V1 {

    private Stack<Character> stack = new Stack<>();

    boolean solution(String s) {
        boolean answer = true;

        for(char value : s.toCharArray()) {
            if(stack.isEmpty()) { // stack이 비어있는 경우
                if(value == ')') { // 처음부터 닫히는 문자가 들어온 경우
                    return false;
                }
                stack.push(value); // 값을 집어 넣기
            }else { // 이미 stack에 값이 존재하는 경우
                if(value == ')') { // 닫히는 문자열인 경우
                    stack.pop();
                } else { // 열리는 문자열인 경우
                    stack.push(value);
                }
            }
        }

        if(stack.isEmpty()) return true; // 스택이 비어있는 경우? 짝이 전부 존재하는 경우
        return false;
    }
}
