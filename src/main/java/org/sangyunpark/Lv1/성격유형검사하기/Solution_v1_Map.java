package org.sangyunpark.Lv1.성격유형검사하기;

import java.util.*;


public class Solution_v1_Map {
    private HashMap<String, Integer> cnt = new HashMap<>();
    private List<String> twin = new ArrayList<>();
    private String[] kind = new String[] {
            "R","T","C","F","J","M","A","N"
    };

    public String solution(String[] survey, int[] choices) {
        String answer = "";

        twin.add("R T");
        twin.add("C F");
        twin.add("J M");
        twin.add("A N");

        for(int i = 0; i < kind.length; i++) {
            cnt.put(kind[i], 0);
        }

        for(int i = 0; i < survey.length; i++) {
            String value = survey[i];
            String[] splitValue = value.split("");
            String a = splitValue[0];
            int aCnt = 0;
            String b = splitValue[1];
            int bCnt = 0;

            int choice = choices[i];

            if(choice < 4) { // 1,2,3
                aCnt += 4 - choice;
                cnt.put(a, cnt.get(a) + aCnt);
            }else if(choice > 4) { // 5
                bCnt += choice - 4;
                cnt.put(b, cnt.get(b) + bCnt);
            }
        }

        for(int i = 0; i < twin.size(); i++) {
            String value = twin.get(i);
            String[] splitValue = value.split(" ");
            String a = splitValue[0];
            String b = splitValue[1];

            int aCnt = cnt.get(a);
            int bCnt = cnt.get(b);

            if(aCnt > bCnt) {
                answer += a;
            }else if(aCnt < bCnt) {
                answer += b;
            }else {
                Arrays.sort(splitValue);
                answer += splitValue[0];
            }
        }

        return answer;
    }
}
