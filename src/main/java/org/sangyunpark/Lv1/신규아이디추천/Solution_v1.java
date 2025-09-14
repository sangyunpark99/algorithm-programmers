package org.sangyunpark.Lv1.신규아이디추천;

public class Solution_v1 {
    public String solution(String new_id) {
        String answer = "";
        char[] values = new_id.toCharArray();

        int n = 1;

        while(n < 8) {

            if(n == 1) {
                for(int i = 0; i < values.length; i++) {
                    answer += String.valueOf(values[i]).toLowerCase();
                }
                n++;
            }

            if(n == 2) {
                values = answer.toCharArray();
                answer = "";
                for(int i = 0; i < values.length; i++) {
                    char ch = values[i];
                    if(ch == '-' || ch == '_' || ch == '.' || Character.isLowerCase(ch) || Character.isDigit(ch)) {
                        answer += String.valueOf(ch);
                    }
                }
                n++;
            }

            if(n == 3) { // .... 인경우

                while(answer.contains("..")) {
                    answer = answer.replace("..", ".");
                }
                n++;
            }

            if(n == 4) {
                values = answer.toCharArray();
                answer = "";
                for(int i = 0; i < values.length; i++) {
                    char ch = values[i];
                    if((i == 0 || i == values.length - 1) && values[i] == '.') {
                        continue;
                    }

                    answer += ch;
                }
                n++;
            }

            if(n == 5) {

                if(answer.isEmpty()) {
                    answer = "a";
                }
                n++;
            }

            if(n == 6) {

                if(answer.length() >= 16) {
                    answer = answer.substring(0, 15);
                }

                if(answer.charAt(answer.length() - 1) == '.') {
                    answer = answer.substring(0, answer.length() - 1);
                }
                n++;
            }

            if(n == 7) {

                if(answer.length() <= 2) {
                    while(answer.length() < 3) {
                        answer += answer.charAt(answer.length() - 1);
                    }
                }
                n++;
            }
        }

        return answer;
    }
}
