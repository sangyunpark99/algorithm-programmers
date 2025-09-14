package org.sangyunpark.Lv1.신규아이디추천;

public class Solution_v1_refactor {
    public String solution(String new_id) {
        String answer = "";
        char[] values = new_id.toCharArray();

        for(int i = 0; i < values.length; i++) {
            answer += String.valueOf(values[i]).toLowerCase();
        }

        values = answer.toCharArray();
        answer = "";
        for(int i = 0; i < values.length; i++) {
            char ch = values[i];
            if(ch == '-' || ch == '_' || ch == '.' || Character.isLowerCase(ch) || Character.isDigit(ch)) {
                answer += String.valueOf(ch);
            }
        }

        while(answer.contains("..")) {
            answer = answer.replace("..", ".");
        }


        values = answer.toCharArray();
        answer = "";
        for(int i = 0; i < values.length; i++) {
            char ch = values[i];
            if((i == 0 || i == values.length - 1) && values[i] == '.') {
                continue;
            }

            answer += ch;
        }

        if(answer.isEmpty()) {
            answer = "a";
        }

        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
        }

        if(answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        if(answer.length() <= 2) {
            while(answer.length() < 3) {
                answer += answer.charAt(answer.length() - 1);
            }
        }

        return answer;
    }
}
