package org.sangyunpark.Lv1.신고결과받기;
import java.util.*;

public class Solution_v1_Set_Map {

    private Set<String> record = new HashSet<>();
    private HashMap<String, List<String>> reportPerson = new HashMap<>(); // 대상자를 신고한 사람 목록
    private HashMap<String, Integer> result = new HashMap<>(); // 신고한 사람이 받을 메일 수

    public int[] solution(String[] id_list, String[] report, int k) {

        for(int i = 0; i < id_list.length; i++) {
            String value = id_list[i];
            reportPerson.put(value, new ArrayList<>());
            result.put(value, 0);
        }

        for(int i = 0; i < report.length; i++) {
            String value = report[i];
            String[] splitValue = value.split(" ");
            String a = splitValue[0];
            String b = splitValue[1];

            if(record.contains(a + " " + b)) { // 이미 신고가 된 것인 경우
                continue;
            }

            // 신고가 안된 경우
            record.add(a + " " + b);
            reportPerson.get(b).add(a);
        }

        for(int i = 0; i < id_list.length; i++) { // O(20)
            String name = id_list[i];
            List<String> value = reportPerson.get(name);
            if(value.size() >= k) { // k명 이상한테 신고 받은 경우
                for(int j = 0; j < value.size(); j++) {
                    String a = value.get(j);
                    result.put(a, result.get(a) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];

        for(int i = 0; i < id_list.length; i++) {
            String name = id_list[i];
            answer[i] = result.get(name);
        }

        return answer;
    }
}
